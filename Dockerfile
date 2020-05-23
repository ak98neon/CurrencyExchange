ARG service_name=currency_exchange
ARG version=1.0.0

FROM openjdk:8

ENV SERVER_PORT 8080
EXPOSE $SERVER_PORT

WORKDIR /app
COPY ./target/currencyexchange-1.0.0.jar /app/target/currencyexchange-1.0.0.jar
COPY ./target/classes/application-docker.yml /app/application-docker.yml

ENTRYPOINT ["java", "-jar", "/app/target/currencyexchange-1.0.0.jar", "--spring.config.location=/app/application-docker.yml", "-Dspring.profiles.active=docker"]

LABEL service=${serice_name} image=prod build=${version}
