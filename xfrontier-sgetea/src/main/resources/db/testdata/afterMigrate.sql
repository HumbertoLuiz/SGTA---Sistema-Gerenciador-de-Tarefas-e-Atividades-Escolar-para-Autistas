SET foreign_key_checks = 0;


lock tables `course write, `discipline` write;  

SET SQL_SAFE_UPDATES=0;

DELETE FROM `course`;
DELETE FROM `discipline`;


SET SQL_SAFE_UPDATES=1;

SET foreign_key_checks = 1;

ALTER TABLE `course` auto_increment = 1;
ALTER TABLE `discipline` auto_increment = 1;




INSERT INTO `course`(`name`, `description`, `class_id`, `student_id`) VALUES 
('Engenharia em Aquicultura', null, 1, 1),
('Licenciatura em Física', null, 1, 1),
('Tecnologia em Gastronomia', null, 1, 1),
('Tecnologia em Análise e Desenvolvimento de Sistemas', null, 1, 1);

INSERT INTO `discipline`(`name`, `description`, `student_id`, `task_id`) VALUES 
('Química I', null, 1, 1),
('Física I', null, 1, 1),
('Matemática', null, 1, 1),
('Metodologia de Software', null, 1, 1);

INSERT INTO students (enrollment_number, enrollment_date, course_status, average_score, academic_year, frequency, guardian_id, course_id) 
VALUES (12345, '2024-10-07', 'ACTIVE', 8.5, '2024', '80%', 1, 1);


INSERT INTO student_subjects_enrolled (student_id, subject_id) VALUES (1, 1);
INSERT INTO student_subjects_completed (student_id, subject_id) VALUES (1, 2);

INSERT INTO student_class_group (student_id, class_group_id) VALUES (1, 1);

INSERT INTO grades (student_id, subject_id, score) VALUES (1, 1, 8.5);

INSERT INTO student_activities (student_id, activity_id) VALUES (1, 1);


unlock tables;