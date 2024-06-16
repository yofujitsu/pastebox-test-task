FROM amazoncorretto:21
MAINTAINER Tsvetkov Alexander

COPY pastebox-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]