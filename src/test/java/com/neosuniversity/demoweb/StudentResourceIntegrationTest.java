package com.neosuniversity.demoweb;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neosuniversity.demoweb.controllers.StudentResource;
import com.neosuniversity.demoweb.domain.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;


import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentResourceIntegrationTest {
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private StudentResource studentResource;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.studentResource).build();
    }

    @Test
    public void testSearchAllStudents() throws Exception {
        mockMvc.perform(get("/students").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].passportNumber", is("E1234789")));
    }

    @Test
    public void testSearchByIdStudent() throws Exception {
        mockMvc.perform(get("/students/{id}", 1).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Veronica")))
                .andExpect(jsonPath("$.passportNumber", is("E1234789")));
    }

    @Test
    public void testCreateNewStudent() throws Exception {
        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content("  { \"name\": \" hector\",\"passportNumber\": \"E1234147\"}")
        )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateStudent() throws Exception {
        mockMvc.perform(put("/students/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \" Judith\",\"passportNumber\": \"E1234147\"}")
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    @Test
    public void testDeleteStudent() throws Exception {
        MvcResult result = mockMvc.perform(get("/students").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        //String contentAsString = result.getResponse().getContentAsString();
        //Student response = objectMapper.readValue(contentAsString, Student.class);
        List<Student> lstStudets = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Student>>() {});
      //  lstStudets.stream().forEach(student-> System.out.println("nombre:"+ student.getName()));
       Student lastStudent = lstStudets.get(lstStudets.size()-1);

        mockMvc.perform(delete("/students/{id}", lastStudent.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
