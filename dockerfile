FROM amazoncorretto:21
WORKDIR /app
ADD ./build/lib/*SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "*SNAPSHOT.jar"]