import ETQWatcher.ETQWatcherStart;

public class Main {
    public static void main(String[] args) {
        try {
            ETQWatcherStart etqWatcherStart = new ETQWatcherStart();
            etqWatcherStart.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
