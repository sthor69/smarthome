mvn package
scp -i ~/.ssh/id_rsa target/Smarthome-0.0.1-SNAPSHOT.war pi@172.20.1.147:~/apache-tomcat-8.0.36/webapps/

