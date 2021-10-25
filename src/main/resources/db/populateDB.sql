DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100000, '2021-10-24 10:00', 'English tea', 100),
       (100000, '2021-10-25 11:00', 'English breakfast', 500),
       (100001, '2021-10-26 10:00', 'Русский мерзкий чай с молоком', 150),
       (100001, '2021-10-26 11:00', 'Русские блины', 550),
       (100001, '2021-10-26 12:00', 'Борщ', 700),
       (100001, '2021-10-26 23:59', 'Пельмени', 1500),
       (100001, '2021-10-27 10:00', 'Русский человеческий цикорий с молоком', 150),
       (100001, '2021-10-27 11:00', 'Русские блины', 550);
