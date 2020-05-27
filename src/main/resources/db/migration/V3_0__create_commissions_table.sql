drop table IF EXISTS commissions CASCADE;
create TABLE commissions
(
    from_currency    VARCHAR(60) NOT NULL,
    to_currency      VARCHAR(60) NOT NULL,
    value            DECIMAL(19, 4)  NOT NULL,
    PRIMARY KEY(from_currency, to_currency)
);
