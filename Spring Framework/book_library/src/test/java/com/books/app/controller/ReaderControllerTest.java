package com.books.app.controller;


import com.books.app.dto.AssignBooksRequest;
import com.books.app.model.Reader;
import com.books.app.service.ReaderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(ReaderController.class)
@AutoConfigureMockMvc
public class ReaderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ReaderService readerService;

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/reader/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/reader/")
                        .content(asJsonString(new Reader("Test Name", (short) 25)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testAssignBooks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/reader/assign")
                .param("readerId", String.valueOf(1))
                .content(asJsonString(new AssignBooksRequest()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
