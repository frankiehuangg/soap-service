FROM openjdk:8
EXPOSE 8020

COPY ./target /app

WORKDIR /app 

ENTRYPOINT ["java", "-jar", "soap-service-jar-with-dependencies.jar"]