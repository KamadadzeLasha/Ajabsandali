package pgdp.threads;

import java.util.concurrent.LinkedBlockingQueue;

public class Threadpool {
	Thread[] workers;
	LinkedBlockingQueue<Task> queue;
	boolean runnig ;

	public Threadpool(int numWorkers) {
		if (numWorkers <= 0){
			throw  new IllegalArgumentException( "Must have more than 0 Workers!");
		}
		
	}

	public Future submit(Runnable job) {
		// TODO
		return null;
	}

	public void shutdownNow() {

	}
}
