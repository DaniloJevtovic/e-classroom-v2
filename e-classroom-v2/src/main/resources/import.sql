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
INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date, student_class_id) VALUES ('student', 'ucPrviA2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Ucenik Ia - 2 ', 'Prvog', 'uc1a2_prvog@gmail.com', true, '2021-04-15 21:58:58', 1);

INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date, student_class_id) VALUES ('student', 'ucPrviB', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Ucenik IB', 'Prvog', 'uc1a_prvogB@gmail.com', true, '2021-04-15 21:58:58', 2);
INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date, student_class_id) VALUES ('student', 'ucPrviB2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Ucenik IB - 2 ', 'Prvog', 'uc1a2_prvogB@gmail.com', true, '2021-04-15 21:58:58', 2);

--roditelji
INSERT INTO users (role, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES ('parent', 'rodPrviA', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Roditelj uc IA', 'Prvog A', 'rod_prvog@gmail.com', true, '2021-04-15 21:58:58');

--dodjela uloga
INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (3, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (4, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (5, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (6, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (7, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (8, 4);

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

--materijali
INSERT INTO materials (name, description, course_id) VALUES ('lekcija 1', 'opis lekcije 1', 1)
INSERT INTO materials (name, description, course_id) VALUES ('lekcija 2', 'opis lekcije 2', 1)

--komentari za materijal
INSERT INTO material_comments(comment, author_id, material_id) VALUES('kom1 lekcija 1', 2, 1)
INSERT INTO material_comments(comment, author_id, material_id) VALUES('kom2 lekcija 1', 2, 1)

INSERT INTO material_comments(comment, author_id, material_id) VALUES('kom1 lekcija 2', 2, 2)

--kviz 1 - sa pitanjima i odgovorima
INSERT INTO quizzes (name, instructions, duration, course_id) VALUES ('kviz 1', 'instrukcije za kviz 1', 15, 1)

INSERT INTO questions (question, points, quiz_id) VALUES ('pit 1 kv1', 2, 1)
INSERT INTO questions (question, points, quiz_id) VALUES ('pit 2 kv1', 3, 1)
INSERT INTO questions (question, points, quiz_id) VALUES ('pit 3 kv1', 2, 1)

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
INSERT INTO quizzes (name, instructions, duration, course_id) VALUES ('kviz 2', 'instrukcije za kviz 2', 30, 1)

INSERT INTO questions (question, points, quiz_id) VALUES ('pit1 kv2', 2, 2)
INSERT INTO questions (question, points, quiz_id) VALUES ('pit2 kv2', 3, 2)
INSERT INTO questions (question, points, quiz_id) VALUES ('pit3 kv2', 5, 2)

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
INSERT INTO course_posts(post, course_id, author_id) values ('objava za mat1', 1, 2)
INSERT INTO post_comments(comment, post_id, author_id) values ('komentar 1. za objavu1 iz mat1', 1, 4)
INSERT INTO post_comments(comment, post_id, author_id) values ('komentar 2. za objavu1 iz mat1', 1, 2)
INSERT INTO post_comments(comment, post_id, author_id) values ('komentar 2. za objavu1 iz mat1', 1, 5)

INSERT INTO course_posts(post, course_id, author_id) values ('objava 2 za mat1', 1, 2)
INSERT INTO post_comments(comment, post_id, author_id) values ('komentar 1. za objavu2 iz mat1', 2, 5)
INSERT INTO post_comments(comment, post_id, author_id) values ('komentar 2. za objavu2 iz mat1', 2, 7)





