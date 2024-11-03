package cpsc457a3;

public class DSM extends Thread{
    private LocalMemory localMemory;
    private BroadcastAgent broadcastAgent;
    
    public DSM(LocalMemory localMemory, BroadcastAgent broadcastAgent) {
        this.localMemory = localMemory;
        this.broadcastAgent = broadcastAgent;
    }
    
    public void store(String message) {
		String[] splitString = message.split(" ");
		int index = Integer.valueOf(splitString[0]);
		int value = Integer.valueOf(splitString[1]);
		boolean turn = Boolean.parseBoolean(splitString[2]);
		localMemory.store(index, value, turn);
		broadcastAgent.broadcast(message);
    }
    
    public int load(int index, boolean turn) {
        return localMemory.load(index, turn);
    }
    
    @Override
    public void run() {
    	while(true);
    }
    
}