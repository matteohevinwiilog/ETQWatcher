package ETQWatcher;

import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ETQPathChooser {

    private Scanner userInputScanner;
    private String pathToWatch;

    public ETQPathChooser() {
        this.userInputScanner = new Scanner(System.in);
    }

    public Path choosePathToWatch() {
        do {
            System.out.println("Choisissez le dossier à monitorer. (Il doit exister.)");
            this.pathToWatch = this.userInputScanner.nextLine();
        } while (!this.isValidPath(this.pathToWatch));
        return Paths.get(this.pathToWatch);
    }

    public boolean isValidPath(String pathToTest) {
        try {
            Paths.get(pathToTest);
        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }
        return true;
    }
}
