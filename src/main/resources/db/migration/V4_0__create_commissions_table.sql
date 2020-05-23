DROP TABLE IF EXISTS commissions CASCADE;
CREATE TABLE commissions
(
    id      BIGSERIAL PRIMARY KEY,
    from_currency    VARCHAR(60) NOT NULL,
    to_currency      VARCHAR(60) NOT NULL,
    value   BIGINT NOT NULL
);
