package validation;

public class CommonValidations {
    private CommonValidations() {
    }

    public static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}