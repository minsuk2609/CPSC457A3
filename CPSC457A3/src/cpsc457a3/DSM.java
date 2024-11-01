package cpsc457a3;

public class DSM {
    private LocalMemory localMemory;
    private BroadcastAgent broadcastAgent;
    
    public DSM(LocalMemory localMemory, BroadcastAgent broadcastAgent) {
        this.localMemory = localMemory;
        this.broadcastAgent = broadcastAgent;
    }
    
    public void store(String x, int val) {
        localMemory.store(x, val);
		broadcastAgent.broadcast(x, val);

    }
    
    public int load(String x) {
        return localMemory.load(x);
    }
}

