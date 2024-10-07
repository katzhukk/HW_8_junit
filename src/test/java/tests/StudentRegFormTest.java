package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import pages.RegistrationPage;
import tests.data.Gender;
import tests.utils.RandomUtils;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class StudentRegFormTest extends TestBase {
    private final RegistrationPage registrationPage = new RegistrationPage();

    String firstName = RandomUtils.getRandomFirstName(),
            lastName = RandomUtils.getRandomLastName(),
            userEmail = RandomUtils.getRandomEmail(),
            gender = RandomUtils.getRandomGender(),
            userNumber = RandomUtils.getRandomNumber(),
            yearOfBirth = RandomUtils.getRandomYearOfBirth(),
            monthOfBirth = RandomUtils.getRandomMonthOfBirth(),
            dayOfBirth = RandomUtils.getRandomDayOfBirth(monthOfBirth, yearOfBirth),
            subjects = RandomUtils.getRandomSubject(),
            hobbies = RandomUtils.getRandomHobbies(),
            picture = RandomUtils.getRandomPicture(),
            currentAddress = RandomUtils.getRandomCurrentAddress(),
            state = RandomUtils.getRandomState(),
            city = RandomUtils.getRandomCity(state);

    @EnumSource(Gender.class)
    @ParameterizedTest(name = "Тест на успешную регистрацию студента по гердеру: {0}.")
    void successfulRegistrationTestWithDifferentGender(Gender gender) {
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setUserNumber(userNumber)
                .setGenderWrapper(String.valueOf(gender))
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .setSubjects(subjects)
                .setHobbiesWrapper(hobbies)
                .setPicture(picture)
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .clickSubmit();

        registrationPage.checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", String.valueOf(gender))
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                .checkResult("Subjects", subjects)
                .checkResult("Hobbies", hobbies)
                .checkResult("Picture", picture)
                .checkResult("Address", currentAddress)
                .checkResult("State and City", state + " " + city);
    }

    @CsvSource(value = {
            "NCR, Delhi",
            "Uttar Pradesh, Lucknow",
            "Haryana, Karnal",
            "Rajasthan, Jaiselmer"
    })
    @ParameterizedTest(name = "Тест на успешную регистрацию студента из города {1} в штате {0}.")
    void successfulRegistrationTestFromDifferentCity(String state, String city) {
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setUserNumber(userNumber)
                .setGenderWrapper(gender)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .setSubjects(subjects)
                .setHobbiesWrapper(hobbies)
                .setPicture(picture)
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .clickSubmit();

        registrationPage.checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                .checkResult("Subjects", subjects)
                .checkResult("Hobbies", hobbies)
                .checkResult("Picture", picture)
                .checkResult("Address", currentAddress)
                .checkResult("State and City", state + " " + city);
    }

    public static Stream<Arguments> checkCities() {
        return Stream.of(
                Arguments.of("NCR",
                        List.of("Delhi", "Gurgaon", "Noida")),
                Arguments.of("Rajasthan",
                        List.of("Jaipur", "Jaiselmer")),
                Arguments.of("Haryana",
                        List.of("Karnal", "Panipat"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Тест на проверку соответствия городов {1} по штату {0}.")
    void checkCities(String state, List<String> cities) {
        registrationPage.openPage();
        $("#react-select-3-input").setValue(String.valueOf(state)).pressEnter();
        $("#city").click();
        for(int i = 0; i < cities.size(); i++)
        {
            $$("[id='stateCity-wrapper']").filter(visible).shouldHave(texts(cities.get(i)));
        }
    }
}