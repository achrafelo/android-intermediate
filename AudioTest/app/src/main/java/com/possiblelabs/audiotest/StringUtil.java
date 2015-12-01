package com.possiblelabs.audiotest;

import java.util.Random;

/**
 * Created by possiblelabs on 7/23/15.
 */
public class StringUtil {

    private static String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static int MAX_LETTERS = 5;

    public static String genFileName(String extension) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < MAX_LETTERS; i++)
            sb.append(LETTERS.charAt(randomInt(0, LETTERS.length() - 1)));
        sb.append('.').append(extension);
        return sb.toString();
    }

    public static int randomInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
