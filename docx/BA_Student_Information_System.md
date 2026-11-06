# Tài Liệu Phân Tích Nghiệp Vụ (BA) — Student Information System (SIS)

> **Dự án**: `KT_EAD` — Student Information System (`sis`)  
> **Đề tài**: Enterprise Application Development in Java EE - Exam Paper (SET01)  
> **Đơn vị nghiệp vụ**: Hanoi University of Science and Technology (Trường Đại học Bách Khoa Hà Nội)

---

## 1. Tổng quan Module (Module Overview)

Hệ thống **Student Information System (SIS)** được xây dựng nhằm phục vụ công tác quản lý hồ sơ sinh viên, danh mục môn học và kết quả điểm học tập tại Phòng Đào tạo (Training Department) của Trường Đại học Bách Khoa Hà Nội.

Hệ thống bao gồm 1 module nghiệp vụ cốt lõi (`sis-core`) với 3 nhóm chức năng chính:
1. **Quản lý Sinh viên (`Student Management`)**: Tiếp nhận và lưu trữ thông tin sinh viên mới (`student_code`, `full_name`, `address`).
2. **Quản lý Điểm môn học (`Score Management`)**: Ghi nhận điểm thành phần (`score1`, `score2`) theo từng môn học (`subject_t`) cho sinh viên.
3. **Tra cứu & Xếp loại học lực (`Grade & Information Display`)**: Hiển thị danh sách tổng hợp thông tin sinh viên, môn học, số tín chỉ, điểm thành phần và tự động quy đổi điểm tổng kết sang thang điểm chữ (`A`, `B`, `D`, `F`).

---

## 2. Tác nhân hệ thống (Actors)

| STT | Actor | Vai trò | Mô tả quyền hạn trên hệ thống |
| :-: | :--- | :--- | :--- |
| 1 | **Employee of Training Department** (Nhân viên Phòng Đào tạo) | Người dùng chính | - Xem danh sách thông tin sinh viên và bảng điểm quy đổi.<br>- Thêm mới hồ sơ sinh viên (`+ Student`).<br>- Nhập điểm môn học cho sinh viên (`+ Score`).<br>- Cập nhật thông tin điểm/sinh viên khi có sai sót. |

---

## 3. Danh sách Use Case (Use Case List)

| Mã UC | Tên Use Case | Actor | Mức ưu tiên | Điểm bài thi (Marking Schema) |
| :-: | :--- | :--- | :-: | :-: |
| **UC-01** | Thêm mới sinh viên (`Insert Student`) | Employee of Training Dept | Cao (Must-have) | 6 điểm (3đ Entity + 3đ Insert Web) |
| **UC-02** | Nhập điểm môn học cho sinh viên (`Insert Score`) | Employee of Training Dept | Cao (Must-have) | 6 điểm (3đ Entity + 3đ Insert Web) |
| **UC-03** | Hiển thị danh sách thông tin sinh viên & quy đổi điểm chữ (`Display Student Information & Convert Grade`) | Employee of Training Dept | Cao (Must-have) | 2 điểm (1đ Display + 1đ Convert Grade) |
| **UC-04** | Chỉnh sửa bản ghi điểm / sinh viên & lọc tìm kiếm trên giao diện (`UI/UX Enhancement`) | Employee of Training Dept | Trung bình (Bonus) | 1 điểm (Bonus UI/UX) |

---

## 4. Đặc tả chi tiết Use Case (Use Case Details)

### 4.1. UC-01: Thêm mới sinh viên (`Insert Student`)
- **Actor**: Employee of Training Department
- **Preconditions**:
  - Hệ thống đã kết nối cơ sở dữ liệu `sis`.
  - Bảng `student_t` đã sẵn sàng lưu trữ dữ liệu.
- **Main Flow (Luồng chính)**:
  1. Từ màn hình chính **Student Information System**, nhân viên nhấn nút **`+ Student`**.
  2. Hệ thống hiển thị biểu mẫu thêm mới sinh viên gồm các trường:
     - Mã sinh viên (`Student Code` / `student_code`)
     - Họ và tên (`Full Name` / `full_name`)
     - Địa chỉ (`Address` / `address`)
  3. Nhân viên nhập đầy đủ thông tin hợp lệ và nhấn nút **Save / Submit**.
  4. Hệ thống kiểm tra tính hợp lệ của dữ liệu (`student_code` và `full_name` không được để trống, độ dài nằm trong giới hạn cột DB).
  5. Hệ thống lưu bản ghi mới vào bảng `student_t`.
  6. Hệ thống thông báo thêm sinh viên thành công và điều hướng về màn hình danh sách chính.
