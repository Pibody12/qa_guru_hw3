package tests.testData;

import com.github.javafaker.Faker;
import java.util.Locale;

public class FakerTestData {
    Faker faker = new Faker();
    Faker fakerRu = new Faker(new Locale("ru"));

    public String fakerUserFirstName = fakerRu.name().firstName(),
            fakerUserLastName = fakerRu.name().lastName(),
            fakerUserFullName = fakerRu.name().fullName(),
            fakerUserEmail = faker.name().firstName().toLowerCase() + "@test.ru",
            fakerUserCurrentAddress = fakerRu.address().fullAddress(),
            fakerUserPermanentAddress = fakerRu.address().fullAddress(),
            fakerUserPhoneNumber = faker.phoneNumber().subscriberNumber(10),
            fakerUserAddress = faker.address().fullAddress(),
            fakerGender = faker.options().option("Male", "Female", "Other"),
            fakerMonth = faker.options().option("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"),
            fakerSubject = faker.options().option("Accounting", "Maths", "Hindi", "Arts", "English", "Economics");

    public Integer fakerBirthday = faker.number().numberBetween(1, 30),
            fakerYear = faker.number().numberBetween(1992, 2026);
}