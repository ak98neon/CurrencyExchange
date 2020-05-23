TRUNCATE users RESTART IDENTITY CASCADE;
TRUNCATE role_users CASCADE;
TRUNCATE role CASCADE;
TRUNCATE commissions CASCADE;
TRUNCATE exchange_rates CASCADE;

INSERT INTO role (name)
VALUES ('ROLE_ADMIN'), ('ROLE_USER');

INSERT INTO users (username, password, email)
VALUES ('admin', '$2a$10$4AlJB9RY1DRHyxD4qm.VSe6plBvxH/zh/XysnQHhzHbWNz0Ai9SqG', 'admin@gmail.com'),
       ('user', '$2a$10$4AlJB9RY1DRHyxD4qm.VSe6plBvxH/zh/XysnQHhzHbWNz0Ai9SqG', 'user@gmail.com'),
       ('ivan', '$2a$10$4AlJB9RY1DRHyxD4qm.VSe6plBvxH/zh/XysnQHhzHbWNz0Ai9SqG', 'ivan@gmail.com');

INSERT INTO role_users (role_id, user_id)
        (SELECT id, (SELECT id FROM users WHERE username = 'admin') FROM role WHERE name = 'ROLE_ADMIN');
INSERT INTO role_users (role_id, user_id)
        (SELECT id, (SELECT id FROM users WHERE username = 'admin') FROM role WHERE name = 'ROLE_USER');

INSERT INTO commissions (id, from_currency, to_currency, value) VALUES (1, 'EUR', 'UAH', 27);
INSERT INTO commissions (id, from_currency, to_currency, value) VALUES (2, 'USD', 'UAH', 3);
INSERT INTO commissions (id, from_currency, to_currency, value) VALUES (3, 'UAH', 'USD', 3);

INSERT INTO exchange_rates (id, from_currency, to_currency, rate) VALUES (1, 'USD', 'UAH', 27);
INSERT INTO exchange_rates (id, from_currency, to_currency, rate) VALUES (3, 'USD', 'RUB', 70);
INSERT INTO exchange_rates (id, from_currency, to_currency, rate) VALUES (4, 'RUB', 'USD', 0.014);
INSERT INTO exchange_rates (id, from_currency, to_currency, rate) VALUES (5, 'UAH', 'USD', 0.037);
