package tests.utils;

import com.github.javafaker.Faker;

public class RandomUtils {
    private static final Faker faker = new Faker();

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String getRandomGender() {
        return faker.options().option("Male", "Female", "Other");
    }

    public static String getRandomNumber() {
        return String.valueOf(faker.phoneNumber().subscriberNumber(10));
    }

    public static String getRandomMonthOfBirth() {
        return faker.options().option("January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December");
    }

    public static String getRandomYearOfBirth() {
        return String.valueOf(faker.number().numberBetween(1900, 2024));
    }

    public static String getRandomDayOfBirth(String month, String year) {
        if (month.equals("January") || month.equals("March") || month.equals("May") || month.equals("July")
                || month.equals("August") || month.equals("October") || month.equals("December")) {
            return String.format("%02d", faker.number().numberBetween(1, 31));
        }
        if (month.equals("April") || month.equals("June") || month.equals("September") || month.equals("November")) {
            return String.format("%02d", faker.number().numberBetween(1, 30));
        }
        if (month.equals("February") && (Integer.parseInt(year) % 4 == 0)) {
            return String.format("%02d", faker.number().numberBetween(1, 29));
        } else {
            return String.format("%02d", faker.number().numberBetween(1, 28));
        }
    }

    public static String getRandomSubject() {
        return faker.options().option("Accounting", "Arts", "Biology", "Chemistry", "Computer Science",
                "Commerce", "Civics", "Economics", "English", "Hindi", "History", "Math", "Social Studies", "Physics");
    }

    public static String getRandomHobbies() {
        return faker.options().option("Sports", "Reading", "Music");
    }

    public static String getRandomPicture() {
        return faker.options().option("StudentRegForm.jpg", "StudentRegForm1.jpg", "StudentRegForm2.jpg");
    }

    public static String getRandomCurrentAddress() {
        return faker.address().streetAddress();
    }

    public static String getRandomState() {
        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }

    public static String getRandomCity(String state) {
        if (state.equals("NCR")) {
            return faker.options().option("Delhi", "Gurgaon", "Noida");
        }
        if (state.equals("Uttar Pradesh")) {
            return faker.options().option("Agra", "Lucknow", "Merrut");
        }
        if (state.equals("Haryana")) {
            return faker.options().option("Karnal", "Panipat");
        }
        if (state.equals("Rajasthan")) {
            return faker.options().option("Jaipur", "Jaiselmer");
        } else {
            return "";
        }
    }
}
