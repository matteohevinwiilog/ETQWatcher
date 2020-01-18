package ETQWatcher;

import ETQPrinter.ETQPrinter;

import java.util.concurrent.Callable;

public class ETQHandlerCallable implements Callable<Void> {
    private ETQHandlerTypes etqHandlerType;
    private ETQPrinter printer;

    void setEtqHandlerType(ETQHandlerTypes etqHandlerType) {
        this.etqHandlerType = etqHandlerType;
    }

    void setPrinter(ETQPrinter printer) {
        this.printer = printer;
    }

    @Override
    public Void call() throws Exception {
        switch (this.etqHandlerType) {
            case CREATE:
                this.printer.startPrinting();
                System.out.println("Created a file " + this.printer.getPathToPrint().toString());
                break;
            case MODIFY:
                System.out.println("Modified a file " + this.printer.getPathToPrint().toString());
                break;
            case DELETE:
                System.out.println("Deleted a file " + this.printer.getPathToPrint().toString());
                break;
        }
        return null;
    }
}
