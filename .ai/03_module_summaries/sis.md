# Module: sis (Student Information System)

- **Purpose**: Quản lý thông tin sinh viên, môn học, nhập điểm thành phần và hiển thị bảng điểm quy đổi (`Grade`) cho Phòng Đào tạo (Training Department).
- **Main APIs / Screens**:
  - `GET /`: Màn hình chính hiển thị bảng **Student Information** (`Id`, `Student Id`, `Student Name`, `Subject Name`, `Score 1`, `Score 2`, `Credit`, `Grade`), hỗ trợ tìm kiếm theo từ khóa và lọc theo `Grade`.
  - `GET /students/new`, `POST /students`: Biểu mẫu và xử lý thêm mới sinh viên (`+ Student`).
  - `GET /scores/new`, `POST /scores`: Biểu mẫu và xử lý nhập điểm môn học cho sinh viên (`+ Score`).
  - `GET /scores/{id}/edit`, `POST /scores/{id}`: Biểu mẫu và xử lý cập nhật điểm khi nhấn icon cây bút (Edit).
- **Entities**:
  - `Student` (`student_t`)
  - `Subject` (`subject_t`)
  - `StudentScore` (`student_score_t`)
- **Services / Classes**:
  - `SisController`, `SisService`, `GradeCalculator`, `DataInitializer`
- **Business Rules**:
  - Điểm tổng kết: `GradeScore = 0.3 * score1 + 0.7 * score2`
  - Quy đổi điểm chữ: `>= 8.0 -> A`, `6.0 .. < 8.0 -> B`, `4.0 .. < 6.0 -> D`, `< 4.0 -> F`
- **External Dependencies**: Spring Web, Spring Data JPA, Jakarta Validation, Thymeleaf, MySQL Connector, Tailwind CSS.
- **Known Risks**: Cần đảm bảo MySQL có database `sis` (đã bật `createDatabaseIfNotExist=true` trong JDBC URL để tự động tạo database `sis` nếu chưa tồn tại).
- **Test Notes**: Kiểm thử đơn vị cho `GradeCalculatorTest` và kiểm thử luồng controller trong `SisControllerTest`.

