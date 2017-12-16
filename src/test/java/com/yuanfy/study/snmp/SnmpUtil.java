package com.yuanfy.study.snmp;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.UserTarget;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.SecurityLevel;
import org.snmp4j.security.SecurityModel;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;

/**
 * @description snmp工具类
 * @author YuanFY 
 * @date 2017年12月16日 上午10:16:44
 * @version 1.0
 */
public class SnmpUtil {
	private static Logger log = LoggerFactory.getLogger(SnmpUtil.class);
	public static Snmp snmp = null;
	private static String community = "public";
	private static String ipAddress = "udp:127.0.0.1/";
	
	/**
	 * @description 初始化snmp
	 * @author YuanFY
	 * @date 2017年12月16日 上午10:28:01
	 * @version 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void initSnmp(int port){
        try {
        	TransportMapping transport = new DefaultUdpTransportMapping();  
            snmp = new Snmp(transport);  
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	/**
	 * @description 针对snmp版本创建对应的目标对象
	 * @param version
	 * @return
	 * @author YuanFY
	 * @date 2017年12月16日 下午1:59:22
	 * @version 1.0
	 */
	private static Target createTarget(int version, int port) {
		Target target = null;
		if (!(version == SnmpConstants.version3 || version == SnmpConstants.version2c || version == SnmpConstants.version1)) {
			log.error("参数version异常");
			return target;
		}
		if (version == SnmpConstants.version3) {
			target = new UserTarget();
			target.setSecurityLevel(SecurityLevel.AUTH_PRIV);
			target.setSecurityModel(SecurityModel.SECURITY_MODEL_USM);
			target.setSecurityName(new OctetString("MD5DES"));
		} else {
			target = new CommunityTarget();
			((CommunityTarget)target).setCommunity(new OctetString(community));
			if (version == SnmpConstants.version2c) {
				target.setSecurityModel(SecurityModel.SECURITY_MODEL_SNMPv2c);
			}
		}
		target.setVersion(version);
		target.setAddress(GenericAddress.parse(ipAddress + port));
		target.setRetries(5);
		target.setTimeout(3000);
		return target;
	}
	/**
	 * @description 通过oid获取节点信息
	 * @author YuanFY
	 * @date 2017年12月16日 上午11:54:24
	 * @version 1.0
	 */
	public static void snmpGet(String oid){
		Target target = createTarget(SnmpConstants.version1, 161);
		PDU pdu = new PDU();
		pdu.add(new VariableBinding(new OID(oid)));
		pdu.setType(PDU.GET);
		System.out.println("-------> 发送PDU <-------"); 
		try {
			ResponseEvent responseEvent = snmp.send(pdu, target);
			PDU response = responseEvent.getResponse();
			System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	public static void snmpWalk(String oid) {
		TableUtils tableUtils = new TableUtils(snmp, new DefaultPDUFactory(PDU.GETBULK));
		tableUtils.setMaxNumRowsPerPDU(5);
		OID[] columnOids = new OID[] {  
                new OID("1.3.6.1.2.1.1.9.1.2"), //sysORID  
                new OID("1.3.6.1.2.1.1.9.1.3"), //sysORDescr  
                new OID("1.3.6.1.2.1.1.9.1.5")  //wrong OID, expect an null in in VariableBinding array  
        };  
		List<TableEvent> result = tableUtils.getTable(createTarget(SnmpConstants.version2c, 161), columnOids, new OID("3"), new OID("10"));
	}
	public static void sendTrap() throws IOException{
		Target target = createTarget(SnmpConstants.version2c, 162); 
		PDU pdu = new PDU();  
        pdu.add(new VariableBinding(new OID(".1.3.6.1.2.3377.10.1.1.1.1"),  
                new OctetString("SnmpTrap")));  
        pdu.add(new VariableBinding(new OID(".1.3.6.1.2.3377.10.1.1.1.2"),  
                new OctetString("JavaEE")));  
        pdu.setType(PDU.TRAP);  
        
        try {
			ResponseEvent respEvnt = snmp.send(pdu, target);  
			System.out.println(respEvnt);
		} catch (IOException e) {
			e.printStackTrace();
		}	
        
	}
}
