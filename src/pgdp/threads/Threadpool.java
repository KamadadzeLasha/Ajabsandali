package pgdp.threads;

import java.util.concurrent.LinkedBlockingQueue;

public class Threadpool {
	Thread[] workers;
	LinkedBlockingQueue<Task> queue;
	boolean running = true;
	public Threadpool(int numWorkers) {
		if (numWorkers <= 0)
			throw new IllegalArgumentException(
					"Must have more than 0 Workers!");
		queue = new LinkedBlockingQueue<>();
		workers = new Thread[numWorkers];
		Runnable worker = () -> {
			try {
				while (true) {
					Task t = queue.take();
					t.getRunnable().run();
					t.getFuture().finish();
				}
			} catch (InterruptedException e) {
				// Shutting down
			}
		};
		for (int i = 0; i < workers.length; i++) {
			workers[i] = new Thread(worker);
			workers[i].start();
		}
	}
	public Future submit(Runnable job)
			throws InterruptedException, ShutdownException {
		if (running) {
			Future result = new Future();
			queue.put(new Task(job, result));
			return result;
		} else {
			throw new ShutdownException();
		}
	}
	public void shutdownNow() {
		running = false;
		for (Thread t : workers)
			t.interrupt();
	}
}