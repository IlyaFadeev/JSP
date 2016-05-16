package entity;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import java.rmi.RemoteException;
import java.util.Collection;

/**
 * Created by Fadeev on 4/23/2016.
 */
public interface PersonHome extends EJBHome {
    public Person create(String lastName,
                         String firstName, String passport)
            throws CreateException, RemoteException;

    public Person findByPrimaryKey(Integer primaryKey)
            throws RemoteException, FinderException;

    public Person findByFirstName(String name)
            throws RemoteException, FinderException;

    public Collection findAll()
            throws RemoteException, FinderException;

}
