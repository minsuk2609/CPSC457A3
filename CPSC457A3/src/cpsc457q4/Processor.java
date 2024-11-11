package cpsc457q4;

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
		//Entry Section
    	for (int k = 0; k <= numOfProcs - 2; k++) {
			//flag
			dsm.store(this.id, k, false, this.ringAgent);
			//turn
			dsm.store(k, this.id, true, this.ringAgent);
			
			System.out.println(ringAgent.getToken().getId());
			boolean exists = false;
			do {
				for (int j = 0; j < numOfProcs; j++) {
					if (j != id) {
						if (dsm.load(j, false) >= k) {
							exists = true;
							break;
						}
					}
				}
			} while (exists && dsm.load(k, true) == id);
		}
		
		//Critical Section
		System.out.println("Process " + id + " is in the critical section at: " + System.currentTimeMillis());
		dsm.increment(id);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Process " + id + " is leaving the critical section at: " + System.currentTimeMillis());
		dsm.decrement(id);
		dsm.store(id, -1, false, this.ringAgent);
		Token token = ringAgent.getToken();
		ringAgent.setToken(null);
		ringAgent.getRingSuccessor().setToken(token);
		
    }
}