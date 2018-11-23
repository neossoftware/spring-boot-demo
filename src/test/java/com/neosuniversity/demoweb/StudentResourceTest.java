package com.neosuniversity.demoweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neosuniversity.demoweb.business.StudentIBusiness;
import com.neosuniversity.demoweb.controllers.StudentResource;
import com.neosuniversity.demoweb.domain.Student;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandlerFactory;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentResource.class)
public class StudentResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentIBusiness studentIBusiness;

    @Mock
    private Student studentMockito;

    private static HttpUrlStreamHandler httpUrlStreamHandler;

    @MockBean
    private MockHttpServletRequest request;


    @BeforeClass
    public static void setupURLStreamHandlerFactory() {
        // Allows for mocking URL connections
        URLStreamHandlerFactory urlStreamHandlerFactory = mock(URLStreamHandlerFactory.class);
        URL.setURLStreamHandlerFactory(urlStreamHandlerFactory);

        httpUrlStreamHandler = new HttpUrlStreamHandler();
        given(urlStreamHandlerFactory.createURLStreamHandler("http")).willReturn(httpUrlStreamHandler);
    }


    @Before
    public void reset() {
        httpUrlStreamHandler.resetConnections();
    }

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

    @Test// para los metodos que tiene parametros es necesario agregar en el RestController @PathVariable("id")
    public void testSearchByIdStudentMock() throws Exception {
        Student student = new Student(3L,"Veronica","E1234789");

        when(studentIBusiness.findStudentById(student.getId())).thenReturn(student);

        mockMvc.perform(get("/students/{id}",student.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Veronica")))
                .andExpect(jsonPath("$.passportNumber", is("E1234789")));
    }
    @Test //hay que agregar las siguientes anotaciones en el RestController @Valid @RequestBody(required = false)
    public void testCreateNewStudentMock() throws Exception {
        Student studentR = new Student(6L,"Veronica","E1234789");
        //ResponseEntity<Object> responseEntity=new ResponseEntity<>(HttpStatus.CREATED);

        //StudentIBusiness busniess =mock(StudentIBusiness.class);
        //URI location = mock(URI.class);
       // URI uri = URI.create("https://www.google.com/");
        given(studentIBusiness.saveStudent(studentMockito)).willReturn(studentR);
        String href = "http://some.host.com/bad-image-reference.gif";

        URLConnection urlConnection = mock(URLConnection.class);
        httpUrlStreamHandler.addConnection(new URL(href), urlConnection);

        IOException fileNotFoundException = new FileNotFoundException(href);
        given(urlConnection.getInputStream()).willThrow(fileNotFoundException);

        when(ServletUriComponentsBuilder.fromRequest(request).build().toUriString()).thenReturn(href);
       // when(location).thenReturn(uri);

              //  URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedStudent.getId()).toUri();
        System.out.println("PASO LO ANTERIO::::");
        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                //.accept(MediaType.APPLICATION_JSON).content("  { \"name\": \" hector\",\"passportNumber\": \"E1234147\"}")
                .accept(MediaType.APPLICATION_JSON).content(asJsonString(studentR))
        )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
