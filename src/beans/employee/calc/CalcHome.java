package beans.employee.calc;

import beans.employee.EmpComp;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import java.rmi.RemoteException;

/**
 * Created by Fadeev on 5/12/2016.
 */
public interface CalcHome extends EJBHome {
    CalcComp create() throws CreateException, RemoteException;
}
