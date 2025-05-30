package com.ecommerce.product;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

import com.ecommerce.product.ProductRepository;
import com.ecommerce.product.Product;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.product.ProductFactory;

@SpringBootTest
@Transactional
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("Should create a new product, read all attributes properly and persist to DB")
    public void readProductTest() {

        Product product = ProductFactory.createRandomMockProduct();
        String name = product.getName();
        String desc = product.getDescription();
        Float price = product.getPrice();
        Integer stock = product.getStock();
        String category = product.getCategory();
        Boolean isActive = product.getIsActive();

        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct);
        assertNotNull(savedProduct.getId());

        assertEquals(name, savedProduct.getName());
        assertEquals(desc, savedProduct.getDescription());
        assertEquals(price, savedProduct.getPrice());
        assertEquals(stock, savedProduct.getStock());
        assertEquals(category, savedProduct.getCategory());
        assertEquals(isActive, savedProduct.getIsActive());

    }

    @Test
    @DisplayName("Should create a new product and persist to DB")
    public void createProductTest() {

        Product product = ProductFactory.createRandomMockProduct();

        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct);
    }

    @Test
    @DisplayName("Should create a new product, update name and persist to DB")
    public void updateProductTest() {

        Product product = ProductFactory.createRandomMockProduct();

        Product savedProduct = productRepository.save(product);
        String newName = "Second Car";
        savedProduct.setName(newName);
        savedProduct = productRepository.save(savedProduct);
        assertEquals(savedProduct.getName(), newName);

    }

    @Test
    @DisplayName("Should create a new product, delete it and persist to DB")
    public void deleteProductTest() {

        Product product = ProductFactory.createRandomMockProduct();

        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct);

        Integer productID = savedProduct.getId();

        productRepository.deleteById(productID);

        try {
            savedProduct = productRepository.getReferenceById(productID);
            fail();
        } catch (Exception JpaObjectRetrievalFailureException) {

        }

    }

}
