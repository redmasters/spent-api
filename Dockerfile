FROM openjdk:17-alpine
WORKDIR /usr/app
COPY . /build/libs/spent-1.9.0.jar /usr/app/spent-1.9.0.jar/
RUN ./gradlew bootJar
ENTRYPOINT exec java -jar spent-1.9.0.jar
EXPOSE 8080
