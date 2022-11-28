#FROM openjdk:8-jdk-alpine
FROM adoptopenjdk/openjdk11:alpine-jre


#===============================
EXPOSE 8080
#ARG JAR_FILE=otc-pub.jar
#ADD ${JAR_FILE} app.jar
WORKDIR /usr/app
COPY messageService-0.0.1-SNAPSHOT.jar /usr/app/

ENTRYPOINT ["java", "-jar" , "/usr/app/messageService-0.0.1-SNAPSHOT.jar"]

