package tech.buildrun.securepassword.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.buildrun.securepassword.utils.Messages;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordServiceTest {

    @Autowired
    private PasswordService passwordService;

    private String shortPassword;
    private String validPassword;
    private String noUppercasePassword;
    private String withUppercasePassword;
    private String noLowercasePassword;
    private String withLowercasePassword;
    private List<String> failures;

    @BeforeEach
    void setUp() {
        failures = new ArrayList<>();
        shortPassword = "queen";
        validPassword = "queenthebest123@";
        noUppercasePassword = "queenthebest123";
        withUppercasePassword = "QueenTheBest123";
        noLowercasePassword = "QUEENTHEBEST123@";
        withLowercasePassword = "QueenTheBest123@";
        passwordService = new PasswordService();
    }

    @Test
    @DisplayName("Validate that a password with 8 or more characters does not generate an error message")
    void testValidatePasswordLengthForValidPassword() {
        assertFalse(validPassword.length() < 8, "A senha de teste deve ter 8 caracteres ou mais.");

        failures = passwordService.validatePass(validPassword);

        assertFalse(failures.contains(Messages.PASSWORD_MIN_LENGTH), "A senha não deve possuir a mensagem de erro.");
    }

    @Test
    @DisplayName("Validate that a password with fewer than 8 characters generates an error message")
    void testValidatePasswordLengthForShortPassword() {
        assertTrue(shortPassword.length() < 8, "A senha de teste deve ter menos de 8 caracteres.");

        failures = passwordService.validatePass(shortPassword);

        assertTrue(failures.contains(Messages.PASSWORD_MIN_LENGTH), "A senha deve possuir pelo menos 08 caracteres..");
    }

    @Test
    @DisplayName("Validate that a password without uppercase letters generates an error message")
    void testValidateUppercaseForNoUppercasePassword() {
        failures = passwordService.validatePass(noUppercasePassword);

        assertTrue(failures.contains(Messages.PASSWORD_UPPERCASE),
                "A senha deve possuir pelo menos uma letra maiúscula.");
    }

    @Test
    @DisplayName("Validate that a password with uppercase letters does not generate an error message")
    void testValidateUppercaseForWithUppercasePassword() {
        failures = passwordService.validatePass(withUppercasePassword);

        assertFalse(failures.contains(Messages.PASSWORD_UPPERCASE),
                "A senha não deve gerar uma mensagem de erro por falta de letras maiúsculas.");
    }
}
