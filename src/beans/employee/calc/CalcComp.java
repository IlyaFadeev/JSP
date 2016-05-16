package beans.employee.calc;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

/**
 * Created by Fadeev on 5/12/2016.
 */
public interface CalcComp extends EJBObject {
    public void save() throws RemoteException;
    public void extractData() throws RemoteException;
    public void reset() throws RemoteException;
    public float add() throws RemoteException;
    public float minus() throws RemoteException;
    public float multiply() throws RemoteException;
    public float division() throws RemoteException;
    public float getRes(String operation) throws RemoteException;
    public float getA() throws RemoteException;
    public void setA(float a) throws RemoteException;
    public float getB() throws RemoteException;
    public void setB(float b) throws RemoteException;
    public float getResult() throws RemoteException;
    public void setResult(float result) throws RemoteException;
    public boolean isMemoryReset() throws RemoteException;
    public void setIsMemoryReset(boolean isMemoryReset) throws RemoteException;

}
