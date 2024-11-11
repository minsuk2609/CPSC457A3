package cpsc457q4;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int numOfProcs = 5;
        
        // Initialize the turn and flag lists for the DSM
        ArrayList<Integer> turnList = new ArrayList<>();
        ArrayList<Integer> flagList = new ArrayList<>();
        
        for (int i = 0; i < numOfProcs; i++) {
            turnList.add(i, -1);
        }
        for (int i = 0; i < numOfProcs; i++) {
            flagList.add(i, -1);
        }

        // Initialize the broadcast system
        BroadcastSystem broadcastSystem = new BroadcastSystem();
        
        // Initialize the TokenRingAgent list
        ArrayList<TokenRingAgent> ringAgents = new ArrayList<>();
        
        for (int i = 0; i < numOfProcs; i++) {
        	TokenRingAgent ringAgent = new TokenRingAgent(i, -1, -1, true);
        	ringAgents.add(ringAgent);
        }
        
        for (int i = 0; i <= numOfProcs - 2; i++) {
            // Create and start the TokenRing object
            TokenRing tokenRing = new TokenRing(ringAgents);
            tokenRing.setToken(Integer.toString(i));
            tokenRing.startTokenRing();
        }
        
        // Create processors, DSMs, and TokenRingAgents
        LocalMemory mem = new LocalMemory(turnList, flagList);
        for (int i = 0; i < numOfProcs; i++) {
            // Create local memory and broadcast agent for each processor
            BroadcastAgent agent = new BroadcastAgent(broadcastSystem);
            DSM dsm = new DSM(mem, agent);
            
            
            broadcastSystem.addAgent(agent);
            
            // Assign the ring agent to the processor
            Processor procs = new Processor(i, dsm, numOfProcs, ringAgents.get(i));  // Use the same `procs` object
            
            // Start the threads for agent, DSM, and processor
            new Thread(agent).start();
            new Thread(dsm).start();
            new Thread(procs).start();
            new Thread(ringAgents.get(i)).start();
        }
        
    }
}
