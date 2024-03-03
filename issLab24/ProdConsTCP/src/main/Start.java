package main;

import unibo.basicomm23.utils.CommUtils;

public class Start{

	public static void main(String[] args) {
		CommUtils.outcyan("Starting Consumer");
		Consumer cons = new Consumer();
		cons.start();
		CommUtils.outcyan("Starting Producers");
		Producer p = new Producer();
		p.start();
		
	}

}
