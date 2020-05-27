DROP TABLE IF EXISTS exchange_rates CASCADE;
CREATE TABLE exchange_rates
(
    from_currency    VARCHAR(60) NOT NULL,
    to_currency      VARCHAR(60) NOT NULL,
    rate   DOUBLE PRECISION NOT NULL,
    PRIMARY KEY(from_currency, to_currency)
);
