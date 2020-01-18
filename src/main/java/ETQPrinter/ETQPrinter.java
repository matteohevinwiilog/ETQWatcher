package ETQPrinter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import javax.print.PrintService;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ETQPrinter {

    private PrintService chosenPrinter;
    private Path pathToPrint;
    private PDDocument docChosen;
    private PDFPageable docChosenToPDF;
    private PrinterJob printerJob;

    public void setChosenPrinter(PrintService chosenPrinter) {
        this.chosenPrinter = chosenPrinter;
    }

    public Path getPathToPrint() {
        return pathToPrint;
    }

    public void setPathToPrint(Path pathToPrint) throws IOException, PrinterException {
        this.pathToPrint = pathToPrint;
        this.docChosen = PDDocument.load(new File(this.pathToPrint.toString()));
        this.printerJob = PrinterJob.getPrinterJob();
        this.printerJob.setPrintService(this.chosenPrinter);
        this.docChosenToPDF = new PDFPageable(this.docChosen);
        this.printerJob.setPageable(this.docChosenToPDF);

    }
    public void startPrinting() throws PrinterException, IOException {
        this.printerJob.print();
        System.out.println("Document " + this.pathToPrint.toString() + " imprimé avec succès.");
        this.docChosen.close();
    }
}
