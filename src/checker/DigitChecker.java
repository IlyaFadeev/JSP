package checker;

/**
 * Created by Fadeev on 02.11.2015.
 */
public class DigitChecker { // Проверяет, является ли переданная в метод строка числом или нет

    public static boolean isDigit(String value){
        try {
            Integer.parseInt(value);
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
}

}
