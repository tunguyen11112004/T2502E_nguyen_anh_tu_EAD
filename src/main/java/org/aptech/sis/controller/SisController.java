package org.aptech.sis.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.aptech.sis.dto.ScoreForm;
import org.aptech.sis.dto.StudentForm;
import org.aptech.sis.entity.Student;
import org.aptech.sis.entity.StudentScore;
import org.aptech.sis.entity.Subject;
import org.aptech.sis.service.SisService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SisController {

    private final SisService sisService;

    public SisController(SisService sisService) {
        this.sisService = sisService;
    }

    @GetMapping({"/", "/sis"})
    public String index(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "grade", required = false, defaultValue = "ALL") String grade,
            Model model) {
        List<StudentScore> scores = sisService.findAllStudentScores(keyword, grade);
        List<Student> students = sisService.findAllStudents();
        List<Subject> subjects = sisService.findAllSubjects();

        long countGradeA = scores.stream().filter(s -> "A".equals(s.getGrade())).count();
        long countGradeB = scores.stream().filter(s -> "B".equals(s.getGrade())).count();
        long countGradeD = scores.stream().filter(s -> "D".equals(s.getGrade())).count();
        long countGradeF = scores.stream().filter(s -> "F".equals(s.getGrade())).count();

        model.addAttribute("scores", scores);
        model.addAttribute("students", students);
        model.addAttribute("subjects", subjects);
        model.addAttribute("keyword", keyword != null ? keyword : "");
        model.addAttribute("selectedGrade", grade);
        model.addAttribute("countGradeA", countGradeA);
        model.addAttribute("countGradeB", countGradeB);
        model.addAttribute("countGradeD", countGradeD);
        model.addAttribute("countGradeF", countGradeF);
        return "index";
    }

    @GetMapping("/students/new")
    public String showStudentForm(Model model) {
        if (!model.containsAttribute("studentForm")) {
            model.addAttribute("studentForm", new StudentForm());
        }
        return "student-form";
    }

    @PostMapping("/students")
    public String saveStudent(
            @Valid @ModelAttribute("studentForm") StudentForm studentForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "student-form";
        }
        Student saved = sisService.createStudent(studentForm);
        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Đã thêm sinh viên thành công: " + saved.getStudentCode() + " - " + saved.getFullName()
        );
        return "redirect:/";
    }

    @GetMapping("/scores/new")
    public String showScoreForm(
            @RequestParam(name = "studentId", required = false) Integer studentId,
            Model model) {
        if (!model.containsAttribute("scoreForm")) {
            ScoreForm form = new ScoreForm();
            form.setStudentId(studentId);
            model.addAttribute("scoreForm", form);
        }
        populateScoreReferenceData(model, false);
        return "score-form";
    }

    @PostMapping("/scores")
    public String saveScore(
            @Valid @ModelAttribute("scoreForm") ScoreForm scoreForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            populateScoreReferenceData(model, scoreForm.getStudentScoreId() != null);
            return "score-form";
        }
        StudentScore saved = sisService.saveOrUpdateScore(scoreForm);
        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Đã lưu điểm môn " + saved.getSubject().getSubjectName()
                        + " cho sinh viên " + saved.getStudent().getFullName()
                        + " (Grade: " + saved.getGrade() + ")"
        );
        return "redirect:/";
    }

    @GetMapping("/scores/{id}/edit")
    public String showEditScoreForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            ScoreForm form = sisService.getScoreFormById(id);
            model.addAttribute("scoreForm", form);
            populateScoreReferenceData(model, true);
            return "score-form";
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
            return "redirect:/";
        }
    }

    @PostMapping("/scores/{id}")
    public String updateScore(
            @PathVariable("id") Integer id,
            @Valid @ModelAttribute("scoreForm") ScoreForm scoreForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        scoreForm.setStudentScoreId(id);
        if (bindingResult.hasErrors()) {
            populateScoreReferenceData(model, true);
            return "score-form";
        }
        StudentScore updated = sisService.saveOrUpdateScore(scoreForm);
        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Đã cập nhật điểm cho sinh viên " + updated.getStudent().getFullName()
                        + " - Môn " + updated.getSubject().getSubjectName()
                        + " (Grade: " + updated.getGrade() + ")"
        );
        return "redirect:/";
    }

    private void populateScoreReferenceData(Model model, boolean editMode) {
        model.addAttribute("students", sisService.findAllStudents());
        model.addAttribute("subjects", sisService.findAllSubjects());
        model.addAttribute("editMode", editMode);
    }
}

