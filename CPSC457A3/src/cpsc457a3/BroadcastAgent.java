package cpsc457a3;

public class BroadcastAgent extends Thread {
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
		int index = Integer.valueOf(splitString[0]);
		int value = Integer.valueOf(splitString[1]);
		boolean turn = Boolean.parseBoolean(splitString[2]);
		localMemory.store(index, value, turn);
	}
	
    @Override
    public void run() {
    	while(true);
    }
}