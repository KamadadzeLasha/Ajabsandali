package fop.eidi;

import java.util.*;

public class Restaurant {
	private GuestWaitingQueue tasks = null;
	private Table[] threads = null;

	public Restaurant(int threadCount){
        // TODO
	}

	public synchronized void  reserve(Runnable task) throws Exception {
        // TODO
	}

	public synchronized void stop(){
        // TODO
	}
}
