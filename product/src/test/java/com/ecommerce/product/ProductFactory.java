package com.ecommerce.product;

import com.ecommerce.product.Product;
import com.ecommerce.utils.RandomGenerator;

public class ProductFactory {

    public static Product createMockProduct(String name, String desc, Float price, Integer stock, String category,
            Boolean isActive) {

        Product product = new Product();
        product.setName(name);
        product.setDescription(desc);
        product.setPrice(price);
        product.setStock(stock);
        product.setCategory(category);
        product.setIsActive(isActive);
        return product;
    }

    public static Product createRandomMockProduct() {

        Integer NAME_LENGTH = 16;
        Integer DESC_LENGTH = 128;
        Integer CATEGORY_LENGTH = 16;

        Product product = new Product();

        String name = RandomGenerator.generateRandomString(NAME_LENGTH);
        String desc = RandomGenerator.generateRandomString(DESC_LENGTH);
        Float price = RandomGenerator.generateRandomFloat();
        Integer stock = RandomGenerator.generateRandomInteger();
        String category = RandomGenerator.generateRandomString(CATEGORY_LENGTH);
        Boolean isActive = RandomGenerator.generateRandomBoolean();

        product.setName(name);
        product.setDescription(desc);
        product.setPrice(price);
        product.setStock(stock);
        product.setCategory(category);
        product.setIsActive(isActive);

        return product;

    }

}
