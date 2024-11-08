package cpsc457a3q4;

public class DSM extends Thread{
    private LocalMemory localMemory;
    private BroadcastAgent broadcastAgent;
    
    public DSM(LocalMemory localMemory, BroadcastAgent broadcastAgent) {
        this.localMemory = localMemory;
        this.broadcastAgent = broadcastAgent;
    }
    
    public synchronized void store(int index, int value, boolean turn, TokenRingAgent tra) {
    	while(turn == true && (tra.receiveToken() != validTokenCheck(index))) {	
    		 try {
    	            Thread.sleep(1000);  // Simulate a delay before receiving the token
    	        } catch (InterruptedException e) {
    	            e.printStackTrace();
    	        }
    	};
    	
		localMemory.store(index, value, turn);
		String message = String.format("%d %d %b", index, value, turn);
		broadcastAgent.broadcast(message);
    }
    
    public int load(int index, boolean turn) {
        return localMemory.load(index, turn);
    }
    
    @Override
    public void run() {
    	while(true) {
    		String message = broadcastAgent.inboxCheck();
    		if(message != null) {
    			String[] splitString = message.split(" ");
    			int index = Integer.parseInt(splitString[0]);
    			int value = Integer.parseInt(splitString[1]);
    			boolean turn = Boolean.parseBoolean(splitString[2]);
    			localMemory.store(index, value, turn);
    		}
    	}
    }
    
}