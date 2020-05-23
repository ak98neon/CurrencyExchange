ARG service_name=currency_exchange
ARG version=1.0.0

FROM openjdk:8

ENV SERVER_PORT 9090
EXPOSE $SERVER_PORT

WORKDIR /app
COPY ./target/currencyexchange-1.0.0.jar /app/target/currencyexchange-1.0.0.jar
COPY ./target/classes/application.yml /app/application.yml

ENTRYPOINT ["java", "-jar", "/app/target/currencyexchange-1.0.0.jar", "--spring.config.location=/app/application.yml"]

LABEL service=${serice_name} image=prod build=${version}
