package cpsc457a3q3q4;

public class BroadcastAgent extends Thread {
	private BroadcastSystem system;
	
	public BroadcastAgent (BroadcastSystem system) {
		this.system = system;
		system.addAgent(this);
	}
	
	public synchronized void broadcast(String message){
		system.broadcast(message);
	}
	
    public synchronized String receive() {
        return system.receive(this);
    }
	
    // Retrieve and process messages from the queue
    @Override
    public void run() {
        while (true);
    }
}