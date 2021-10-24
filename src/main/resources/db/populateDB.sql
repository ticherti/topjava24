DELETE FROM user_roles;
DELETE from user_meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO user_meals (user_id,  date_time, description, calories)
VALUES (100000, '2021-10-24 15:56:06.309896', 'English tea', 100),
       (100000, '2021-10-24 16:56:06.309897', 'English breakfast', 500),
       (100001, '2021-10-24 17:56:06.309896', 'Русский мерзкий чай с молоком', 150),
       (100001, '2021-10-24 18:56:06.309898', 'Русские блины', 550);
