package main;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.BasicMsgUtil;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.utils.ConnectionFactory;

public class Producer extends Thread{
	
	public void run() {
		ProtocolType protocol = ProtocolType.tcp;
		String hostAddr = "localhost";
		String entry    = "8090";
		Interaction conn = ConnectionFactory.createClientSupport(protocol, hostAddr, entry);
		
		String destination = "Sever";
		String sender      = "Client";
		String msgid       = "doJob";
		String msgcontent  = "12";
		IApplMessage req = BasicMsgUtil.buildRequest(sender,msgid,msgcontent,destination);
		
		String answer = "";
		try {
			conn.forward(req);
			answer = conn.receiveMsg();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CommUtils.outblue("ack: "+answer);
	}
	
}
