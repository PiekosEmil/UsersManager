INSERT INTO user(username, password)
VALUES ('RalphDemolka', '{bcrypt}$2a$12$y7v0CrAsIxK4K3wHBJrx8e2Opm6R9UyKDBqWHC61Ktc/BuygfJ2w2'), -- prostehaslo
       ('Bobber', '{bcrypt}$2a$12$8LlJA4PEy7Eb.0H1OkmXmuJ6hzdIlFtMoDKfbysfooQuSGF2SVtD6'), -- trudniejszehaslo
       ('TestowyUser', '{bcrypt}$2a$12$OFKFoJrQQv0GFA9gL7v4HOpp59N5aCB2/cZoxHVEDZrtALxsn.r6.'); -- testowehaslo

INSERT INTO user_role(user_id, role)
VALUES (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER'),
       (3, 'ROLE_USER');