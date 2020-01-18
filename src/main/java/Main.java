import ETQPrinter.ETQPrinter;
import ETQPrinter.ETQPrinterDetector;
import ETQWatcher.ETQWatcher;

import javax.print.PrintService;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            ETQPrinterDetector etqPrinterDetector = new ETQPrinterDetector();
            PrintService chosenPrinter = etqPrinterDetector.detectAndChoosePrinter();
            ETQPrinter printer = new ETQPrinter();
            printer.setChosenPrinter(chosenPrinter);
            ETQWatcher watcher = new ETQWatcher(Paths.get("C:\\Users\\o-User-DawinAlt\\Downloads"), printer);
            watcher.startWatching();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
