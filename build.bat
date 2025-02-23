cls 
del hello\*.class
set cp=.;sqljdbc_12.8\enu\jars\mssql-jdbc-12.8.1.jre11.jar;lib\*;lib\hibernate-entitymanager-5.4.30.Final.jar;
set dllPath=sqljdbc_12.8\enu\auth\x64
javac -cp %cp% hello/*.java
REM java -Djava.library.path=%dllPath% -cp %cp% hello.JpaExample
java -Djava.library.path=%dllPath% -Djava.util.logging.config.file=logging.properties -cp %cp% hello.JpaExample

