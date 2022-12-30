package pgdp.threads;

public class TaskBuilder {
    private Runnable runnable;
    private Future future;

    public TaskBuilder setRunnable(Runnable runnable) {
        this.runnable = runnable;
        return this;
    }

    public TaskBuilder setFuture(Future future) {
        this.future = future;
        return this;
    }

    public Task createTask() {
        return new Task(runnable, future);
    }
}