- **Alternative / Exception Flows (Luồng rẽ nhánh / ngoại lệ)**:
  - **4a. Dữ liệu trống hoặc vượt quá độ dài**: Hệ thống giữ nguyên form, hiển thị thông báo lỗi cụ thể dưới từng trường bị lỗi để người dùng nhập lại.
  - **4b. Người dùng nhấn Cancel / Back**: Hệ thống hủy thao tác và quay về màn hình danh sách chính mà không lưu dữ liệu.

### 4.2. UC-02: Nhập điểm môn học cho sinh viên (`Insert Score`)
- **Actor**: Employee of Training Department
- **Preconditions**:
  - Trong cơ sở dữ liệu đã có ít nhất 1 sinh viên trong bảng `student_t` và ít nhất 1 môn học trong bảng `subject_t`.
- **Main Flow (Luồng chính)**:
  1. Từ màn hình chính **Student Information System**, nhân viên nhấn nút **`+ Score`**.
  2. Hệ thống tải danh sách sinh viên (`student_t`) và danh sách môn học (`subject_t`), sau đó hiển thị biểu mẫu nhập điểm gồm:
     - Chọn sinh viên (`Student`)
     - Chọn môn học (`Subject`)
     - Điểm thành phần 1 (`Score 1`)
     - Điểm thành phần 2 (`Score 2`)
  3. Nhân viên chọn sinh viên, chọn môn học, nhập `Score 1`, `Score 2` (từ `0.0` đến `10.0`) và nhấn **Save / Submit**.
  4. Hệ thống kiểm tra tính hợp lệ của dữ liệu (`student_id`, `subject_id` tồn tại; `score1`, `score2` nằm trong khoảng `[0.0, 10.0]`).
  5. Hệ thống lưu bản ghi vào bảng `student_score_t`.
  6. Hệ thống thông báo lưu điểm thành công và chuyển về màn hình danh sách chính, nơi bản ghi vừa thêm được hiển thị kèm điểm chữ (`Grade`) tự động tính toán.
- **Alternative / Exception Flows (Luồng rẽ nhánh / ngoại lệ)**:
  - **4a. Điểm nhập ngoài khoảng `0.0 - 10.0` hoặc bỏ trống**: Hệ thống hiển thị thông báo lỗi validation trên form.
  - **4b. Sinh viên đã có điểm môn học này (nếu chọn cập nhật)**: Hệ thống cho phép chỉnh sửa hoặc cảnh báo rõ ràng cho người dùng.

### 4.3. UC-03: Hiển thị thông tin sinh viên & quy đổi điểm chữ (`Display Student Information & Grade`)
- **Actor**: Employee of Training Department
- **Preconditions**:
  - Cơ sở dữ liệu `sis` có dữ liệu trong các bảng `student_t`, `subject_t`, `student_score_t`.
- **Main Flow (Luồng chính)**:
  1. Nhân viên truy cập trang chủ hệ thống (`/` hoặc `/sis`).
  2. Hệ thống truy vấn danh sách kết quả học tập kết hợp (`JOIN`) giữa `student_score_t`, `student_t`, và `subject_t`.
  3. Với mỗi bản ghi điểm, hệ thống tính điểm tổng kết theo công thức:
     $$\text{Total Score} = 0.3 \times \text{score1} + 0.7 \times \text{score2}$$
  4. Hệ thống đối chiếu `Total Score` với bảng quy đổi để xác định `Grade` (`A`, `B`, `D`, `F`).
  5. Hệ thống hiển thị bảng **Student Information** gồm các cột đúng theo thiết kế đề bài:
     - `Id` (STT / `student_score_id`)
     - `Student Id` (`student_code`, ví dụ: `2007A10`)
     - `Student Name` (`full_name`, ví dụ: `Nguyễn Văn A`)
     - `Subject Name` (`subject_name` / `subject_code`)
     - `Score 1` (`score1`)
     - `Score 2` (`score2`)
     - `Credit` (`credit`)
     - `Grade` (`A`, `B`, `D`, `F`)
     - Cột thao tác (Icon chỉnh sửa `Edit`).
- **Alternative Flows**:
  - **2a. Chưa có dữ liệu điểm nào**: Hệ thống hiển thị trạng thái trống (Empty State) trực quan cùng nút tắt **`+ Student`** và **`+ Score`**.

---

## 5. Quy tắc Nghiệp vụ (Business Rules)

