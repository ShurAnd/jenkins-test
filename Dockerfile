FROM amazoncorretto:21
WORKDIR /app
ADD ./build/libs/*SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "back2javawebapp-0.0.1-SNAPSHOT.jar"]