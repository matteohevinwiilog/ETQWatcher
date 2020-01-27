FOR /f "tokens=1,3" %%a IN ('jps -lv') DO (
    if "%%b" == "-Dname=ETQWatcher" (
        taskkill /F /pid %%a
    )
)