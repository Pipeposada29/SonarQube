FROM openjdk:11
LABEL MANTAINER=oursofka@sofka.com.co

VOLUME /tmp
WORKDIR /app

ADD applications/app-service/build/libs/applications-app-service-*.jar .
RUN mv applications-app-service-*.jar app.jar

RUN mkdir config
ADD applications/app-service/build/resources/main/application.yaml config/application.yml

EXPOSE 5000:8080

ENTRYPOINT ["java", "-Duser.timezone=UTC", "-jar", "/app/app.jar"]
