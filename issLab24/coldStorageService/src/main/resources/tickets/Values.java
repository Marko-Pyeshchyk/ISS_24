package main.resources.tickets;
import java.sql.Timestamp;

public class Values {
	private Timestamp timestamp;
	private int weight;
	
	public Values(Timestamp timestamp, int weight) {
		super();
		this.timestamp = timestamp;
		this.weight = weight;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
}
