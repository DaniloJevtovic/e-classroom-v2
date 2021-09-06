--uloge
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');
insert INTO authority (name) VALUES ('ROLE_TEACHER');
insert INTO authority (name) VALUES ('ROLE_STUDENT');
insert INTO authority (name) VALUES ('ROLE_PARENT');

INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES ('admin','admin', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Direktor', 'Skole', 'direktor@gmail.com', true, '2021-04-15 21:58:58');
--profesori
INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date, subjects) VALUES ('teacher', 'profMat', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Profesor', 'Matematike', 'prof_mat@gmail.com', true, '2021-04-15 21:58:58', 'mat, inf');
INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date, subjects) VALUES ('teacher', 'profSrp', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Profesor', 'Srpskog', 'prof_srp@gmail.com', true, '2021-04-15 21:58:58', 'srp');

--ucenici
INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date, student_class_id, st_parent_id) VALUES ('student', 'ucPrviA', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Ucenik Ia', 'Prvog', 'uc1a_prvog@gmail.com', true, '2021-04-15 21:58:58', 1, 8);
INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date, student_class_id, st_parent_id) VALUES ('student', 'ucPrviA2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Ucenik Ia - 2 ', 'Prvog', 'uc1a2_prvog@gmail.com', true, '2021-04-15 21:58:58', 1, 9);

INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date, student_class_id, st_parent_id) VALUES ('student', 'ucPrviB', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Ucenik IB', 'Prvog', 'uc1a_prvogB@gmail.com', true, '2021-04-15 21:58:58', 2, 8);
INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date, student_class_id, st_parent_id) VALUES ('student', 'ucPrviB2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Ucenik IB - 2 ', 'Prvog', 'uc1a2_prvogB@gmail.com', true, '2021-04-15 21:58:58', 2, 10);

