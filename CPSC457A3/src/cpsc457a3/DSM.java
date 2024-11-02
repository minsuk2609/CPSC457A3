package cpsc457a3;

public class DSM {
    private LocalMemory localMemory;
    private BroadcastAgent broadcastAgent;
    
    public DSM(LocalMemory localMemory, BroadcastAgent broadcastAgent) {
        this.localMemory = localMemory;
        this.broadcastAgent = broadcastAgent;
    }
    
    public void store(int turn, String val) {
        localMemory.store(turn, val);
		broadcastAgent.broadcast(val);
    }
    
    public String load(int x) {
        return localMemory.load(x);
    }
}