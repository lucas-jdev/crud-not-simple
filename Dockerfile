FROM openjdk:21-slim

COPY /target/project-http-0.0.1-SNAPSHOT.jar /spring-boot-app/spring-boot-app.jar

WORKDIR /spring-boot-app

EXPOSE 8080

VOLUME /spring-boot-app

RUN chmod +x spring-boot-app.jar

CMD ["java", "-jar", "spring-boot-app.jar"]

# chatgpt:
#FROM openjdk:21-jdk-slim
#WORKDIR /app
#COPY target/project-http.0.0.1-SNAPSHOT.jar .
#CMD ["java", "-jar", "sua-aplicacao.jar"]


# -----------------------------------

#RUN apt-get update && \
#apt-get install -y openjdk-21-jdk && \
#apt-get install -y ant && \
#apt-get clean;

#FROM maven:3.9.9-sapmachine-21 as build
#RUN mkdir -p /usr/src/app
#WORKDIR /usr/src/app
#ADD . /usr/src/app
#RUN mvn package
#
#FROM openjdk:21-slim
#RUN mkdir -p /usr/src/app
#WORKDIR /usr/src/app
#COPY --from=build /usr/src/app/target/rapaz-0.0.1-SNAPSHOT.jar app.jar
#CMD ["java", "-jar", "app.jar"]

# -----------------------------------------------------
#WORKDIR /app
#
#COPY pom.xml /app
#
#RUN apt-get update && apt-get install -y maven
#
#RUN mvn dependency:go-offline
#
#COPY . /app
#
#EXPOSE 8080
#
#ENTRYPOINT ["mvn", "spring-boot:run", "-Dspring-boot.devtools.remote.secret=secret", "-Dspring.devtools.restart.enabled=true", "-Dspring-boot.devtools.remote.connect-url=http://localhost:8080"]