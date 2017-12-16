package com.yuanfy.study.snmp;

import java.io.IOException;

/**
 * @description snmp测试类
 * @author YuanFY 
 * @date 2017年12月16日 上午9:48:36
 * @version 1.0
 */
public class SnmpTest {

	public static void main(String[] args) throws IOException {
		//snmpget
//		SnmpUtil.initSnmp(161);
//		SnmpUtil.startListen();
//		SnmpUtil.snmpGet("1.3.6.1.2.1.25.2.1");
		SnmpUtil.initSnmp(162);
		SnmpUtil.sendTrap();
		SnmpUtil.snmpGet("1.3.6.1.2.1.25.2.1");
	}
}
