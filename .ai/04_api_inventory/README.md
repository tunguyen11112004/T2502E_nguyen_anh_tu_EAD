# API & Route Inventory

| Method | URI | Controller Method | View / Redirect | Mô tả |
| :-: | :--- | :--- | :--- | :--- |
| `GET` | `/` | `SisController#index` | `templates/index.html` | Hiển thị danh sách thông tin sinh viên, điểm thành phần, tín chỉ và điểm quy đổi (`Grade`), kèm bộ lọc `keyword` & `grade` |
| `GET` | `/students/new` | `SisController#showStudentForm` | `templates/student-form.html` | Hiển thị form thêm mới sinh viên khi bấm `+ Student` |
| `POST` | `/students` | `SisController#saveStudent` | `redirect:/` | Lưu sinh viên mới vào bảng `student_t` |
| `GET` | `/scores/new` | `SisController#showScoreForm` | `templates/score-form.html` | Hiển thị form nhập điểm cho sinh viên khi bấm `+ Score` |
| `POST` | `/scores` | `SisController#saveScore` | `redirect:/` | Lưu điểm (`score1`, `score2`) vào bảng `student_score_t` |
| `GET` | `/scores/{id}/edit` | `SisController#showEditScoreForm` | `templates/score-form.html` | Hiển thị form chỉnh sửa điểm khi bấm icon cây bút trên bảng |
| `POST` | `/scores/{id}` | `SisController#updateScore` | `redirect:/` | Cập nhật bản ghi điểm trong bảng `student_score_t` |
