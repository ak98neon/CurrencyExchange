DROP TABLE IF EXISTS exchange_rates CASCADE;
CREATE TABLE exchange_rates
(
    id      BIGSERIAL PRIMARY KEY,
    from_currency    VARCHAR(60) NOT NULL,
    to_currency      VARCHAR(60) NOT NULL,
    rate   DOUBLE PRECISION NOT NULL
);
