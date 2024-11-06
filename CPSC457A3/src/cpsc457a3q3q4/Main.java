package cpsc457a3q3q4;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int numOfProcs = 3;
        
        // Initialize the turn and flag lists for the DSM
        ArrayList<Integer> turnList = new ArrayList<>();
        ArrayList<Integer> flagList = new ArrayList<>();
        
        for (int i = 0; i < numOfProcs; i++) {
            turnList.add(i, -1);
        }
        for (int i = 0; i < numOfProcs - 1; i++) {
            flagList.add(i, 0);
        }

        // Initialize the broadcast system
        BroadcastSystem broadcastSystem = new BroadcastSystem();
        
        // Initialize the TokenRingAgent list
        ArrayList<TokenRingAgent> ringAgents = new ArrayList<>();
        
        // Create processors, DSMs, and TokenRingAgents
        for (int i = 0; i < numOfProcs; i++) {
            // Create local memory and broadcast agent for each processor
            LocalMemory mem = new LocalMemory(turnList, flagList);
            BroadcastAgent agent = new BroadcastAgent(broadcastSystem);
            DSM dsm = new DSM(mem, agent);
                    
            // Create the TokenRingAgent for each processor
            TokenRingAgent ringAgent = new TokenRingAgent(i, -1, -1, true);
            
            // Add ring agent to the list
            ringAgents.add(ringAgent);
            
            // Assign the ring agent to the processor
            Processor procs = new Processor(i, dsm, numOfProcs, ringAgent);  // Use the same `procs` object
            
            // Start the threads for agent, DSM, and processor
            new Thread(agent).start();
            new Thread(dsm).start();
            new Thread(procs).start();
        }
        
        // Create and start the TokenRing object
        TokenRing tokenRing = new TokenRing(ringAgents);
        tokenRing.startTokenRing();
    }
}
