#FROM openjdk:8-jdk-alpine
FROM adoptopenjdk/openjdk11:alpine-jre
#==============================
RUN apk --no-cache add curl
ENV FILEBEAT_VERSION=7.13.0

COPY filebeat.yml /

#RUN apk add libc6-compat
#RUN apk add --update-cache curl bash libc6-compat && \
RUn  rm -rf /var/cache/apk/* && \
    curl https://artifacts.elastic.co/downloads/beats/filebeat/filebeat-${FILEBEAT_VERSION}-linux-x86_64.tar.gz -o /filebeat.tar.gz && \
    tar xzvf filebeat.tar.gz && \
    rm filebeat.tar.gz && \
    mv filebeat-${FILEBEAT_VERSION}-linux-x86_64 filebeat && \
    cd filebeat && \
    cp filebeat /usr/bin && \
    rm -rf /filebeat/filebeat.yml && \
    cp /filebeat.yml /filebeat/ && \
    ls -ltr /filebeat && \
    cat /filebeat/filebeat.yml

VOLUME /filebeat/data

WORKDIR /filebeat/

#===============================
EXPOSE 8080
#ARG JAR_FILE=otc-pub.jar
#ADD ${JAR_FILE} app.jar
COPY otc-pub.jar /
#ENTRYPOINT ["java","-jar","/otc-pub.jar"]
#ENTRYPOINT ["/bin/sh", "-c" , "./filebeat -e -c filebeat.yml & sleep 20 && java -Xms2048m -Xmx8000m -jar /otc-pub.jar"]
ENTRYPOINT ["/bin/sh", "-c" , "./filebeat -e -c filebeat.yml & sleep 20 && java -Xms2048m -Xmx4096m -jar /otc-pub.jar"]

