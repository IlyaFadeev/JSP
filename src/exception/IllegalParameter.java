package exception;

/**
 * Created by Fadeev on 09.11.2015.
 */
public class IllegalParameter extends IndexOutOfBoundsException { // ����������, ������������� ��� �������� ���������

    public IllegalParameter(String message){
        super(message);
    }
}
