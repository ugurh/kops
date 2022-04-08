package io.ugurh.kops.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.ugurh.kops.dto.ProductDto;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc
class ProductControllerTest {

    /**
     * @WebAppConfiguration is used to make sure the ApplicationContext is of the WebApplicationContext type.
     * @Gson is a Java library that can be used to convert Java Objects into their JSON representation.
     * @MockMvc instance is constructed from the WebApplicationContext.
     * @MockMvcRequestBuilders post and get methods are used to send the request.
     */

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
        ProductDto productDto = new ProductDto();
        productDto.setName("Test Product");

        String json = gson.toJson(productDto);
        MockHttpServletRequestBuilder requestBuilderOne = MockMvcRequestBuilders.post("/product/create");
        requestBuilderOne.contentType(MediaType.APPLICATION_JSON);
        requestBuilderOne.content(json.getBytes());
        mockMvc.perform(requestBuilderOne).andExpect(MockMvcResultMatchers.status().isOk());
    }

    private void testUpdate() throws Exception {

        MockHttpServletRequestBuilder requestBuilder2 = MockMvcRequestBuilders.get("/product/findAll");
        MvcResult result = mockMvc.perform(requestBuilder2).andReturn();
        String response2 = result.getResponse().getContentAsString();
        Type listType = new TypeToken<List<ProductDto>>() {
        }.getType();
        List<ProductDto> productDtoList = gson.fromJson(response2, listType);
        ProductDto productDto2 = productDtoList.get(0);
        productDto2.setName("DEF");
        String json2 = gson.toJson(productDto2);
        MockHttpServletRequestBuilder requestBuilder3 = MockMvcRequestBuilders.post("/product/edit");
        requestBuilder3.contentType(MediaType.APPLICATION_JSON);
        requestBuilder3.content(json2.getBytes());
        mockMvc.perform(requestBuilder3).andExpect(MockMvcResultMatchers.status().isOk());
    }

    private void testDelete() throws Exception {

        MockHttpServletRequestBuilder requestBuilder2 = MockMvcRequestBuilders.get("/product/findAll");
        MvcResult result = mockMvc.perform(requestBuilder2).andReturn();
        String response2 = result.getResponse().getContentAsString();
        Type listType = new TypeToken<List<ProductDto>>() {
        }.getType();
        List<ProductDto> productDtoList = gson.fromJson(response2, listType);
        ProductDto productDto2 = productDtoList.get(0);
        MockHttpServletRequestBuilder requestBuilder3 = MockMvcRequestBuilders.post("/product/remove/" + productDto2.getId());
        requestBuilder3.contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder3).andExpect(MockMvcResultMatchers.status().is(HttpStatus.NO_CONTENT.value()));
    }
}



