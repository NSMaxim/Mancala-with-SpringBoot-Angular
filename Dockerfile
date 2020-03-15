FROM maven:3-jdk-8 as build
RUN mkdir mancala
COPY ./pom.xml /mancala
COPY ./mcl-app /mancala/mcl-app
COPY ./mcl-service /mancala/mcl-service
COPY ./mcl-ui /mancala/mcl-ui
WORKDIR /mancala
RUN mvn package

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /mancala/mcl-app/target/mcl-app-0.0.1-SNAPSHOT.jar /app
CMD ["java", "-jar" , "mcl-app-0.0.1-SNAPSHOT.jar"]