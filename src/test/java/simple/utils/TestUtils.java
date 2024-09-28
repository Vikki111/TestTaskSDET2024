package simple.utils;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import simple.page.ListCustomersPage;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class TestUtils {

    public static String getProperty(String key) {
        Properties props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("base.properties");
        try {
            props.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props.getProperty(key);
    }

    public static String getFirstNameFromPostCode(String postCode) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < postCode.length(); i += 2) {
            String twoDigitStr = postCode.substring(i, i + 2);
            int number = Integer.parseInt(twoDigitStr);

            char letter = getLetterFromNumber(number);
            result.append(letter);
        }

        return result.toString();
    }

    public static char getLetterFromNumber(int number) {
        int index = number % 26;

        return (char) ('a' + index);
    }

    public static boolean isAlertPresent(WebDriver driver){
        try{
            driver.switchTo().alert();
            return true;
        }catch (NoAlertPresentException e){
            return false;
        }
    }

    public static Map<String, String> getMapNameToIndex(List<String> actualListOfFirstNames) {
        Map<String, String> mapNameToIndex = new HashMap<>();
        for (int i = 0; i < actualListOfFirstNames.size(); i++) {
            mapNameToIndex.put(actualListOfFirstNames.get(i), Integer.toString(i+1));
        }
        return mapNameToIndex;
    }

    public static String getNameForDeletion(List<String> actualListOfFirstNames) {
        OptionalDouble averageLength = actualListOfFirstNames.stream()
                .mapToInt(String::length)
                .average();

        return actualListOfFirstNames.stream()
                .min(Comparator.comparingDouble(s -> Math.abs(s.length() - averageLength.getAsDouble()))).get();
    }

    public static List<String> getActualListOfFirstNames(ListCustomersPage listCustomersPage) {
        List<String> actualListOfFirstNames = new LinkedList<>();

        for (int i = 1; i <= listCustomersPage.getSizeOfTable(); i++) {
            actualListOfFirstNames.add(listCustomersPage.getCellByRowNumberForFirstName(Integer.toString(i)));
        }
        return actualListOfFirstNames;
    }
}
