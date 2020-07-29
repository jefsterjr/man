FROM openjdk:11.0.8-slim-buster

RUN mkdir /usr/app

WORKDIR /file-processor
COPY target/file-processor.jar .

ENTRYPOINT ["java","-jar","file-processor.jar"]
