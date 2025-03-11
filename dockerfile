FROM amazoncorretto:21
WORKDIR /app
ADD ./build/libs/*SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "*SNAPSHOT.jar"]