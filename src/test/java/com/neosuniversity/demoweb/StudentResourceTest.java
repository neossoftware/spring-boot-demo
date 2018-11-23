package com.neosuniversity.demoweb;

import com.neosuniversity.demoweb.business.StudentIBusiness;
import com.neosuniversity.demoweb.controllers.StudentResource;
import com.neosuniversity.demoweb.domain.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentResource.class)
public class StudentResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("studentBusiness")
    private StudentIBusiness studentIBusiness;

    @Test
    public void testSearchAllStudents() throws Exception {
        Student student = new Student(1L,"Janeth","E1234789");
        List<Student> lstStudents = new ArrayList<Student>();
        lstStudents.add(student);

        given(studentIBusiness.findAllStudents()).willReturn(lstStudents);

        mockMvc.perform(get("/students")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].passportNumber", is("E1234789")));
    }

}
