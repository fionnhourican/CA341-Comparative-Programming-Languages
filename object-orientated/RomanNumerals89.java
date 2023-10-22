/*
 * For a number written in Roman numerals to be considered valid there are basic rules which must be followed. 
 * Even though the rules allow some numbers to be expressed in more than one way there is always a "best" way of 
 * writing a particular number.
 *
 * For example, it would appear that there are at least six ways of writing the number sixteen:
 *
 * IIIIIIIIIIIIIIII
 * VIIIIIIIIIII
 *
 * VVIIIIII
 * XIIIIII
 * VVVI
 * XVI
 *
 * However, according to the rules only XIIIIII and XVI are valid, and the last example is considered to be the
 *  most efficient, as it uses the least number of numerals.
 *
 * The 11K text file, roman.txt (right click and 'Save Link/Target As...'), contains one thousand numbers written
 * in valid, but not necessarily minimal, Roman numerals; see About... Roman Numerals for the definitive rules for 
 * this problem.
 *
 * Find the number of characters saved by writing each of these in their minimal form.
 *
 * Note: You can assume that all the Roman numerals in the file contain no more than four consecutive identical units.
*/

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.HashMap;
import java.util.Map;

/**
 * The RomanNumerals89 class contains methods for converting Roman numerals to their minimal form and
 * calculating the number of characters saved by the conversion.
 * It also provides a main method for reading Roman numerals from a file and calculating the total characters saved.
 * The class follows the rules for valid Roman numerals.
 *
 * @author Fionn Hourican
 */
public class RomanNumerals89 {
    /**
     * The main method is the entry point of the program.
     * It reads the file "roman.txt" line by line, calls the method "difference," and calculates the total characters saved.
     *
     * @param args an array of command-line arguments (not used in this method).
     */
    public static void main(String[] args) {
        /**
        * The characters saved by converting to minimal form.
        */
        int charSaved = 0;
        // Try open the file if not found throw an error.
        try {
            File myObj = new File("roman.txt");
            Scanner myReader = new Scanner(myObj);
            // While the file has another line call the function.
            while (myReader.hasNextLine()) {
                // Roman numeral given
                String oldRoman = myReader.nextLine();
                // call function to find the difference.
                int differencce = difference(oldRoman);
                // Add difference to the counter of characters saved.
                charSaved += differencce;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        // Print out the final total of the counter.
        System.out.println(charSaved);
    }

    /**
     * Calculates the difference in the number of characters between the original Roman numeral and its minimal form.
     *
     * @param oldRoman The Roman numeral to be converted to minimal form.
     * @return The difference in the number of characters.
     */
    public static int difference(String oldRoman) {
        // Find origional length
        int originalLength = oldRoman.length();
        // Find Integer value of origional
        int value = romanToInt(oldRoman);
        // Convert Integer Value to minimal form.
        String newRoman = intToRoman(value);
        // Find minimal form Length
        int newLength = newRoman.length();

        // Return the difference.
        return originalLength - newLength;
    }

    /**
     * Converts a Roman numeral to an integer.
     *
     * @param roman The Roman numeral to be converted to an integer.
     * @return The integer representation of the Roman numeral.
     */
    public static int romanToInt(String roman) {

        // Create Map from symbol to corresponding value
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        // Length of origional roman
        int originalLength = roman.length();
        // Variable for Final Integer Value
        int intResult = 0;
        // Previous Roman symbols value
        int prevValue = 0;
        // For loop to go over evry symbol in Roman form.
        for (int i = 0; i < originalLength; i++) {
            // Current value = symbol at i postion converted to int
            int value = romanMap.get(roman.charAt(i));
            // 
            if (value > prevValue && prevValue != 0) {
                intResult += (value - (prevValue * 2));
            } else {
                intResult += value;
            }
            prevValue = value;
        }
        return intResult;
    }

    /**
     * Converts an integer to a minimal Roman numeral representation.
     *
     * @param intRoman The integer value to be converted to a minimal Roman numeral.
     * @return The minimal Roman numeral representation.
     */
    public static String intToRoman(int intRoman) {
        int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] numerals = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

        String roman = "";
        for (int i = 0; i < values.length; i++) {
            while (intRoman >= values[i]) {
                roman = roman + (numerals[i]);
                intRoman -= values[i];
            }
        }
        return roman;
    }
}