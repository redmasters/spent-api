FROM openjdk:17-alpine
COPY build/libs/*.jar /app.jar
WORKDIR /src
COPY . /src
RUN gradle clean build
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar", "build/libs/*.jar"]
