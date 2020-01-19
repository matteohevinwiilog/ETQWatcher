package ETQWatcher;

public class ETQWatcherRun extends Thread {

    private ETQWatcher etqWatcher;

    public ETQWatcherRun(ETQWatcher etqWatcher) {
        this.etqWatcher = etqWatcher;
    }

    @Override
    public void run() {
        try {
            this.etqWatcher.treatWatchEvent();
        } catch (Exception ignored) {
        }
    }
}
