package com.hdfc.setu.qa.utils;

import java.util.Random;

public class GenerateMobileNumber {
	
	private static final String[] prefixes = {"7", "8", "9"};
    private static final Random random = new Random();
    
    
    public static String generateRandomMobileNumber() {
        // Choose a random prefix (7, 8, or 9)
        String prefix = prefixes[random.nextInt(prefixes.length)];
        
        // Generate the remaining 9 digits
        StringBuilder remainingDigits = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            remainingDigits.append(random.nextInt(10));
        }
        
        // Concatenate the prefix and remaining digits to form the mobile number
        return prefix + remainingDigits.toString();
    }


}
