package ETQWatcher;

import java.util.Scanner;
import java.util.prefs.Preferences;

public class ETQWatcherPreferences {

    private Preferences userPreferences;
    private final String CHOSEN_PATH = "chosen_path";
    private final String CHOSEN_PRINTER = "chosen_printer";
    private Scanner userScanner;
    private String userChoiceAfterInit;

    public ETQWatcherPreferences() {
        this.userScanner = new Scanner(System.in);
        this.userPreferences = Preferences.userRoot();
    }

    public String getChosenPrinter() {
        return this.userPreferences.get(this.CHOSEN_PRINTER, null);
    }

    public String getChosenPathToWatch() {
        return this.userPreferences.get(this.CHOSEN_PATH, null);
    }

    void setChosenPrinter(String chosenPrinter) {
        this.userPreferences.put(this.CHOSEN_PRINTER, chosenPrinter);
    }

    void setChosenPathToWatch(String chosenPathToWatch) {
        this.userPreferences.put(this.CHOSEN_PATH, chosenPathToWatch);
    }

    public boolean modifyPreferences() {
        do {
            System.out.println("Tapez 1 à n'importe quel moment quand vous souhaitez modifier ces préférences..");
            this.userChoiceAfterInit = this.userScanner.nextLine();
        } while (!this.userChoiceAfterInit.equals("1"));
        return true;
    }
}
