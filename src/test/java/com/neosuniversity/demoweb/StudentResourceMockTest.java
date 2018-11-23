package com.neosuniversity.demoweb;

import com.neosuniversity.demoweb.controllers.StudentResource;
import com.neosuniversity.demoweb.domain.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentResource.class)
public class StudentResourceMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentResource studentResource;


    @Test
    public void testSearchAllStudentsMock() throws Exception {
        Student student = new Student(1L,"Janeth","E1234789");
        List<Student> lstStudents = new ArrayList<Student>();
        lstStudents.add(student);

        given(studentResource.retrieveAllStudents()).willReturn(lstStudents);

        mockMvc.perform(get("/students")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].passportNumber", is("E1234789")));
    }

    @Test// para los metodos que tiene parametros es necesario agregar en el RestController @PathVariable("id")
    public void testSearchByIdStudentMock() throws Exception {
        Student student = new Student(1L,"Veronica","E1234789");

        when(studentResource.retrieveStudent(anyLong())).thenReturn(student);

        mockMvc.perform(get("/students/{id}",anyLong())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Veronica")))
                .andExpect(jsonPath("$.passportNumber", is("E1234789")));
    }

    @Test //hay que agregar las siguientes anotaciones en el RestController @Valid @RequestBody(required = false)
    public void testCreateNewStudentMock() throws Exception {
        ResponseEntity<Object>  responseEntity=new ResponseEntity<>(HttpStatus.CREATED);
        Student student = new Student("hector","E1234147");

        when(studentResource.createStudent(student)).thenReturn(responseEntity);

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateStudentMock() throws Exception {
        ResponseEntity<Object>  responseEntity=new ResponseEntity<>(HttpStatus.NO_CONTENT);
        Student student = new Student(1L,"Veronica","E1234789");

        when(studentResource.updateStudent(student,student.getId())).thenReturn(responseEntity);

        mockMvc.perform(put("/students/{id}",student.getId())
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }
   @Test
    public void testDeleteStudentMock() throws Exception {
       doNothing().when(studentResource).deleteStudent(anyLong());
        mockMvc.perform(delete("/students/{id}", anyLong())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
       verify(studentResource, times(1)).deleteStudent(anyLong());
    }

}
