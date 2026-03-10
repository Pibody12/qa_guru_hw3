package tests.testData;

import com.github.javafaker.Faker;
import java.util.Locale;

public class FakerTestData {
    static Faker faker = new Faker();
    static Faker fakerRu = new Faker(new Locale("ru"));

    public static String fakerUserFirstName = fakerRu.name().firstName();
    public static String fakerUserLastName = fakerRu.name().lastName();
    public static String fakerUserFullName = fakerRu.name().fullName();
    public static String fakerUserEmail = faker.name().firstName().toLowerCase() + "@test.ru";
    public static String fakerUserCurrentAddress = fakerRu.address().fullAddress();
    public static String fakerUserPermanentAddress = fakerRu.address().fullAddress();
    public static String fakerUserPhoneNumber = faker.phoneNumber().subscriberNumber(10);
    public static String fakerUserAddress = faker.address().fullAddress();
}
