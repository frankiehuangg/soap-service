FROM openjdk:8
EXPOSE 8020

WORKDIR app 

COPY ./target .

ENTRYPOINT ["java", "-jar", "service-soap-jar-with-dependencies.jar"]