package checker;

/**
 * Created by Fadeev on 02.11.2015.
 */
public class DigitChecker { // ���������, �������� �� ���������� � ����� ������ ������ ��� ���

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
