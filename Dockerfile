FROM openjdk:11.0-jre-slim
COPY target/mappy-places-0.0.1-SNAPSHOT.jar /mappy-places.jar
CMD ["java", "-Djava.security-egd=file:/dev/./urandom","-Dserver.port=${PORT}","-jar","/mappy-places.jar"]
