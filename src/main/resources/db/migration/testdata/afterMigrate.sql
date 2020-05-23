TRUNCATE users RESTART IDENTITY CASCADE;
TRUNCATE role_users CASCADE;
TRUNCATE role CASCADE;

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