| Mã Rule | Tên Quy tắc | Nội dung chi tiết | Nguồn |
| :-: | :--- | :--- | :--- |
| **BR-01** | Công thức tính điểm tổng kết | Điểm tổng kết của một sinh viên cho một môn học được tính theo trọng số:<br>$$\text{Combined Score} = 0.3 \times \text{score1} + 0.7 \times \text{score2}$$ | Đề bài Mục 3 (Trang 2) |
| **BR-02** | Quy tắc quy đổi điểm chữ (`Grade`) | Dựa trên giá trị điểm tổng kết (`Combined Score`):<br>- **Từ `8.0` đến `10.0`**: Xếp loại **`A`**<br>- **Từ `6.0` đến `7.9`** (`< 8.0`): Xếp loại **`B`**<br>- **Từ `4.0` đến `5.9`** (`< 6.0`): Xếp loại **`D`**<br>- **Nhỏ hơn `4.0`** (`< 4.0`): Xếp loại **`F`** | Bảng quy đổi Đề bài (Trang 2) |
| **BR-03** | Ràng buộc dữ liệu Sinh viên | - `student_code`: Bắt buộc (`NOT NULL`), tối đa 20 ký tự.<br>- `full_name`: Bắt buộc (`NOT NULL`), tối đa 100 ký tự.<br>- `address`: Không bắt buộc, tối đa 255 ký tự. | DDL `student_t` (Trang 2) |
| **BR-04** | Ràng buộc dữ liệu Môn học | - `subject_code`: Bắt buộc (`NOT NULL`), tối đa 20 ký tự.<br>- `subject_name`: Bắt buộc (`NOT NULL`), tối đa 100 ký tự.<br>- `credit`: Bắt buộc (`NOT NULL`), kiểu số nguyên dương. | DDL `subject_t` (Trang 2) |
| **BR-05** | Ràng buộc dữ liệu Điểm | - `student_id` phải tham chiếu tới `student_t(student_id)` hợp lệ.<br>- `subject_id` phải tham chiếu tới `subject_t(subject_id)` hợp lệ.<br>- `score1`, `score2`: Kiểu `DECIMAL(5,2)`, giá trị hợp lệ trong thang điểm 10 (`0.00` – `10.00`). | DDL `student_score_t` & Thang điểm (Trang 2-3) |

---

## 6. Ánh xạ API / Màn hình / Cơ sở dữ liệu (Mapping Matrix)

| Use Case | Màn hình (Thymeleaf View) | Endpoint / Route | Bảng CSDL tác động |
| :--- | :--- | :--- | :--- |
| **UC-03**: Hiển thị danh sách thông tin sinh viên & Grade | `templates/index.html` | `GET /`<br>`GET /students` | `student_score_t` (READ)<br>`student_t` (READ)<br>`subject_t` (READ) |
| **UC-01**: Thêm mới sinh viên (`+ Student`) | `templates/student-form.html` (hoặc Modal + trang riêng) | `GET /students/new`<br>`POST /students` | `student_t` (INSERT) |
| **UC-02**: Nhập điểm cho sinh viên (`+ Score`) | `templates/score-form.html` (hoặc Modal + trang riêng) | `GET /scores/new`<br>`POST /scores` | `student_score_t` (INSERT)<br>`student_t` (READ)<br>`subject_t` (READ) |
| **UC-04**: Chỉnh sửa bản ghi điểm (Icon cây bút trên bảng) | `templates/score-form.html` | `GET /scores/{id}/edit`<br>`POST /scores/{id}` | `student_score_t` (UPDATE) |

---

## 7. Câu hỏi mở (Open Questions)

1. **OQ-01 (Xử lý số thập phân ở ranh giới khoảng điểm)**: Trong bảng quy đổi của đề bài ghi `8.0 to 10 -> A`, `6.0 to 7.9 -> B`, `4.0 to 5.9 -> D`, `less than 4.0 -> F`. Với các giá trị như `7.95` hoặc `5.95` (do $0.3 \times \text{score1} + 0.7 \times \text{score2}$ có thể có 2 chữ số thập phân), hệ thống sẽ áp dụng ngưỡng liên tục chuẩn toán học (`>= 8.0` là `A`, `>= 6.0 && < 8.0` là `B`, `>= 4.0 && < 6.0` là `D`, `< 4.0` là `F`) để không bị rơi khoảng giữa `7.9` và `8.0`.
2. **OQ-02 (Hiển thị sinh viên chưa có điểm môn học)**: Bảng chính trên hình minh họa hiển thị các dòng điểm môn học của sinh viên (`student_score_t`). Để hỗ trợ nhân viên Phòng Đào tạo theo dõi cả những sinh viên mới thêm qua nút `+ Student` (chưa nhập điểm), giao diện sẽ cung cấp thêm khu vực/tab thống kê danh sách Sinh viên và Môn học bên cạnh bảng kết quả điểm chính.
3. **OQ-03 (Chức năng của icon cây bút ở cột cuối trong hình đề bài)**: Đề bài có vẽ icon cây bút (Edit) ở cuối mỗi dòng nhưng trong phần text chỉ yêu cầu Insert Student, Insert Score và Display. Hệ thống cài đặt đầy đủ chức năng Edit Score khi bấm vào icon cây bút để đạt điểm tối đa phần UI/UX Bonus.

