package org.example;

import org.example.exceptions.WrongLoginException;
import org.example.exceptions.WrongPasswordException;

public class Main {

    private static final String VALIDATE_PATTERN = "^[a-zA-z0-9_]+$";

    public static void main(String[] args) {

        validate("login", "pass", "pass");
        validate("loginloginloginloginloginloginloginloginlogin", "pass", "pass");
        validate("login", "pass", "pass2");
        validate("login", "passpasspasspasspasspasspasspasspasspasspasspasspasspass", "pass");
        validate("login%%", "pass", "pass");

    }

    public static Boolean validate(String login, String password, String confirmPassword) {

        boolean isValid = true;

        try {
            validateLogin(login);
            validatePassword(password, confirmPassword);

        } catch (WrongLoginException e) {
            System.out.println("Ошибка с введенным логином" + e.getMessage());
            isValid = false;

        } catch (WrongPasswordException e) {
            System.out.println("Ошибка с введенным логином" + e.getMessage());
            isValid = false;

        }

        if (isValid) {
            System.out.println("Логин и пароль корректны");
        }

        return isValid;
    }

    public static void validateLogin(String login) throws WrongLoginException {
        if (!login.matches(VALIDATE_PATTERN)) {
            throw new WrongLoginException("Логин может содержать в себе только латинские буквы, цифры и знак подчеркивания.");
        }
        if (login.length() > 20) {
            throw new WrongLoginException("Логин не может быть больше 20 символов.");
        }

    }

    public static void validatePassword(String password, String confirmPassword) throws WrongPasswordException {
        if (!password.matches(VALIDATE_PATTERN)) {
            throw new WrongPasswordException("Пароль может содержать в себе только латинские буквы, цифры и знак подчеркивания.");
        }
        if (password.length() > 20) {
            throw new WrongPasswordException("ПАроль не может быть больше 20 символов.");
        }
        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Пароли не совпадают.");
        }

    }


}