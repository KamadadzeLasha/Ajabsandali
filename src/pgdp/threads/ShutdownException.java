package pgdp.threads;

@SuppressWarnings("serial")
public class ShutdownException extends Exception {
    public ShutdownException() {
        super("ThreadPool has already been shut down!");
    }
}