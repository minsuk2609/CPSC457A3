package cpsc457a3;

public class Processor extends Thread{
	private DSM dsm;
	private int id;
	private int numOfProcs;
	
	public Processor (int id, DSM dsm, int numOfProcs) {
		this.dsm = dsm;
		this.id = id;
		this.numOfProcs = numOfProcs;
	}
	
	@Override
	public void run() {
		//Entry Section
		for (int k = 0; k <= numOfProcs - 2; k++) {
			dsm.store(id + " " + k + " " + false);
			dsm.store(k + " " + id + " " + true);
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
		
		System.out.println("Process " + id + " is leaving the critical section");
		
		dsm.store(id + " " + -1 + " " + false);
	}
	

		

}
