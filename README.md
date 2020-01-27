# ETQWatcher

This java command line app listen for created pdf files in a specific chosen directory and automatically prints it to a chosen printer.
To build the app, from the project root, run : mvn clean compile assembly:single. 

The generated jar will be in the target directory and named ETQWatcher-1.0-SNAPSHOT-jar-with-dependencies.jar.

Place it in a folder with the three .bats.

Double click the .bat script to run the program.

ETQExit : 
    - stops the watching of a directory if the app is watching.
ETQLaunch : 
    - kills the previous monitoring task and launches the watching with the window to edit the preferences. 
    - To monitor in background with the new preferences, you must relaunch ETQWatch.
ETQWatch : 
    - Launches the monitoring task in background, with the preferences set by the ETQLaunch.
    

Feel free to schedule the ETQWatch.bat at computer start. 

STILL TODO : 
 - Run the script as a background task that launches itself at computer start. (1)
 - Implement a log system. (2)
 - Implement tests. (3)
 - Setup maven testing workflow on git actions. (4)
 
REQUIREMENTS :
 - Maven.
 - Java 7 or higher.

DEPENDENCIES : 
 - PdfBox. 
 - PdfBox Tools. 
 - Apache Commons Logging. 
 - Maven assembly plug-in. 
 - Maven compiler plug-in. 
