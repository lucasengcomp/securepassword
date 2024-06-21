package tech.buildrun.securepassword.service;

import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;
import tech.buildrun.securepassword.utils.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class PasswordService {

    public List<String> validatePass(String pass) {
        List<String> failures = new ArrayList<>();

        validateLength(pass, failures);
        validateUppercase(pass, failures);
        validateLowercase(pass, failures);
        validateNumber(pass, failures);
        validateSpecialChars(pass, failures);

        return failures;
    }

    private static void validateLength(String pass, List<String> failures) {
        if (StringUtils.isBlank(pass) || pass.length() < 8) {
            failures.add(Messages.PASSWORD_MIN_LENGTH);
        }
    }

    private static void validateUppercase(String pass, List<String> failures) {
        if (!Pattern.matches(".*[A-Z].*", pass)) {
            failures.add(Messages.PASSWORD_UPPERCASE);
        }
    }

    private static void validateLowercase(String pass, List<String> failures) {
        if (!Pattern.matches(".*[a-z].*", pass)) {
            failures.add(Messages.PASSWORD_LOWERCASE);
        }
    }

    private static void validateNumber(String pass, List<String> failures) {
        if (!Pattern.matches(".*[0-9].*", pass)) {
            failures.add(Messages.PASSWORD_NUMBER);
        }
    }

    private static void validateSpecialChars(String pass, List<String> failures) {
        if (!Pattern.matches(".*[\\W].*", pass)) {
            failures.add(Messages.PASSWORD_SPECIAL_CHAR);
        }
    }
}
