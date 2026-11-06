package org.aptech.sis.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentForm {

    @NotBlank(message = "Mã sinh viên (Student Code) không được để trống")
    @Size(max = 20, message = "Mã sinh viên tối đa 20 ký tự")
    private String studentCode;

    @NotBlank(message = "Họ và tên (Full Name) không được để trống")
    @Size(max = 100, message = "Họ và tên tối đa 100 ký tự")
    private String fullName;

    @Size(max = 255, message = "Địa chỉ tối đa 255 ký tự")
    private String address;

    public StudentForm() {
    }

    public StudentForm(String studentCode, String fullName, String address) {
        this.studentCode = studentCode;
        this.fullName = fullName;
        this.address = address;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

