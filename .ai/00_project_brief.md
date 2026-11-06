# Project Brief

- **Tên dự án**: `KT_EAD` — Student Information System (`sis`)
- **Đơn vị bài toán**: Hanoi University of Science and Technology (Đề thi Enterprise Application Development in Java EE - SET01)
- **Group**: `org.aptech`
- **Package**: `org.aptech.sis`
- **Tech Stack**: Java 21, Spring Boot 3.4.x (Spring Web MVC, Spring Data JPA, Validation, Thymeleaf), Tailwind CSS, MySQL (`sis` database)
- **Mục tiêu dự án**: Xây dựng hệ thống Quản lý Thông tin Sinh viên (Student Information System - SIS) cho phép nhân viên Phòng Đào tạo (Training Department):
  1. Thêm mới sinh viên (`+ Student`)
  2. Nhập điểm môn học cho sinh viên (`+ Score`)
  3. Hiển thị bảng thông tin sinh viên kèm điểm môn học, số tín chỉ và xếp loại học lực (`Grade = 0.3 * score1 + 0.7 * score2` quy đổi ra `A`, `B`, `D`, `F`).

## Open Questions

- Icon cây bút (Edit) ở cột cuối bảng danh sách trên đề bài không mô tả chi tiết trong 3 câu hỏi chính: hệ thống sẽ hỗ trợ chỉnh sửa điểm/thông tin sinh viên để hoàn thiện trải nghiệm UI/UX (Bonus Point).
- Ràng buộc trùng lặp `(student_id, subject_id)` trong bảng `student_score_t` không khai báo `UNIQUE` trong SQL gốc của đề: xử lý kiểm tra ở tầng nghiệp vụ hoặc cho phép cập nhật.
