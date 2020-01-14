package com.books.app.controller;

import com.books.app.datamapper.EntityToRestMapper;
import com.books.app.datamapper.RestToEntityMapper;
import com.books.app.dto.BookRestDto;
import com.books.app.service.BookService;
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
@WebMvcTest(BookController.class)
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @MockBean
    RestToEntityMapper restToEntityMapper;

    @MockBean
    EntityToRestMapper entityToRestMapper;

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/book/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/book/")
                        .content(asJsonString(new BookRestDto("Test Name", "Test Author")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/book/{bookId}", 1l)
                .content(asJsonString(new BookRestDto("Test Name", "Test Author")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/book/{bookId}", 1))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/book/{bookId}", 1))
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
