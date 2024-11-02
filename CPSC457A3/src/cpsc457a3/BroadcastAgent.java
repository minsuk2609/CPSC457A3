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
		//Splitting message string and storing it in the local memory
		String[] splitString = message.split(" ");
		int turn = Integer.valueOf(splitString[0]);
		String i = splitString[1];
		localMemory.store(turn, i);
	}
}