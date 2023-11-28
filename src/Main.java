public class Main {
    private static final String VALIDATE_PATTERN = "^[a-zA-Z0-9-_]+$";

    public static void main(String[] args) throws WrongLoginException, WrongPasswordException {
        check("Login", "pass", "pass");
        check("loginlogin", "pass1", "pass");
        check("login%XX*", "pass", "pass");
        check("Login", "passssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss", "passssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        check("Login", "pass$$", "pass$$");

    }

    private static boolean check(String login, String password, String confirmPassword) throws WrongLoginException, WrongPasswordException {
        boolean isValid = true;
        try {
            checkLogin(login);
            checkPasswordAndConfirmPassword(password, confirmPassword);
        } catch (WrongLoginException e) {
            System.out.println("Ошибка с введенным логином " + e.getMessage());
            isValid = false;
        } catch (WrongPasswordException e) {
            System.out.println("Ошибка с введенным паролем " + e.getMessage());
            isValid = false;
        }
        if (isValid) {
            System.out.println("Логин и пароль корректны");
        }

        return isValid;
    }

    private static void checkLogin(String login) throws WrongLoginException {
        if (!login.matches(VALIDATE_PATTERN)) {
            throw new WrongLoginException("Логин должен содержать в себе только латинские буквы, цифры и знак подчеркивания.");
        } else if (login.length() > 20) {
            throw new WrongLoginException("Логин должен быть равен или меньше 20 символов");
        }
    }

    private static void checkPasswordAndConfirmPassword(String password, String confirmPassword) throws WrongPasswordException {
        if (!password.matches(VALIDATE_PATTERN)) {
            throw new WrongPasswordException("Пароль должен содержать в себе только латинские буквы, цифры и знак подчеркивания.");
        } else if (password.length() > 20) {
            throw new WrongPasswordException("Пароль должен быть равен или меньше 20 символов");
        } else if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Пароли должны совпадать");
        }
    }
}