# Repo Inventory

Dự án `KT_EAD` — Student Information System (`sis`).
Cập nhật theo index CodeGraph sau `codegraph init -y` (`19 files`, `299 nodes`, `422 edges`).

| Path | Vai trò |
| --- | --- |
| `pom.xml` | Cấu hình Maven: Spring Boot 3.4.3, Java 21, Spring Web MVC, Spring Data JPA, Validation, Thymeleaf, MySQL Connector, H2 (test) |
| `sql/sis_schema_and_data.sql` | Script khởi tạo CSDL `sis` (`student_t`, `subject_t`, `student_score_t`) và dữ liệu mẫu theo đề thi |
| `src/main/java/org/aptech/sis/SisApplication.java` | Entry point của ứng dụng Spring Boot |
| `src/main/java/org/aptech/sis/config/DataInitializer.java` | Tự động khởi tạo dữ liệu mẫu (`JAVA`, `PHP`, `WDA`, sinh viên `2007A10 - Nguyễn Văn A`, điểm mẫu) khi DB trống |
| `src/main/java/org/aptech/sis/entity/Student.java` | Entity ánh xạ bảng `student_t` (Question 1) |
| `src/main/java/org/aptech/sis/entity/Subject.java` | Entity ánh xạ bảng `subject_t` (Question 1) |
| `src/main/java/org/aptech/sis/entity/StudentScore.java` | Entity ánh xạ bảng `student_score_t` & tính điểm quy đổi Grade (Question 2 & 3) |
| `src/main/java/org/aptech/sis/dto/StudentForm.java` | DTO & Validation thêm/sửa sinh viên |
| `src/main/java/org/aptech/sis/dto/ScoreForm.java` | DTO & Validation nhập/sửa điểm môn học |
| `src/main/java/org/aptech/sis/repository/` | `StudentRepository`, `SubjectRepository`, `StudentScoreRepository` |
| `src/main/java/org/aptech/sis/service/SisService.java` | Xử lý nghiệp vụ quản lý sinh viên, môn học, điểm và tính toán thống kê |
| `src/main/java/org/aptech/sis/util/GradeCalculator.java` | Công thức tính điểm `0.3 * score1 + 0.7 * score2` và quy đổi `A`, `B`, `D`, `F` |
| `src/main/java/org/aptech/sis/controller/SisController.java` | Controller phục vụ màn hình danh sách, thêm sinh viên (`+ Student`), thêm/sửa điểm (`+ Score`) |
| `src/main/resources/templates/` | Giao diện Thymeleaf tích hợp Tailwind CSS (`index.html`, `student-form.html`, `score-form.html`) |
| `src/test/java/org/aptech/sis/` | Unit test & MVC integration test kiểm증 công thức Grade và các luồng nghiệp vụ |
| `docx/` | Tài liệu BA (`BA_Student_Information_System.md`) và Đặc tả dự án (`Dac_Ta_Du_An_SIS.md`) |
| `AGENTS.md`, `AI_RULES.md` | Quy định vận hành AI Agent |
| `.codegraph/` | Index đồ thị mã nguồn local (đã thêm vào `.gitignore`) |
