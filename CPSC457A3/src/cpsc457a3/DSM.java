package cpsc457a3;

public class DSM {
    private LocalMemory localMemory;
    private BroadcastAgent broadcastAgent;
    
    public DSM(LocalMemory localMemory, BroadcastAgent broadcastAgent) {
        this.localMemory = localMemory;
        this.broadcastAgent = broadcastAgent;
    }
    
    public void store(int turn, int val) {
        localMemory.store(turn, val);
		broadcastAgent.broadcast(turn, val);
    }
    
    public int load(String x) {
        return localMemory.load(x);
    }
}