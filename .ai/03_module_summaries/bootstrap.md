# Module: bootstrap

- **Mục đích**: Khởi động ứng dụng Spring Boot (`SisApplication`) và nạp dữ liệu mẫu ban đầu (`DataInitializer`) cho hệ thống Student Information System (`sis`).
- **API / màn hình**: Chuyển tiếp tới `SisController` (`/`).
- **Entity**: `Student`, `Subject`, `StudentScore`.
- **Class chính**: `SisApplication`, `DataInitializer`.
- **Phụ thuộc**: Spring Boot Web MVC, Spring Data JPA, Validation, Thymeleaf, MySQL Connector.
- **Test**: `GradeCalculatorTest`, `SisControllerTest`.
