CREATE TABLE IF NOT EXISTS `students` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `enrollment_number` INT NOT NULL,
    `enrollment_date` DATE NOT NULL,
    `course_status` VARCHAR(11) NOT NULL,
    `average_score` DOUBLE NOT NULL,
    `academic_year` VARCHAR(255) NOT NULL,
    `frequency` VARCHAR(255) NOT NULL,
    `guardian_id` BIGINT,
    `course_id` BIGINT,
    FOREIGN KEY (guardian_id) REFERENCES guardians(id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;