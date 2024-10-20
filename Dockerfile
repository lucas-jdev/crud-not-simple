FROM maven:3.9.9-sapmachine-21 as build
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
ADD . /usr/src/app
RUN mvn package

FROM openjdk:21-slim
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY --from=build /usr/src/app/target/project-http-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]