package main;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.BasicMsgUtil;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.utils.ConnectionFactory;

public class ServiceWithInteraction {
	
	public static void main(String[] args) throws Exception {
		
		ProtocolType protocol = ProtocolType.tcp;
		String hostAddr = "130.136.113.239";
		String entry    = "8011";
		Interaction conn = ConnectionFactory.createClientSupport(protocol, hostAddr, entry);
		
		String destination = "servicemath";
		String sender      = "client";
		String msgid       = "dofibo";
		String msgcontent  = "dofibo(10)";
		IApplMessage req = BasicMsgUtil.buildRequest(sender,msgid,msgcontent,destination);
		
		//String answer = conn.request(req.toString());
		conn.forward(req);
		String answer = conn.receiveMsg();
		CommUtils.outblue("answer:"+answer);

	}

}
