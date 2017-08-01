# noinspection SqlNoDataSourceInspectionForFile
INSERT INTO user (id, user_name, password, first_name, last_name, email, enabled, created_on, updated_on, contact, login_mode, state) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, now(), now(), '1234567890', 0, 'CAN');
INSERT INTO user (id, user_name, password, first_name, last_name, email, enabled, created_on, updated_on, contact, login_mode, state) VALUES (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', 1, now(), now(), '12345671131', 0, 'CAN');
INSERT INTO user (id, user_name, password, first_name, last_name, email, enabled, created_on, updated_on, contact, login_mode, state) VALUES (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0, now(), now(), '1234564334', 0, 'CAN');
INSERT INTO user (id, user_name, password, first_name, last_name, email, enabled, created_on, updated_on, contact, login_mode, state) VALUES (4,'user4','$2a$11$BoHHQRZYJaEI0KGbj7kOIe8QrLnEJey0FzL3aHTc5j4CRWGh.Rya102','User','Four','user4@hotmail.com',1, now(), now(),'878656785',1,'CAN');
INSERT INTO user (id, user_name, password, first_name, last_name, email, enabled, created_on, updated_on, contact, login_mode, state) VALUES (5,'user5','$2a$11$BoHHQRZYJaEI0KGbj7kOIe8QrLnEJey0FzL3aHTc5j4CRWGh.Rya103','User','five','user5@gmail.com',1, now(), now(),'8786567860',2,'CALGARY');
INSERT INTO user (id, user_name, password, first_name, last_name, email, enabled, created_on, updated_on, contact, login_mode, state) VALUES (6,'user6','$2a$11$BoHHQRZYJaEI0KGbj7kOIe8QrLnEJey0FzL3aHTc5j4CRWGh.Rya104','User','six','user6@yahoo.co.in',1, now(), now(),'8786567861',3,'EDMONTON');
INSERT INTO user (id, user_name, password, first_name, last_name, email, enabled, created_on, updated_on, contact, login_mode, state) VALUES (7,'user7','$2a$11$BoHHQRZYJaEI0KGbj7kOIe8QrLnEJey0FzL3aHTc5j4CRWGh.Rya105','User','seven','user7@hotmail.com',1, now(), now(),'8786567862',1,'MONTREAL');
INSERT INTO user (id, user_name, password, first_name, last_name, email, enabled, created_on, updated_on, contact, login_mode, state) VALUES (8,'user8','$2a$11$BoHHQRZYJaEI0KGbj7kOIe8QrLnEJey0FzL3aHTc5j4CRWGh.Rya106','User','eight','user8@gmail.com',1, now(), now(),'8786567863',2,'SASKATOON');
INSERT INTO user (id, user_name, password, first_name, last_name, email, enabled, created_on, updated_on, contact, login_mode, state) VALUES (9,'user9','$2a$11$BoHHQRZYJaEI0KGbj7kOIe8QrLnEJey0FzL3aHTc5j4CRWGh.Rya107','User','nine','user9@yahoo.co.in',1, now(), now(),'8786567864',3,'KINGSTON');
INSERT INTO user (id, user_name, password, first_name, last_name, email, enabled, created_on, updated_on, contact, login_mode, state) VALUES (10,'user10','$2a$11$BoHHQRZYJaEI0KGbj7kOIe8QrLnEJey0FzL3aHTc5j4CRWGh.Rya108','User','ten','user10@hotmail.com',1, now(), now(),'8786567865',1,'KITCHENER');

INSERT INTO authority (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO authority (id, name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_authority VALUES (1, 2);
INSERT INTO user_authority VALUES (2, 1);
INSERT INTO user_authority VALUES (3, 1);
insert into user_authority values (4,1);
insert into user_authority values (5,1);
insert into user_authority values (6,1);
insert into user_authority values (7,1);
insert into user_authority values (8,1);
insert into user_authority values (9,1);
insert into user_authority values (10,1);
