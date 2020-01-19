package ETQWatcher;

import ETQPrinter.ETQPrinter;

import java.io.IOException;
import java.nio.file.*;

public class ETQWatcher {

    private final String FILE_CREATED = "ENTRY_CREATE";
    private final String FILE_MODIFIED = "ENTRY_MODIFY";
    private final String FILE_DELETED = "ENTRY_DELETE";
    private Path pathToWatch;
    private WatchService watchService;
    private ETQHandlerCallable ETQHandler;
    private WatchKey watchKey;
    private ETQPrinter printer;
    private ETQWatcherRun etqWatcherRun;

    public ETQWatcher(Path pathToWatch, ETQPrinter printer) throws IOException {
        this.pathToWatch = pathToWatch;
        this.printer = printer;
        this.watchService = FileSystems.getDefault().newWatchService();
        this.ETQHandler = new ETQHandlerCallable();
        this.etqWatcherRun = new ETQWatcherRun(this);
    }

    public Path getPathToWatch() {
        return pathToWatch;
    }

    public void setPathToWatch(Path pathToWatch) {
        this.pathToWatch = pathToWatch;
    }

    private void registerPathForWatch() throws IOException {
        System.out.println("Dossier " + this.pathToWatch + " monitoré...");
        this.pathToWatch.register(
                this.watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE
        );
    }

    public void treatWatchEvent() throws Exception {
        while ((this.watchKey = this.watchService.take()) != null) {
            for (WatchEvent<?> event : this.watchKey.pollEvents()) {
                switch (event.kind().name()) {
                    case FILE_CREATED:
                        this.ETQHandler.setEtqHandlerType(ETQHandlerTypes.CREATE);
                        break;
                    case FILE_MODIFIED:
                        this.ETQHandler.setEtqHandlerType(ETQHandlerTypes.MODIFY);
                        break;
                    case FILE_DELETED:
                        this.ETQHandler.setEtqHandlerType(ETQHandlerTypes.DELETE);
                        break;
                }
                String fileExtension = this.getExtensionByStringHandling(event.context().toString());
                if (fileExtension != null && fileExtension.equals("pdf")) {
                    this.printer.setPathToPrint(Paths.get(this.pathToWatch.toString() + "\\" + event.context().toString()));
                    this.ETQHandler.setPrinter(this.printer);
                    this.ETQHandler.call();
                }
            }
            this.watchKey.reset();
        }
    }

    public void startWatching() throws Exception {
        this.registerPathForWatch();
        this.etqWatcherRun.start();
    }

    public void stopWatching() {
        this.etqWatcherRun.interrupt();
        if (this.watchKey != null) this.watchKey.cancel();
    }

    private String getExtensionByStringHandling(String filename) {
        return filename.lastIndexOf(".") >= 0 ? filename.substring(filename.lastIndexOf(".") + 1) : null;
    }
}
