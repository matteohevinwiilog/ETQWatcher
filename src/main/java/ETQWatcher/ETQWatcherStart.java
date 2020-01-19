package ETQWatcher;

import ETQPrinter.ETQPrinter;
import ETQPrinter.ETQPrinterDetector;

import javax.print.PrintService;
import javax.swing.border.EtchedBorder;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ETQWatcherStart {

    private ETQPrinterDetector etqPrinterDetector;
    private PrintService chosenPrinter;
    private ETQPrinter printer;
    private ETQPathChooser etqPathChooser;
    private Path pathToWatch;
    private ETQWatcher watcher;
    private ETQWatcherPreferences etqWatcherPreferences;

    public ETQWatcherStart() {
        this.etqWatcherPreferences = new ETQWatcherPreferences();
        this.etqPrinterDetector = new ETQPrinterDetector();
        this.printer = new ETQPrinter();
        this.etqPathChooser = new ETQPathChooser();
    }

    private void start() throws Exception {
        this.pathToWatch = Paths.get(this.etqWatcherPreferences.getChosenPathToWatch());
        this.chosenPrinter = this.etqPrinterDetector.getByName(this.etqWatcherPreferences.getChosenPrinter());
        this.printer.setChosenPrinter(this.chosenPrinter);
        this.watcher = new ETQWatcher(pathToWatch, printer);
        System.out.println("Programme intialisé avec le dossier : " + this.pathToWatch.toString());
        System.out.println("Programme intialisé avec l'imprimante : " + this.chosenPrinter.getName());
        watcher.startWatching();
        this.listenForModify();
    }

    private void startWithSetup() throws Exception {
        this.chosenPrinter = etqPrinterDetector.detectAndChoosePrinter();
        this.printer.setChosenPrinter(chosenPrinter);
        this.setupWatcher();
        try {
            watcher.startWatching();
            this.listenForModify();
        } catch (NoSuchFileException ex) {
            this.setupWatcher();
        }
    }

    private void setupWatcher() throws IOException {
        this.pathToWatch = etqPathChooser.choosePathToWatch();
        this.watcher = new ETQWatcher(pathToWatch, printer);
        this.etqWatcherPreferences.setChosenPathToWatch(this.pathToWatch.toString());
        this.etqWatcherPreferences.setChosenPrinter(this.chosenPrinter.getName());
    }

    private boolean needsStartupSetup() {
        System.out.println(this.etqWatcherPreferences.getChosenPrinter());
        System.out.println(this.etqWatcherPreferences.getChosenPathToWatch());
        return (
                this.etqWatcherPreferences.getChosenPrinter() == null ||
                        this.etqWatcherPreferences.getChosenPathToWatch() == null ||
                !this.etqPathChooser.isValidPath(this.etqWatcherPreferences.getChosenPathToWatch()));
    }

    public void init() throws Exception {
        if (this.needsStartupSetup()) {
            this.startWithSetup();
        } else {
            this.start();
        }
    }

    private void listenForModify() throws Exception {
        if (this.etqWatcherPreferences.modifyPreferences()) {
            this.watcher.stopWatching();
            this.startWithSetup();
        }
    }
}
