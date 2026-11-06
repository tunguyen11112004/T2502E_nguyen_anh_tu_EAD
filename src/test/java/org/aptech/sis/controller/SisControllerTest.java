package org.aptech.sis.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldDisplayStudentInformationPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("scores", "students", "subjects"));
    }

    @Test
    void shouldInsertNewStudentFromWebsite() throws Exception {
        mockMvc.perform(post("/students")
                        .param("studentCode", "2007A11")
                        .param("fullName", "Trần Thị B")
                        .param("address", "Hải Phòng"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void shouldInsertNewScoreFromWebsite() throws Exception {
        mockMvc.perform(post("/scores")
                        .param("studentId", "1")
                        .param("subjectId", "2")
                        .param("score1", "9.0")
                        .param("score2", "8.5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}

