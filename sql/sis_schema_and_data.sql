-- =========================================================================
-- Enterprise Application Development in Java EE - SET01
-- Database Name: sis
-- =========================================================================

CREATE DATABASE IF NOT EXISTS sis CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE sis;

-- create table script
CREATE TABLE IF NOT EXISTS student_t (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    student_code VARCHAR(20) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    address VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS subject_t (
    subject_id INT AUTO_INCREMENT PRIMARY KEY,
    subject_code VARCHAR(20) NOT NULL,
    subject_name VARCHAR(100) NOT NULL,
    credit INT NOT NULL
);

CREATE TABLE IF NOT EXISTS student_score_t (
    student_score_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    subject_id INT,
    score1 DECIMAL(5,2),
    score2 DECIMAL(5,2),
    CONSTRAINT fk_student_id FOREIGN KEY (student_id) REFERENCES student_t(student_id),
    CONSTRAINT fk_subject_id FOREIGN KEY (subject_id) REFERENCES subject_t(subject_id)
);

-- Insert sample data
INSERT INTO subject_t (subject_code, subject_name, credit) VALUES
('JAVA', 'Java Programming', 4),
('PHP', 'PHP Programming', 3),
('WDA', 'Web Development and Applications', 3);

INSERT INTO student_t (student_code, full_name, address)
VALUES ('2007A10', 'Nguyễn Văn A', 'Hà Nội');

INSERT INTO student_score_t (student_id, subject_id, score1, score2)
VALUES (1, 1, 8.5, 7.0);

