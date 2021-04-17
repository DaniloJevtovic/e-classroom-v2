--uloge
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');
insert INTO authority (name) VALUES ('ROLE_TEACHER');
insert INTO authority (name) VALUES ('ROLE_STUDENT');

INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES ('admin','direktor', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Direktor', 'Skole', 'direktor@gmail.com', true, '2021-04-15 21:58:58');
--profesori
INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES ('teacher', 'profMat', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Profesor', 'Matematike', 'prof_mat@gmail.com', true, '2021-04-15 21:58:58');
INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES ('teacher', 'profSrp', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Profesor', 'Srpskog', 'prof_srp@gmail.com', true, '2021-04-15 21:58:58');
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

--odjeljenja
INSERT INTO student_class (name, description) VALUES ('prvo A odjeljenje', 'opis')
INSERT INTO student_class (name, description) VALUES ('prbo B odjeljenje', 'opis prvo b')

--predmeti
INSERT INTO courses (name, description, teacher_id) VALUES ('mat1', 'opis predmeta', 2)
INSERT INTO courses (name, description, teacher_id) VALUES ('srp1', 'srpski jezik za 1 razred', 3)

