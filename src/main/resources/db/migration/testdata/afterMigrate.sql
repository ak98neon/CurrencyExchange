truncate users RESTART IDENTITY CASCADE;
truncate commissions CASCADE;
truncate exchange_rates CASCADE;

insert into users (username, password, email)
values ('admin', '$2a$10$4AlJB9RY1DRHyxD4qm.VSe6plBvxH/zh/XysnQHhzHbWNz0Ai9SqG', 'admin@gmail.com'),
       ('user', '$2a$10$4AlJB9RY1DRHyxD4qm.VSe6plBvxH/zh/XysnQHhzHbWNz0Ai9SqG', 'user@gmail.com'),
       ('ivan', '$2a$10$4AlJB9RY1DRHyxD4qm.VSe6plBvxH/zh/XysnQHhzHbWNz0Ai9SqG', 'ivan@gmail.com');

insert into commissions (from_currency, to_currency, value) values ('EUR', 'UAH', 5);
insert into commissions (from_currency, to_currency, value) values ('UAH', 'EUR', 5);
insert into commissions (from_currency, to_currency, value) values ('USD', 'UAH', 3);
insert into commissions (from_currency, to_currency, value) values ('UAH', 'USD', 3);

insert into exchange_rates (from_currency, to_currency, rate) values ('USD', 'UAH', 27);
insert into exchange_rates (from_currency, to_currency, rate) values ('USD', 'RUB', 70);
insert into exchange_rates (from_currency, to_currency, rate) values ('RUB', 'USD', 0.014);
insert into exchange_rates (from_currency, to_currency, rate) values ('UAH', 'USD', 0.037);
