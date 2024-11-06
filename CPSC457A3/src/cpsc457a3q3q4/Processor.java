package cpsc457a3q3q4;

public class Processor extends Thread {
    private DSM dsm;
    private int id;
    private int numOfProcs;
    private TokenRingAgent ringAgent;
    
    public Processor(int id, DSM dsm, int numOfProcs, TokenRingAgent ringAgent) {
        this.dsm = dsm;
        this.id = id;
        this.numOfProcs = numOfProcs;
        this.ringAgent = ringAgent;
    }
    
    @Override
    public void run() {
        while (true) {
            // Wait for the token to enter the critical section
            String token = ringAgent.receiveToken();
            
    		//Entry Section
//    		for (int k = 0; k <= numOfProcs - 2; k++) {
//    			dsm.store(id, k, false);
//    			dsm.store(k, id, true);
//    			boolean exists = true;
//    			do {
//    				exists = false;
//    				for (int j = 0; j < numOfProcs; j++) {
//    					if (j != id) {
//    						if (dsm.load(j, false) >= k) {
//    							exists = true;
//    							break;
//    						}
//    					}
//    				}
//    			} while (exists && dsm.load(k, true) == id);
//    		}
    		
    		 // NEW ENTRY THING FROM GPT
            for (int k = 0; k <= numOfProcs - 2; k++) {
                // Store state information in dsm to track each processor's progress
                dsm.store(id, k, false);  
                dsm.store(k, id, true);    

                boolean exists;
                do {
                    exists = false; // Assume no other process exists
                    for (int j = 0; j < numOfProcs; j++) {
                        if (j != id) {
                            // If any other processor has reached the same stage `k` or further, set exists to true
                            if (dsm.load(j, false) >= k) {
                                exists = true;
                                break; // Exit early to recheck the condition
                            }
                        }
                    }
                    
                    // Ensure the processor only continues if it is the right one to proceed
                    if (dsm.load(k, true) == id) {
                        exists = false; // We can proceed since we are the correct processor
                    }

                } while (exists); // Continue looping as long as exists is true
            }
    		
            // Critical Section
            System.out.println("Process " + id + " is in the critical section");
            try {
                Thread.sleep(2000); // Simulate work in the critical section
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            System.out.println("Process " + id + " is leaving the critical section");
            
            // Exit Section
            dsm.store(id, -1, false);

            // Send the token to the next processor
            ringAgent.ringSuccessor.setToken(new Token(token));
        }
    }
}
