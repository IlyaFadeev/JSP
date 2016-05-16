package beans.employee;

import employee.Employee;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.List;

/**
 * Created by Fadeev on 5/12/2016.
 */
public interface EmpComp extends EJBObject {
    public Connection getConnection() throws RemoteException;
    public List<Employee> searchEmpById(int id) throws RemoteException;
    public List<Employee> searchEmpByName(String name) throws RemoteException;
    public List<Employee> getAllEmp() throws RemoteException;
}
