# Architecture Map

Kiến trúc phân tầng Spring Boot MVC + Spring Data JPA + Thymeleaf (Tailwind CSS):

```
Browser (Thymeleaf + Tailwind CSS UI)
        |
        v
  SisController (GET /, GET/POST /students, GET/POST /scores)
        |
        v
   SisService (Business Logic + GradeCalculator)
        |
        +---> StudentRepository      ---> student_t
        +---> SubjectRepository      ---> subject_t
        +---> StudentScoreRepository ---> student_score_t
```

- **Database chính**: MySQL (`sis`), hỗ trợ tự động tạo bảng (`ddl-auto=update`) và nạp dữ liệu mẫu ban đầu qua `DataInitializer` nếu bảng trống.
- **UI/UX**: Tích hợp Tailwind CSS bám sát bố cục đề thi (`Student Information System`) kèm tính năng lọc, tìm kiếm, badge điểm chữ (`A`, `B`, `D`, `F`) và chỉnh sửa điểm.
