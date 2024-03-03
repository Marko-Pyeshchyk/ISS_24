package main;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.BasicMsgUtil;
import unibo.basicomm23.utils.ConnectionFactory;

public class Consumer extends Thread{
	public void run() {
		ProtocolType protocol = ProtocolType.tcp;
		String hostAddr = "localhost";
		String entry    = "8090";
		Interaction conn = ConnectionFactory.createClientSupport(protocol, hostAddr, entry);
		
		String destination = "Client";
		String sender      = "Server";
		String msgid       = "doJob";
		int ack = 0;
		
		while (true) {
			System.out.println("------processing------");
			String msgcontent  = Integer.toString(ack++);
			IApplMessage response = BasicMsgUtil.buildRequest(sender,msgid,msgcontent,destination);
			
			try {
				conn.receiveMsg();
				conn.forward(response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
