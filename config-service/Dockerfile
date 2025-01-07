FROM openjdk:17-ea-11-jdk-slim
ENV http_proxy=http://10.10.10.213:3128
ENV https_proxy=http://10.10.10.213:3128
VOLUME /tmp
COPY apiEncryptionKey.jks apiEncryptionKey.jks
COPY target/config-service-1.0.jar ConfigService.jar
ENTRYPOINT ["java","-jar","ConfigService.jar"]
