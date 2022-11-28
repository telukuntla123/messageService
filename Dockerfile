#FROM openjdk:8-jdk-alpine
FROM adoptopenjdk/openjdk11:alpine-jre


#===============================
EXPOSE 8080
#ARG JAR_FILE=otc-pub.jar
#ADD ${JAR_FILE} app.jar
COPY messageService-0.0.1-SNAPSHOT.jar /
#ENTRYPOINT ["java","-jar","/otc-pub.jar"]
#ENTRYPOINT ["/bin/sh", "-c" , "./filebeat -e -c filebeat.yml & sleep 20 && java -Xms2048m -Xmx8000m -jar /otc-pub.jar"]
ENTRYPOINT ["java", "-jar" , "messageService-0.0.1-SNAPSHOT.jar"]

