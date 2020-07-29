FROM openjdk:11.0.8-slim-buster

WORKDIR /fota
COPY target/fota.jar .
EXPOSE 8400


ENTRYPOINT ["java","-jar","fota.jar"]
