package com.yuanfy.study.snmp;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.CommandResponder;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.MessageDispatcher;
import org.snmp4j.MessageDispatcherImpl;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.MPv1;
import org.snmp4j.mp.MPv2c;
import org.snmp4j.mp.MPv3;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.snmp4j.util.ThreadPool;

public class SnmpTrapHandler implements CommandResponder{
	private static Logger log = LoggerFactory.getLogger(SnmpTrapHandler.class);
	private static int threadNum = 200;
	private static String ipAddress = "udp:127.0.0.1/";
	private Snmp snmp = null;
	public void init(int port){
		//1、初始化多线程消息转发类
		ThreadPool threadPool = ThreadPool.create("SnmpTrap", threadNum);
		MessageDispatcher messageDispatcher = new MultiThreadedMessageDispatcher(threadPool, new MessageDispatcherImpl()); 
		//其中要增加三种处理模型。如果snmp初始化使用的是Snmp(TransportMapping<? extends Address> transportMapping) ,就不需要增加 
		messageDispatcher.addMessageProcessingModel(new MPv1());
		messageDispatcher.addMessageProcessingModel(new MPv2c());
		OctetString localEngineID = new OctetString(MPv3.createLocalEngineID());
		USM usm = new USM(SecurityProtocols.getInstance().addDefaultProtocols(), localEngineID, 0);
		//usm.addUser(user);
		messageDispatcher.addMessageProcessingModel(new MPv3(usm));
		//2、创建transportMapping
		TransportMapping transportMapping = null;
		try {
			UdpAddress updAddr = (UdpAddress) GenericAddress.parse(System.getProperty("snmp4j.listenAddress", "udp:127.0.0.1/162"));
			transportMapping = new DefaultUdpTransportMapping(updAddr);
			//3、正式创建snmp
			snmp = new Snmp(messageDispatcher, transportMapping);
			//开启监听
			snmp.listen();
		} catch (IOException e) { 
			log.error("初始化transportMapping失败：", e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void start() {
		init(162);
		snmp.addCommandResponder(this);
		System.out.println("开始监听trap信息");
	}
	@Override
	public void processPdu(CommandResponderEvent event) {
		System.out.println(event);
	}
	public static void main(String[] args) {
		SnmpTrapHandler handler = new SnmpTrapHandler();
		handler.start();
	}
}
