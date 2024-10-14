INSERT INTO user(username, password, first_name, last_name, address, email, phone_number)
VALUES ('RalphDemolka', '{noop}prostehaslo', 'Jacek', 'Czekaj', 'London Rd, London, Great Britain', 'jaca@wp.pl', '+48-222-222-222'),
       ('Bobber', '{noop}trudnehaslo', 'John', 'Smith', 'Sulechów, ul. Jana Pawła II 53/19', 'john@wp.pl', '+48-333-333-333'),
       ('TestowyUser', '{noop}testowehaslo', 'Alex', 'Root','Madrid', 'root@wp.pl', '+48-444-444-444');

INSERT INTO user_role(user_id, role)
VALUES (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER'),
       (3, 'ROLE_USER');