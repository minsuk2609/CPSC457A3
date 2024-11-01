package cpsc457a3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BroadcastSystem extends Thread{
	private final BlockingQueue<Message> messages = new LinkedBlockingQueue<>();
	
	public void broadcast(Message message) throws InterruptedException{
		Thread.sleep(100);
		messages.put(message);
	}
	
	public Message receive() throws InterruptedException {
		Message message;
		message = messages.take();
		Thread.sleep(100);
		return message;
	}
	
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			Message message;
			try {
				message = receive();
				System.out.println("Processed message: " + message);
			} catch (InterruptedException e) {
				Thread.currentThread().isInterrupted();
				e.printStackTrace();
			}
		}
	}
}

