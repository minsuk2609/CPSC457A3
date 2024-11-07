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
    	String token = null;
		//Entry Section
    	for (int k = 0; k <= numOfProcs - 2; k++) {
			//flag
			dsm.store(id, k, false);
			while (true) {
				token = ringAgent.receiveToken();
				boolean tokenStatus = !token.isBlank();
				dsm.tokenStatus(tokenStatus);
				
				if (tokenStatus) break;
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					e.printStackTrace();
				}
			}
			dsm.store(k, id, true);
			ringAgent.ringSuccessor.setToken(new Token(token));
			boolean exists = true;
			do {
				exists = false;
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
		System.out.println("Process " + id + " is in the critical section");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dsm.increment(id);
		
		dsm.decrement(id);
		System.out.println("Process " + id + " is leaving the critical section");
		
		dsm.store(id, -1, false);
		
    }
}
