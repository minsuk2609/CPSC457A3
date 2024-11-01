package cpsc457a3;

public class BroadcastAgent extends Thread{
	private final int id;
	private final LocalMemory localMemory;
	private final BroadcastSystem broadcastSystem;
	
	public BroadcastAgent(int id,LocalMemory localMemory, BroadcastSystem broadcastSystem) {
		this.id = id;
		this.localMemory = localMemory;
		this.broadcastSystem = broadcastSystem;
	}
	
	public void broadcast(String x, int v) throws InterruptedException{
		Message message = new Message(x, v, id);
		broadcastSystem.broadcast(message);
	}
	
	public void receive() throws InterruptedException{
		Message message;
		message = broadcastSystem.receive();
		localMemory.store(message.getKey(), message.getValue());
	}
	
	@Override
	public void run() {
        while (true) {
            try {
                receive();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
	}
}
