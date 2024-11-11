package cpsc457a3;

public class DSM extends Thread{
    private LocalMemory localMemory;
    private BroadcastAgent broadcastAgent;
    
    public DSM(LocalMemory localMemory, BroadcastAgent broadcastAgent) {
        this.localMemory = localMemory;
        this.broadcastAgent = broadcastAgent;
    }
    
    public synchronized void store(int index, int value, boolean turn) {
		localMemory.store(index, value, turn);
		String message = String.format("%d %d %b", index, value, turn);
		broadcastAgent.broadcast(message);
    }
    
    public synchronized int load(int index, boolean turn) {
        return localMemory.load(index, turn);
    }
    
	public synchronized void increment(int processId) {
		localMemory.increment(processId);
		broadcastAgent.broadcast("Increment " + Integer.toString(processId));
	}
	
	public synchronized void decrement(int processId) {
		localMemory.decrement(processId);
		broadcastAgent.broadcast("Decrement " + Integer.toString(processId));
	}
    
    @Override
    public void run() {
    	while(true) {
    		String message = broadcastAgent.inboxCheck();
    		if(message != null) {
    			String[] splitString = message.split(" ");
    			if (splitString[0].equals("Increment")) {
    				int counter = Integer.parseInt(splitString[1]);
    				localMemory.increment(counter);
    			}
    			else if(splitString[0].equals("Decrement")) {
    				int counter = Integer.parseInt(splitString[1]);
    				localMemory.decrement(counter);
    			}
    			else {
        			int index = Integer.parseInt(splitString[0]);
        			int value = Integer.parseInt(splitString[1]);
        			boolean turn = Boolean.parseBoolean(splitString[2]);
        			localMemory.store(index, value, turn);
    			}
    		}
    	}
    }
    
}