package cpsc457a3;

import java.util.concurrent.ConcurrentLinkedQueue;

public class BroadcastAgent extends Thread {
	private BroadcastSystem system;
	private ConcurrentLinkedQueue<String> messages;
	
	public BroadcastAgent (BroadcastSystem system) {
		this.system = system;
		this.messages = new ConcurrentLinkedQueue<String>();
	}
	
	public synchronized  void broadcast(String message){
		for(BroadcastAgent agent : system.getAgents()) {
			agent.receive(message);
		}
	}
	
    public void receive(String message) {
        messages.add(message);
    }
   
    public synchronized String inboxCheck() {
		return messages.poll();
	}
	
    // Retrieve and process messages from the queue
    @Override
    public void run() {
        while (true);
    }
}