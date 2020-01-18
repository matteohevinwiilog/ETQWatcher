# ETQWatcher

This java app listen for created pdf files in a specific directory (in the code for now) and automatically prints it to a chosen printer.
To build the app, from the project root, run : mvn clean compile assembly:single. 

The generated jar will be in the target directory and named ETQWatcher-1.0-SNAPSHOT-jar-with-dependencies.jar.

Place it in a folder with a .bat script filled with : 

java -jar ETQWatcher-1.0-SNAPSHOT-jar-with-dependencies.jar

Double click the .bat script to run the program.

STILL TODO : 
 - Run the script as a background task that launches itself at computer start.
 - Pick the default printer everytime or select it a first time then never prompt again.
 - Allow directory to watch selection with the printer selection.
 - Implement a log system.
 - Implement a graphic interface. 
 
REQUIREMENTS :
 - Maven.
 - Java 7 or higher.

DEPENDENCIES : 
 - PdfBox. 
 - PdfBox Tools. 
 - Apache Commons Logging. 
 - Maven assembly plug-in. 
