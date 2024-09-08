FROM openjdk:21
COPY ./build/libs/audio-insights-service-0.0.1-SNAPSHOT.jar /tmp/app.jar
WORKDIR /tmp
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/tmp/app.jar"]