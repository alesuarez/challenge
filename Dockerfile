FROM maven:3.6.3 AS maven
LABEL MAINTAINER="sas.tuc@gmail.com"

WORKDIR /usr/src/app
COPY . /usr/src/app
# Compile and package the application to an executable JAR
RUN mvn package

# For Java 11,
FROM openjdk:11

ARG JAR_FILE=spring-boot-api.jar

WORKDIR /opt/app

# Copy the spring-boot-api-tutorial.jar from the maven stage to the /opt/app directory of the current stage.
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/

ENTRYPOINT ["java","-jar","spring-boot-api.jar"]