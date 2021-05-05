--uloge
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');
insert INTO authority (name) VALUES ('ROLE_TEACHER');
insert INTO authority (name) VALUES ('ROLE_STUDENT');

INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES ('admin','admin', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Direktor', 'Skole', 'direktor@gmail.com', true, '2021-04-15 21:58:58');
--profesori
INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date, subjects) VALUES ('teacher', 'profMat', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Profesor', 'Matematike', 'prof_mat@gmail.com', true, '2021-04-15 21:58:58', 'mat, inf');
INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date, subjects) VALUES ('teacher', 'profSrp', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Profesor', 'Srpskog', 'prof_srp@gmail.com', true, '2021-04-15 21:58:58', 'srp');
--ucenici
INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date, student_class_id) VALUES ('student', 'ucPrviA', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Ucenik Ia', 'Prvog', 'uc1a_prvog@gmail.com', true, '2021-04-15 21:58:58', 1);
INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date, student_class_id) VALUES ('student', 'ucPrviA2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Ucenik Ia - 2 ', 'Prvog', 'uc1a2_prvog@gmail.com', true, '2021-04-15 21:58:58', 1);

INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date, student_class_id) VALUES ('student', 'ucPrviB', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Ucenik IB', 'Prvog', 'uc1a_prvogB@gmail.com', true, '2021-04-15 21:58:58', 2);
INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date, student_class_id) VALUES ('student', 'ucPrviB2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Ucenik IB - 2 ', 'Prvog', 'uc1a2_prvogB@gmail.com', true, '2021-04-15 21:58:58', 2);

--dodjela uloga
INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (3, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (4, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (5, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (6, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (7, 3);

--razredi -- I, II, III, IV
insert into school_class (name, description) values ('I', 'prvi razred')
insert into school_class (name, description) values ('II', 'drugi razred')
insert into school_class (name, description) values ('III', 'treci razred')

--odjeljenja IA, IB, IC, IIA, IIB ...
INSERT INTO student_class (name, description, school_class_id) VALUES ('IA', 'prvo A odjeljenje', 1)
INSERT INTO student_class (name, description, school_class_id) VALUES ('IB', 'prvo B odjeljenje', 1)

insert into student_class (name, description, school_class_id) values ('IIA', 'drugo A odjeljenje', 2)
insert into student_class (name, description, school_class_id) values ('IIB', 'drugo B odjeljenje', 2)
insert into student_class (name, description, school_class_id) values ('IIC', 'drugo C dojeljenje', 2)

insert into student_class (name, description, school_class_id) values ('IIIA', 'trece C dojeljenje', 3)

--predmeti
INSERT INTO courses (name, description, is_deleted, teacher_id, school_class_id) VALUES ('mat1', 'opis predmeta', false, 2, 1)
INSERT INTO courses (name, description, is_deleted, teacher_id, school_class_id) VALUES ('srp1', 'srpski jezik za 1 razred', false, 3, 1)
INSERT INTO courses (name, description, is_deleted, teacher_id, school_class_id) VALUES ('mat2', 'matematika za II razred', false, 2, 2)
INSERT INTO courses (name, description, is_deleted, teacher_id, school_class_id) VALUES ('mat3', 'matematika za III razred', false, 2, 3)

