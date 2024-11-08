package withtoken;


public class TokenRingAgent extends Thread{
	private volatile int processorId; // Logical ID for the processor
	private TokenRingAgent ringPredecessor; // Logical ID of predecessor in the ring
	private TokenRingAgent ringSuccessor; // Logical ID of successor in the ring
	private volatile boolean active; // Determines if the token ring is active
	private volatile Token token; // The token currently held
	private volatile TokenRing tokenRing;
	
	public TokenRingAgent(TokenRing tokenRing, int processorId) {
		this.tokenRing = tokenRing;
		this.processorId = processorId;
	}
	
	public synchronized TokenRingAgent getRingPredecessor() {
		return this.ringPredecessor;
	}
	
	public synchronized TokenRingAgent getRingSuccessor() {
		return this.ringSuccessor;
	}
	
	public synchronized void setRingPredecessor(TokenRingAgent ringPredecessor) {
		this.ringPredecessor = ringPredecessor;
	}
	
	public synchronized void setRingSuccessor(TokenRingAgent ringSuccessor) {
		this.ringSuccessor = ringSuccessor;
	}
	
	// Method to receive a new token (sets the token directly)
	public synchronized void setToken(Token t) {
		this.token = t;
	}
	
	public synchronized String receiveToken() {
		
	}
}
