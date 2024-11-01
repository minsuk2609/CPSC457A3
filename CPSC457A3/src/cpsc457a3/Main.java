package cpsc457a3;

public class Main {
    public static void main(String[] args) {
        int numProcessors = 10; // Set number of processors (can be increased for your experiments)
        
        // Create BroadcastSystem instance (shared by all processors)
        BroadcastSystem broadcastSystem = new BroadcastSystem();
        
        // Create array to hold all the processors
        Processor[] processors = new Processor[numProcessors];

        // Create DSM instances for each processor
        for (int i = 0; i < numProcessors; i++) {
            DSM dsm = new DSM(i, broadcastSystem); // DSM takes processor id and broadcastSystem as arguments
            processors[i] = new Processor(i, dsm); // Processor takes processor id and DSM object
        }

        // Create a TokenRing with a single token
        TokenRing tokenRing = new TokenRing(numProcessors);
        
        // Add TokenRingAgents to processors and set up the token ring
        for (int i = 0; i < numProcessors; i++) {
            TokenRingAgent tokenRingAgent = new TokenRingAgent(i, (i - 1 + numProcessors) % numProcessors, (i + 1) % numProcessors, tokenRing);
            processors[i].setTokenRingAgent(tokenRingAgent);
        }

        // Start the processors (each processor runs in a separate thread)
        for (int i = 0; i < numProcessors; i++) {
            new Thread(processors[i]).start();
        }

        // Start the broadcast system
        new Thread(broadcastSystem).start();
        
        // Start the token ring (begin with the first processor)
        tokenRing.startTokenRing(processors[0].getTokenRingAgent());

        // Allow processors to enter their critical sections multiple times
        for (int i = 0; i < numProcessors; i++) {
            processors[i].enterCriticalSection();
        }
    }
}
