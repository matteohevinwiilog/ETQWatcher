# ETQWatcher

This java app listen for created pdf files in a specific chosen directory and automatically prints it to a chosen printer.
To build the app, from the project root, run : mvn clean compile assembly:single. 

The generated jar will be in the target directory and named ETQWatcher-1.0-SNAPSHOT-jar-with-dependencies.jar.

Place it in a folder with a .bat script filled with : 

java -jar ETQWatcher-1.0-SNAPSHOT-jar-with-dependencies.jar

Double click the .bat script to run the program.

STILL TODO : 
 - Run the script as a background task that launches itself at computer start. (2)
 - Implement a log system. (3)
 - Implement a graphic interface. (1)
 - Implement tests. (3)
 - Setup maven testing workflow on git actions. (5)
 
REQUIREMENTS :
 - Maven.
 - Java 7 or higher.

DEPENDENCIES : 
 - PdfBox. 
 - PdfBox Tools. 
 - Apache Commons Logging. 
 - Maven assembly plug-in. 
 - Maven compiler plug-in. 
