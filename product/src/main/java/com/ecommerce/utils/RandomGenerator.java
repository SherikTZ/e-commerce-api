package com.ecommerce.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CHARACTERS_LENGTH = CHARACTERS.length();

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS_LENGTH);
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }

    public static Float generateRandomFloat() {
        return ThreadLocalRandom.current().nextFloat();
    }

    public static Boolean generateRandomBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    public static Integer generateRandomInteger() {
        return ThreadLocalRandom.current().nextInt();
    }

}
