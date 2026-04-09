FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/app-0.0.1-SNAPSHOT.jar
ARG PROP_FILE_PRP=src/main/resources/application-prod.properties
COPY ${JAR_FILE} app.jar
COPY ${PROP_FILE_PRP} application.properties
EXPOSE 9993
ENTRYPOINT ["java","-jar", "app.jar" ,"--spring.config.location=file:/application.properties"]