--roditelji
INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES ('parent', 'rodPrviAB', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Roditelj uc IA/B', 'Prvog AB', 'rod_prvog@gmail.com', true, '2021-04-15 21:58:58');
INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES ('parent', 'rodPrviA2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Roditelj uc IA2', 'Prvog A2', 'rod_prvog2@gmail.com', true, '2021-04-15 21:58:58');
INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES ('parent', 'rodPrviB', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Roditelj uc IB', 'Prvog B', 'rod_prvog@gmail.com', true, '2021-04-15 21:58:58');

--jos profesora (id: 11-13)
INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date, subjects) VALUES ('teacher', 'profInf', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Profesor', 'Informatike', 'prof_inf@gmail.com', true, '2021-08-25 11:23:18', 'inf, prog, db');
INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date, subjects) VALUES ('teacher', 'profEng', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Profesor', 'Engleskog', 'prof_eng@gmail.com', true, '2021-08-23 19:12:42', 'eng');
INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date, subjects) VALUES ('teacher', 'profBio', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Profesor', 'Biologije', 'prof_bio@gmail.com', true, '2021-08-14 13:12:53', 'bio, zoo');

--dodjela uloga
INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (3, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (4, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (5, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (6, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (7, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (8, 4);
INSERT INTO user_authority (user_id, authority_id) VALUES (9, 4);
INSERT INTO user_authority (user_id, authority_id) VALUES (10, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (11, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (12, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (13, 2);

--razredi -- I, II, III, IV
insert into school_class (name, description) values ('I', 'prvi razred')
insert into school_class (name, description) values ('II', 'drugi razred')
insert into school_class (name, description) values ('III', 'treci razred')
insert into school_class (name, description) values ('IV', 'cetvrti razred')

--odjeljenja IA, IB, IC, IIA, IIB ...
INSERT INTO student_class (name, description, school_class_id) VALUES ('IA', 'prvo A odjeljenje', 1)
INSERT INTO student_class (name, description, school_class_id) VALUES ('IB', 'prvo B odjeljenje', 1)
INSERT INTO student_class (name, description, school_class_id) VALUES ('IC', 'prvo C odjeljenje', 1)

insert into student_class (name, description, school_class_id) values ('IIA', 'drugo A odjeljenje', 2)
insert into student_class (name, description, school_class_id) values ('IIB', 'drugo B odjeljenje', 2)
insert into student_class (name, description, school_class_id) values ('IIC', 'drugo C dojeljenje', 2)

insert into student_class (name, description, school_class_id) values ('IIIA', 'trece C dojeljenje', 3)

--predmeti
INSERT INTO courses (name, description, is_deleted, teacher_id, school_class_id) VALUES ('mat1', 'matematika za I razred', false, 2, 1)
INSERT INTO courses (name, description, is_deleted, teacher_id, school_class_id) VALUES ('srp1', 'srpski jezik za I razred', false, 3, 1)
INSERT INTO courses (name, description, is_deleted, teacher_id, school_class_id) VALUES ('mat2', 'matematika za II razred', false, 2, 2)
INSERT INTO courses (name, description, is_deleted, teacher_id, school_class_id) VALUES ('mat3', 'matematika za III razred', false, 2, 3)
INSERT INTO courses (name, description, is_deleted, teacher_id, school_class_id) VALUES ('inf4', 'informatika za IV razred', false, 11, 4)

--materijali
INSERT INTO materials (name, description, course_id, creation_date) VALUES ('lekcija 1', 'opis lekcije 1', 1, '2021-07-10 13:57:23')
INSERT INTO materials (name, description, course_id, creation_date) VALUES ('lekcija 2', 'opis lekcije 2', 1, '2021-07-10 13:57:23')

--komentari za materijal
INSERT INTO material_comments(comment, author_id, material_id, mc_date) VALUES('kom1 lekcija 1', 2, 1, '2021-07-12 18:57:43')
INSERT INTO material_comments(comment, author_id, material_id, mc_date) VALUES('kom2 lekcija 1', 2, 1, '2021-07-11 11:51:53')

INSERT INTO material_comments(comment, author_id, material_id, mc_date) VALUES('kom1 lekcija 2', 2, 2, '2021-07-12 16:25:26')

--kviz 1 - sa pitanjima i odgovorima
INSERT INTO quizzes (name, instructions, duration, total_points, course_id, quiz_status) VALUES ('kviz 1', 'instrukcije za kviz 1', 15, 7, 1, 1)

INSERT INTO questions (question, points, quiz_id, question_type) VALUES ('pit 1 kv1', 2, 1, 0)
INSERT INTO questions (question, points, quiz_id, question_type) VALUES ('pit 2 kv1', 3, 1, 0)
INSERT INTO questions (question, points, quiz_id, question_type) VALUES ('pit 3 kv1', 2, 1, 0)

INSERT INTO answers (answer, correct, question_id) VALUES ('odg1 pit3', true, 3)
INSERT INTO answers (answer, correct, question_id) VALUES ('odg2 pit3', false, 3)
INSERT INTO answers (answer, correct, question_id) VALUES ('odg3 pit3', true, 3)
INSERT INTO answers (answer, correct, question_id) VALUES ('odg1 pit1', true, 1)
INSERT INTO answers (answer, correct, question_id) VALUES ('odg2 pit1', false, 1)
INSERT INTO answers (answer, correct, question_id) VALUES ('odg1 pit2', true, 2)
INSERT INTO answers (answer, correct, question_id) VALUES ('odg2 pit2', false, 2)
INSERT INTO answers (answer, correct, question_id) VALUES ('odg3 pit2', true, 2)
INSERT INTO answers (answer, correct, question_id) VALUES ('odg4 pit2', false, 2)

--kviz 2 - sa pitanjima i odgovorima
INSERT INTO quizzes (name, instructions, duration, total_points, course_id, quiz_status) VALUES ('kviz 2', 'instrukcije za kviz 2', 30, 10, 1, 1)

INSERT INTO questions (question, points, quiz_id, question_type) VALUES ('pit1 kv2', 2, 2, 0)
INSERT INTO questions (question, points, quiz_id, question_type) VALUES ('pit2 kv2', 3, 2, 0)
INSERT INTO questions (question, points, quiz_id, question_type) VALUES ('pit3 kv2', 5, 2, 0)

INSERT INTO answers (answer, correct, question_id) VALUES ('odg1 pit3', true, 6)
INSERT INTO answers (answer, correct, question_id) VALUES ('odg2 pit3', false, 6)
INSERT INTO answers (answer, correct, question_id) VALUES ('odg3 pit3', true, 6)
INSERT INTO answers (answer, correct, question_id) VALUES ('odg1 pit1', true, 4)
INSERT INTO answers (answer, correct, question_id) VALUES ('odg2 pit1', false, 4)
INSERT INTO answers (answer, correct, question_id) VALUES ('odg1 pit2', true, 5)
INSERT INTO answers (answer, correct, question_id) VALUES ('odg2 pit2', false, 5)
INSERT INTO answers (answer, correct, question_id) VALUES ('odg3 pit2', true, 5)
INSERT INTO answers (answer, correct, question_id) VALUES ('odg4 pit2', false, 5)

--postovi i komentari
INSERT INTO course_posts(post, course_id, author_id, post_date) values ('objava za mat1', 1, 2, '2021-07-10 13:57:23')
INSERT INTO post_comments(comment, post_id, author_id, pc_date) values ('komentar 1. za objavu1 iz mat1', 1, 4, '2021-07-11 19:14:53')
INSERT INTO post_comments(comment, post_id, author_id, pc_date) values ('komentar 2. za objavu1 iz mat1', 1, 2, '2021-07-12 11:46:26')
INSERT INTO post_comments(comment, post_id, author_id, pc_date) values ('komentar 2. za objavu1 iz mat1', 1, 5, '2021-07-13 12:27:13')

INSERT INTO course_posts(post, course_id, author_id, post_date) values ('objava 2 za mat1', 1, 2, '2021-07-11 15:24:13')
INSERT INTO post_comments(comment, post_id, author_id, pc_date) values ('komentar 1. za objavu2 iz mat1', 2, 5, '2021-07-11 15:17:53')
INSERT INTO post_comments(comment, post_id, author_id, pc_date) values ('komentar 2. za objavu2 iz mat1', 2, 7, '2021-07-11 10:51:25')





