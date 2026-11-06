# KT_EAD — Student Information System (`sis`)

Dự án bài thi thực hành **Enterprise Application Development in Java EE (SET01)** — Hệ thống Quản lý Thông tin và Điểm Sinh viên (**Student Information System - SIS**) cho Trường Đại học Bách Khoa Hà Nội (Hanoi University of Science and Technology).

---

## 1. Công nghệ sử dụng

- **Java**: 21
- **Framework**: Spring Boot 3.4.3 (Spring Web MVC, Spring Data JPA, Jakarta Validation, Thymeleaf)
- **Giao diện (UI/UX)**: Thymeleaf + **Tailwind CSS** (tông màu xanh rêu `#5a7753` theo đúng mẫu đề thi kết hợp thẻ KPI, bộ lọc điểm chữ và tính điểm trực tiếp trên form)
- **Cơ sở dữ liệu**: MySQL (`sis`) + H2 In-Memory (cho chạy kiểm thử tự động)
- **Code Intelligence**: CodeGraph (`.codegraph/`)

---

## 2. Cấu trúc thư mục chính

- [AGENTS.md](file:///c:/aptech_ky4/KT_EAD/AGENTS.md), [AI_RULES.md](file:///c:/aptech_ky4/KT_EAD/AI_RULES.md): Quy tắc vận hành AI Agent cho dự án `KT_EAD`.
- [docx/BA_Student_Information_System.md](file:///c:/aptech_ky4/KT_EAD/docx/BA_Student_Information_System.md): Tài liệu Phân tích Nghiệp vụ (BA).
- [docx/Dac_Ta_Du_An_SIS.md](file:///c:/aptech_ky4/KT_EAD/docx/Dac_Ta_Du_An_SIS.md): Tài liệu Đặc tả Dự án & Kiến trúc Hệ thống (SA).
- [sql/sis_schema_and_data.sql](file:///c:/aptech_ky4/KT_EAD/sql/sis_schema_and_data.sql): Script SQL tạo bảng (`student_t`, `subject_t`, `student_score_t`) và dữ liệu mẫu theo đề bài.
- `src/main/java/org/aptech/sis/`: Mã nguồn Java phân tầng (`entity`, `repository`, `service`, `controller`, `dto`, `util`, `config`).
- `src/main/resources/templates/`: Giao diện Thymeleaf tích hợp Tailwind CSS (`index.html`, `student-form.html`, `score-form.html`).

---

## 3. Hướng dẫn chạy dự án

1. **Khởi tạo MySQL Database**:
   - Đảm bảo MySQL đang chạy ở cổng `3306`.
   - Ứng dụng đã cấu hình `createDatabaseIfNotExist=true`, `spring.jpa.hibernate.ddl-auto=update` và [`DataInitializer`](file:///c:/aptech_ky4/KT_EAD/src/main/java/org/aptech/sis/config/DataInitializer.java) tự động thêm dữ liệu mẫu của đề thi nếu bảng trống. Hoặc bạn có thể chạy trực tiếp file [sql/sis_schema_and_data.sql](file:///c:/aptech_ky4/KT_EAD/sql/sis_schema_and_data.sql).
2. **Cấu hình tài khoản DB (nếu cần)**:
   - Mặc định dùng `DB_USERNAME=root`, `DB_PASSWORD=` (rỗng) trong [application.properties](file:///c:/aptech_ky4/KT_EAD/src/main/resources/application.properties).
3. **Chạy ứng dụng**:
   ```powershell
   mvn spring-boot:run
   ```
   Truy cập: `http://localhost:8080`
4. **Chạy kiểm thử tự động (Unit & Integration Tests)**:
   ```powershell
   mvn test
   ```

