INSERT INTO "public".users (id, name, email, active, password, address, phone) VALUES (-1, 'admin', 'admin@restaurant.hu', true, '$2a$10$7z8ZcKhYICuyb5rkKhqo7uUh4je3HQocio0f5onZdugR0QTrxHDLi', '4028 Debrecen, Kassai ut 26.', '06 90 666 6969');
INSERT INTO "public".users (id, name, email, active, password, address, phone) VALUES (-2, 'owner', 'threemusketeers.restaurant@gmail.com', true, '$2a$08$MMXBQeDpYUkQwtsWCvi5pO5ot8jNm.YgfvT9IotlGsu9RRCSD7Lcm', '4028 Debrecen, Kassai ut 26.', '06 90 666 9696');

INSERT INTO "public".roles (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO "public".roles (id, name) VALUES (2, 'ROLE_ADMIN');
INSERT INTO "public".roles (id, name) VALUES (3, 'ROLE_OWNER');

INSERT INTO "public".users_roles (userentity_id, roles_id) VALUES (-1, 1);
INSERT INTO "public".users_roles (userentity_id, roles_id) VALUES (-1, 2);
INSERT INTO "public".users_roles (userentity_id, roles_id) VALUES (-2, 1);
INSERT INTO "public".users_roles (userentity_id, roles_id) VALUES (-2, 3);

INSERT INTO "public".foods (id, name, price) VALUES (nextval('hibernate_sequence'), 'Bableves', 1000);
INSERT INTO "public".foods (id, name, price) VALUES (nextval('hibernate_sequence'), 'Spagetti', 500);
INSERT INTO "public".foods (id, name, price) VALUES (nextval('hibernate_sequence'), 'Pizza', 800);
INSERT INTO "public".foods (id, name, price) VALUES (nextval('hibernate_sequence'), 'Sargarepa kremleves', 1200);
INSERT INTO "public".foods (id, name, price) VALUES (nextval('hibernate_sequence'), 'Valami szutymak naranccsal', 1450);
INSERT INTO "public".foods (id, name, price) VALUES (nextval('hibernate_sequence'), 'Fogalmam sincs', 1750);

INSERT INTO "public".drinks (id, name, price) VALUES (nextval('hibernate_sequence'), 'Bor', 750);
INSERT INTO "public".drinks (id, name, price) VALUES (nextval('hibernate_sequence'), 'Tea', 450);
INSERT INTO "public".drinks (id, name, price) VALUES (nextval('hibernate_sequence'), 'Vodka', 120);

INSERT INTO "public".tables (id, number, reserved) VALUES (1, 1, FALSE );
INSERT INTO "public".tables (id, number, reserved) VALUES (2, 2, FALSE );
INSERT INTO "public".tables (id, number, reserved) VALUES (3, 3, FALSE );

INSERT INTO "public".users (id, name, email, active, password, address, phone) VALUES (-3, 'kappa', 'bgabi90@gmail.com', true, '$2a$06$ubhBq03QbEgkmrKCo1WuZ.BFYI0/6f8v7EypJFe2B2gi46QqodXLW', '4028 Debrecen, Kassai ut 26.', '06 90 666 9696');
INSERT INTO "public".users_roles (userentity_id, roles_id) VALUES (-3, 1);