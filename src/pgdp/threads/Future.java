package pgdp.threads;

public class Future {

	boolean going = true;
	public synchronized void get() throws InterruptedException {
		while(going){
			wait();
		}
	}
	public synchronized void finish() {
		going  = false;
		notifyAll();
	}
}
