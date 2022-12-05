#FROM openjdk:8-jdk-alpine
FROM adoptopenjdk/openjdk8:alpine-jre

#ARG JAR_FILE= /home/runner/work/messageService/messageService/target/messageService-0.0.1-SNAPSHOT.jar

#===============================
EXPOSE 8080
ADD target/messageService-0.0.1-SNAPSHOT.jar messageService-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} messageService-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar" , "/messageService-0.0.1-SNAPSHOT.jar"]

