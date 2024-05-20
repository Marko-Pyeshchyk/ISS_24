package main.resources.tickets;
import java.sql.Timestamp;
import java.util.HashMap;

public class Tickets {
	private HashMap<Integer,Values> Tic;
	private int w;
	private int currID;
	private int secs;

	public Tickets() {
		this.Tic=new HashMap<Integer,Values>();
		this.w=0;
		this.currID=0;
		this.secs=20;
	}
	
	public String store(int weight) {
		chackTickets();
		currID++;
		Tic.put(currID, new Values(new Timestamp(System.currentTimeMillis()), weight));
		return String.valueOf(currID);
	}
	
	public int spaceBooked()
	{
		chackTickets();
		return w;
	}
	
	public int checkValidity(String s) {
		int id= Integer.valueOf(s);
		int temp=0;
		chackTickets();
		if(Tic.get(id)!=null) {
			temp=Tic.get(id).getWeight();
			w=w-temp;
			Tic.remove(id);
			return temp;
		}
		return temp;
	}
	
	public void chackTickets() {
		for(int l:Tic.keySet()) {
			if (Tic.get(l).getTimestamp().getTime()+(secs*1000) < System.currentTimeMillis()) {
				w=w-Tic.get(l).getWeight();
				Tic.remove(l);
			}
		}
	}
}
