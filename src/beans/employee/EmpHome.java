package beans.employee;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import java.rmi.RemoteException;

/**
 * Created by Fadeev on 5/12/2016.
 */
public interface EmpHome extends EJBHome {
    EmpComp create() throws CreateException, RemoteException;
}
