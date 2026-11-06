# Database Schema Summary (`sis`)

Database: `sis` (MySQL)

1. **`student_t`**:
   - `student_id INT AUTO_INCREMENT PRIMARY KEY`
   - `student_code VARCHAR(20) NOT NULL`
   - `full_name VARCHAR(100) NOT NULL`
   - `address VARCHAR(255)`
2. **`subject_t`**:
   - `subject_id INT AUTO_INCREMENT PRIMARY KEY`
   - `subject_code VARCHAR(20) NOT NULL`
   - `subject_name VARCHAR(100) NOT NULL`
   - `credit INT NOT NULL`
3. **`student_score_t`**:
   - `student_score_id INT AUTO_INCREMENT PRIMARY KEY`
   - `student_id INT` (FK -> `student_t.student_id`)
   - `subject_id INT` (FK -> `subject_t.subject_id`)
   - `score1 DECIMAL(5,2)`
   - `score2 DECIMAL(5,2)`
