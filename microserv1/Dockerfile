FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./build/libs/microserv1-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]