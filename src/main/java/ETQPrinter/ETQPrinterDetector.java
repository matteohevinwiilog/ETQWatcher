package ETQPrinter;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ETQPrinterDetector {

    private Scanner userInputScan;

    public ETQPrinterDetector() {
        this.userInputScan = new Scanner(System.in);
    }

    private List<PrintService> getAvailablePrinters() {
        return Arrays.asList(PrintServiceLookup.lookupPrintServices(null, null));
    }

    private void listAvailablePrinters() {
        List<PrintService> availablePrinters = this.getAvailablePrinters();
        int index = 0;
        System.out.println(availablePrinters.size() + " imprimantes détéctées.");
        for (PrintService printServiceDetected : availablePrinters) {
            System.out.println((index++) + ". " + printServiceDetected.getName());
        }
    }

    private void displayDefaultPrinter() {
        System.out.println("Imprimante par défaut : " + this.getDefaultPrintService().getName());
    }

    public PrintService detectAndChoosePrinter() {
        this.listAvailablePrinters();
        this.displayDefaultPrinter();
        System.out.println("Choisissez votre imprimante (entrée pour celle par défaut).");
        String chosenInput = this.userInputScan.nextLine();
        return treatUserInput(chosenInput);
    }

    private PrintService getDefaultPrintService() {
        return PrintServiceLookup.lookupDefaultPrintService();
    }

    private PrintService treatUserInput(String userChoice) {
        PrintService chosenPrinter;
        if ("".equals(userChoice)) {
            chosenPrinter = this.getDefaultPrintService();
        } else {
            chosenPrinter = this.getAvailablePrinters().get(Integer.parseInt(userChoice));
        }
        System.out.println("Imprimante choisie : " + chosenPrinter.getName());
        System.out.println("Elle supporte les format suivants : ");
        for (DocFlavor flavor : chosenPrinter.getSupportedDocFlavors()) {
            System.out.println(" - " + flavor.toString());
        }
        return chosenPrinter;
    }

    public PrintService getByName(String printerNameChosen) {
        for (PrintService printService : this.getAvailablePrinters()) {
            if (printService.getName().equals(printerNameChosen)) {
                return printService;
            }
        }
        return null;
    }
}
