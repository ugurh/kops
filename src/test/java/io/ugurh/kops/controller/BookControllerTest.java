package io.ugurh.kops.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.ugurh.kops.dto.BookDto;
import io.ugurh.kops.dto.ShippingDto;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@SpringBootTest
@Transactional
@WebAppConfiguration
@AutoConfigureMockMvc
class BookControllerTest {

    private final Gson gson = new GsonBuilder().create();

    @Resource
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testAll() throws Exception {
        testCreate();
        testUpdate();
        testDelete();
    }

    private void testCreate() throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setName("Java SE");
        ShippingDto shippingDto = new ShippingDto();
        shippingDto.setCity("IND");
        bookDto.setShipping(shippingDto);

        String json = gson.toJson(bookDto);
        MockHttpServletRequestBuilder requestBuilderOne = MockMvcRequestBuilders.post("/books/create");
        requestBuilderOne.contentType(MediaType.APPLICATION_JSON);
        requestBuilderOne.content(json.getBytes());
        mockMvc.perform(requestBuilderOne).andExpect(MockMvcResultMatchers.status().isOk());
    }

    private void testUpdate() throws Exception {
        MockHttpServletRequestBuilder requestBuilder2 = MockMvcRequestBuilders.get("/books/findAll");
        MvcResult result = mockMvc.perform(requestBuilder2).andReturn();
        String response2 = result.getResponse().getContentAsString();
        Type listType = new TypeToken<List<BookDto>>() {
        }.getType();
        List<BookDto> bookDtos = gson.fromJson(response2, listType);
        BookDto bookDto2 = bookDtos.get(0);
        bookDto2.setName("JEFF");
        String json2 = gson.toJson(bookDto2);
        MockHttpServletRequestBuilder requestBuilder3 = MockMvcRequestBuilders.post("/books/edit");
        requestBuilder3.contentType(MediaType.APPLICATION_JSON);
        requestBuilder3.content(json2.getBytes());
        this.mockMvc.perform(requestBuilder3).andExpect(MockMvcResultMatchers.status().isOk());
    }

    private void testDelete() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/findAll");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String response2 = result.getResponse().getContentAsString();
        Type listType = new TypeToken<List<BookDto>>() {
        }.getType();
        List<BookDto> bookDtos = gson.fromJson(response2, listType);
        BookDto bookDto2 = bookDtos.get(0);
        MockHttpServletRequestBuilder requestBuilder2 = MockMvcRequestBuilders.post("/books/remove/" + bookDto2.getId());
        requestBuilder2.contentType(MediaType.APPLICATION_JSON);
      mockMvc.perform(requestBuilder2).andExpect(MockMvcResultMatchers.status().is(204));
    }

}
