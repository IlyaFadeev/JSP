package exception;

/**
 * Created by Fadeev on 09.11.2015.
 */
public class IllegalParameter extends IndexOutOfBoundsException { // Исключение, выбрасываемое при неверном параметре

    public IllegalParameter(String message){
        super(message);
    }
}
