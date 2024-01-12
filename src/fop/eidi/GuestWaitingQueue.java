package fop.eidi;

import java.util.*;

public class GuestWaitingQueue {
	private List<Runnable> list;

	public GuestWaitingQueue() {
		this.list = new LinkedList<>();
	}

	public synchronized void enqueue(Runnable r) {
		// TODO
	}

	public synchronized Runnable assign() {
		return null; // TODO
	}
}
