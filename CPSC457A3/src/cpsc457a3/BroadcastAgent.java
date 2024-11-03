package cpsc457a3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BroadcastAgent extends Thread {
	private BroadcastSystem system;
	private BlockingQueue<String> messageQ = new LinkedBlockingQueue<String>(); 
	
	public BroadcastAgent (BroadcastSystem system) {
		this.system = system;
	}
	
	public void broadcast(String message){
		system.broadcast(message);
	}
	
	public String receiveMessage() {
		try {
			return messageQ.take();
			
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return null;
		}
	}
	
	public void receive(String message){
		//Splitting message string and storing it in the local memory
		messageQ.add(message);
	}
	
    @Override
    public void run() {
    	while(true);
    }
}