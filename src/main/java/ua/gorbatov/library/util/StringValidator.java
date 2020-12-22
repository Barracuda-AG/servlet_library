package ua.gorbatov.library.util;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator {
    private static final StringValidator stringValidator = new StringValidator();

    private StringValidator(){
    }

    public static StringValidator getInstance(){
        return stringValidator;
    }
    public static final String REG_EXP_NAME = "^[A-Za-z0-9]{1,20}";

    public static final String REG_EXP_NAME_UA = "^[А-ЩЬЮЯҐІЇЄ][а-щьюяґіїє']{1,20}$";

    public static final String REG_EXP_PASSWORD = "^[a-zA-Z0-9_.$!]{2,30}";

    public static final String REG_EXP_BOOK_TITLE = "^[A-Za-z0-9А-ЩЬЮЯҐІЇЄа-щьюяґіїє' ]{1,20}";

    public static final String REG_EXP_EMAIL = "(\\w+)@(\\w+\\.)(\\w+)";

    public boolean checkEmail(String emailToCheck){
        Pattern pattern = Pattern.compile(REG_EXP_EMAIL);
        Matcher matcher = pattern.matcher(emailToCheck);
        return matcher.matches();
    }
    public boolean checkNameEn(String nameToCheck){
        Pattern pattern = Pattern.compile(REG_EXP_NAME);
        Matcher matcher = pattern.matcher(nameToCheck);
        return matcher.matches();
    }
    public boolean checkNameUa(String nameToCheck){
        Pattern pattern = Pattern.compile(REG_EXP_NAME_UA);
        Matcher matcher = pattern.matcher(nameToCheck);
        return matcher.matches();
    }
    public boolean checkPassword(String passwordToCheck){
        Pattern pattern = Pattern.compile(REG_EXP_PASSWORD);
        Matcher matcher = pattern.matcher(passwordToCheck);
        return matcher.matches();
    }
    public boolean checkBookTitle(String bookTitle){
        Pattern pattern = Pattern.compile(REG_EXP_BOOK_TITLE);
        Matcher matcher = pattern.matcher(bookTitle);
        return matcher.matches();
    }
    public boolean checkDate(String localDate){
        return Objects.nonNull(localDate);
    }
    public boolean checkInteger(String integerString){
        if(integerString.isEmpty()) return false;

        Integer integer = Integer.valueOf(integerString);
        return integer > 0;
    }

}
