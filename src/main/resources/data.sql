INSERT INTO user(username, password, first_name, last_name, email, phone_number)
VALUES ('RalphDemolka', '{noop}prostehaslo', 'Jacek', 'Czekaj', 'jaca@wp.pl', '+48-222-222-222'),
       ('Bobber', '{noop}trudnehaslo', 'John', 'Smith', 'john@wp.pl', '+48-333-333-333'),
       ('TestowyUser', '{noop}testowehaslo', 'Alex', 'Root', 'root@wp.pl', '+48-444-444-444');

INSERT INTO user_role(user_id, role)
VALUES (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER'),
       (3, 'ROLE_USER');