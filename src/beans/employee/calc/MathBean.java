package beans.employee.calc;

import javax.ejb.*;
import java.rmi.RemoteException;

/**
 * Created by Fadeev on 4/23/2016.
 */
@Stateful
public class MathBean implements SessionBean {

    private float a;
    private float b;
    private float result;
    private float[] memory;
    private boolean isMemoryReset = false;

    public MathBean(){
            }


    public void save(){
        memory = new float[2];
        memory[0] = this.a;
        memory[1] = this.b;
        setIsMemoryReset(false);
    }

    public void extractData(){
        this.a = this.memory[0];
        this.b = this.memory[1];
        setIsMemoryReset(false);
    }

    public void reset(){
        setIsMemoryReset(true);
        memory = null;
    }

    public float add(){
        setResult(a + b);
        return getResult();
    }

    public float minus(){
        return a - b;
    }

    public float multiply(){
        return a * b;
    }

    public float division(){
        return a / b;
    }

    public float getRes(String operation){
        if (operation.equals("+")) return add();
        if (operation.equals("-")) return minus();
        if (operation.equals("*")) return multiply();
        if (operation.equals("/")) return division();

        return Float.MAX_VALUE;
    }




    @Override
    public void setSessionContext(SessionContext sessionContext) throws EJBException {

    }

    @Override
    public void ejbRemove() throws EJBException {

    }

    @Override
    public void ejbActivate() throws EJBException {

    }

    @Override
    public void ejbPassivate() throws EJBException {

    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }


    public boolean isMemoryReset() {
        return isMemoryReset;
    }

    public void setIsMemoryReset(boolean isMemoryReset) {
        this.isMemoryReset = isMemoryReset;
    }

    public void ejbCreate() {}
}
