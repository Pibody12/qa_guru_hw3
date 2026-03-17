package utils;

import org.junit.jupiter.api.Test;

import javax.xml.transform.Result;
import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.String.format;

public class RandomUtils {

    public static void main(String[] args){
        System.out.println(getRandomString(8));
        System.out.println(getRandomEmail());
        System.out.println(getRandomNumber_bad_practice(5));
        System.out.println(getRandomInt(0, 999999));
        System.out.println(getRandomInt(11111111, 999999999));
        System.out.println(getRandomPhone());
        System.out.println(getRandomGender());
    }


    public static String getRandomString(int length) {
//        String LETTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXQZabcdefghijklmnopqrstuvwxqz";
        String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXQZabcdefghijklmnopqrstuvwxqz";
        StringBuilder result = new StringBuilder();
        SecureRandom rnd = new SecureRandom();

        for (int i = 0; i < length; i++) {
            result.append(
                    LETTERS.charAt(                         //random letter from A to z
                            rnd.nextInt(                    //random from 0 to 10
                                    LETTERS.length())));    //10
        }

        return result.toString();
    }

    public static String getRandomEmail() {
//        return getRandomString(4) + "@" + getRandomString(4) + ".ru";
//        return String.format("%s@%s.ru");

        return format("%s@%s.com", getRandomString(5), getRandomString(5));
    }

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
//        SecureRandom rnd = new SecureRandom();
//        return rnd.nextInt(max + 1);
    }

    public static String getRandomPhone() {
        String phoneTemplate = "+%s (%s) %s - %s - %s";

        return format(phoneTemplate, getRandomInt(1, 9), getRandomInt(111, 999), getRandomInt(111, 999), getRandomInt(11, 99), getRandomInt(11, 99));
    }

    public static String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};

        return getRandomItemFromStringArray(genders);
    }

    public static String getRandomItemFromStringArray(String[] stringArray) {
        int arrayLength = stringArray.length;
        int randomIndex = getRandomInt(0, arrayLength - 1);

        return stringArray[randomIndex];
    }

    public static String getRandomMonth() {
        String[] month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        return getRandomItemFromStringArray(month);
    }

    public static String getRandomSubjects() {
        String[] subjects = {"Accounting", "Maths", "Hindi", "Arts", "English", "Economics"};

        return getRandomItemFromStringArray(subjects);
    }

    public static String getRandomState() {
        String[] stateArray = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};

        return getRandomItemFromStringArray(stateArray);
    }

    public static String getRandomCity(String State) {
        String City = "";

        String[] cityArrayForNRC = {"Delhi", "Gurgaon", "Noida"};
        String[] cityArrayForUttarPradesh = {"Agra", "Lucknow", "Merrut"};
        String[] cityArrayForHaryana = {"Karnal", "Panipat"};
        String[] cityArrayForRajasthan = {"Jaipur", "Jaiselmer"};

        if (State.equals("NCR")) {
            City = getRandomItemFromStringArray(cityArrayForNRC);
        } else if (State.equals("Uttar Pradesh")) {
            City = getRandomItemFromStringArray(cityArrayForUttarPradesh);
        } else if (State.equals("Haryana")) {
            City = getRandomItemFromStringArray(cityArrayForHaryana);
        } else if (State.equals("Rajasthan")) {
            City = getRandomItemFromStringArray(cityArrayForRajasthan);
        }

        return City;
    }

    // плохие практики
    public static String getRandomNumber_bad_practice(int length) { // Bad Practice
        String LETTERS = "0123456789";
        StringBuilder result = new StringBuilder();
        SecureRandom rnd = new SecureRandom();

        for (int i = 0; i < length; i++) {
            result.append(
                    LETTERS.charAt(                         //random letter from A to z
                            rnd.nextInt(                    //random from 0 to 10
                                    LETTERS.length())));    //10
        }

        return result.toString();
    }

    public static String getRandomGender_bad_practice() {
        String[] genders = {"Male",  "Female", "Other"};

        int randomIndex = getRandomInt(0, 2);

        return genders[randomIndex];
    }
}
