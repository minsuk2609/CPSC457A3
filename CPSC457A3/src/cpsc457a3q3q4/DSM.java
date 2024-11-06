package cpsc457a3q3q4;

public class DSM extends Thread {
    private LocalMemory localMemory;
    private BroadcastAgent broadcastAgent;
    
    public DSM(LocalMemory localMemory, BroadcastAgent broadcastAgent) {
        this.localMemory = localMemory;
        this.broadcastAgent = broadcastAgent;
    }
    
    // Store method without token management
    public synchronized void store(int index, int value, boolean turn) {
        localMemory.store(index, value, turn);
        String message = String.format("%d %d %b", index, value, turn);
        broadcastAgent.broadcast(message);
    }
    
    public synchronized int load(int index, boolean turn) {
        return localMemory.load(index, turn);
    }
    
    @Override
    public void run() {
        while (true) {
            // Wait for a broadcast message
            String message = broadcastAgent.receive();
            if (message != null) {
                String[] splitString = message.split(" ");
                int index = Integer.parseInt(splitString[0]);
                int value = Integer.parseInt(splitString[1]);
                boolean turn = Boolean.parseBoolean(splitString[2]);
                localMemory.store(index, value, turn);
            }
            
            // Avoid busy-waiting
            try {
                Thread.sleep(10);  // Short sleep to avoid constant polling
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
