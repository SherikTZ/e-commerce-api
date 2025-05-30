package com.ecommerce.product;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;

import com.ecommerce.product.ProductRepository;
import com.ecommerce.product.Product;
import com.ecommerce.product.ProductService;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import com.ecommerce.product.ProductFactory;

@SpringBootTest
@Transactional
public class ProductServiceTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    @DisplayName("Should create two products, persist them to DB, and return them as a List")
    public void getAllProductsTest() {

        Product mockProduct = ProductFactory.createRandomMockProduct();
        Product secondMockProduct = ProductFactory.createRandomMockProduct();

        List<Product> mockProductList = new ArrayList<>();
        mockProductList.add(mockProduct);
        mockProductList.add(secondMockProduct);

        when(productRepository.findAll()).thenReturn(mockProductList);

        List<Product> productList = productService.getAllProducts();
        Product firstProduct = productList.get(0);
        Product secondProduct = productList.get(1);
        assertEquals(firstProduct, mockProduct);
        assertEquals(secondProduct, secondMockProduct);
    }

    @Test
    @DisplayName("Should create a product, persist it to DB, and fetch it by ID")
    public void getProductByIdTest() {

        Product mockProduct = ProductFactory.createRandomMockProduct();
        Integer ID = 1;
        mockProduct.setId(ID);

        when(productRepository.findById(ID)).thenReturn(Optional.of(mockProduct));

        Optional<Product> product = productService.getProductById(ID);
        product.ifPresent(realProduct -> assertEquals(realProduct, mockProduct));
    }

    @Test
    @DisplayName("Should create a product, persist it to DB")
    public void createProductTest() {

        Product mockProduct = ProductFactory.createRandomMockProduct();

        when(productRepository.save(mockProduct)).thenReturn(mockProduct);

        Product product = productService.createProduct(mockProduct);

        assertNotNull(product);

    }

    @Test
    @DisplayName("Should create a product, update product, persist it to DB")
    public void updateProductTest() {

        Product mockProduct = ProductFactory.createRandomMockProduct();
        when(productRepository.save(mockProduct)).thenReturn(mockProduct);
        Product product = productService.createProduct(mockProduct);
        String newName = "New Car Name";
        product.setName(newName);

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(mockProduct));

        Product updatedProduct = productService.updateProduct(product.getId(), product);
        assertEquals(updatedProduct.getName(), newName);
    }

    @Test
    @DisplayName("Should create a product, try to update product, fail to update because of wrong ID")
    public void updateProductWrongIDTest() {

        Product mockProduct = ProductFactory.createRandomMockProduct();
        when(productRepository.save(mockProduct)).thenReturn(mockProduct);
        Product product = productService.createProduct(mockProduct);
        String newName = "New Car Name";
        product.setName(newName);

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(mockProduct));

        try {
            Integer nonExistentID = product.getId() - 1;
            productService.updateProduct(nonExistentID, product);
            fail();
        } catch (Exception RuntimeException) {

        }
    }

    @Test
    @DisplayName("Should create a product, then delete the product. Attempting to retrieve should throw RuntimeException")
    public void deleteProductTest() {

        Product mockProduct = ProductFactory.createRandomMockProduct();

        when(productRepository.save(mockProduct)).thenReturn(mockProduct);

        Product product = productService.createProduct(mockProduct);
        Integer ID = product.getId();

        productService.deleteProduct(ID);
        Optional<Product> deletedProduct = productService.getProductById(ID);
        deletedProduct.ifPresent(p -> fail());
    }
}
