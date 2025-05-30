package com.ecommerce.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.product.ProductService;
import com.ecommerce.product.Product;

import com.ecommerce.product.ProductFactory;

@WebMvcTest(ProductController.class)
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    ProductService productService;

    @Test
    public void getAllProductsTest() throws Exception {

        Product mockProduct = ProductFactory.createRandomMockProduct();
        Product secondMockProduct = ProductFactory.createRandomMockProduct();

        String name = mockProduct.getName();
        String secondName = secondMockProduct.getName();

        List<Product> mockProductList = new ArrayList<>();
        mockProductList.add(mockProduct);
        mockProductList.add(secondMockProduct);

        when(productService.getAllProducts()).thenReturn(mockProductList);
        MvcResult res = mockMvc.perform(get("/products")).andExpect(status().isOk()).andReturn();

        String json = res.getResponse().getContentAsString();
        List<Product> products = objectMapper.readValue(json, new TypeReference<List<Product>>() {

        });

        Product firstProduct = products.get(0);
        Product secondProduct = products.get(1);

        assertEquals(firstProduct.getName(), name);
        assertEquals(secondProduct.getName(), secondName);

    }

}
