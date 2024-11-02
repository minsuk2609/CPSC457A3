package cpsc457a3;

public class BroadcastAgent extends Thread{
	private BroadcastSystem system;
	private LocalMemory localMemory;
	
	public BroadcastAgent (BroadcastSystem system, LocalMemory localMemory) {
		this.system = system;
		this.localMemory = localMemory;
	}
	
	public void broadcast(String message){
		system.broadcast(message);
	}
	
	public void receive(String message){
		localMemory.store(turn, i)
	}
}