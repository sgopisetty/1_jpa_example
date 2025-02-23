set cp=.;sqljdbc_12.8\enu\jars\mssql-jdbc-12.8.1.jre11.jar;lib\*;
set dllPath=sqljdbc_12.8\enu\auth\x64
javac -cp %cp% hello/*.java
java -cp %cp% hello.JpaExample