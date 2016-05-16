package entity;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import java.sql.Date;

/**
 * Created by Fadeev on 4/23/2016.
 */
public interface Person extends EJBObject {
    public Integer getEmpno() throws RemoteException;

    public void setEmpno(Integer empno) throws RemoteException;

    public String getEname() throws RemoteException;

    public void setEname(String ename) throws RemoteException;

    public String getJob() throws RemoteException;

    public void setJob(String job) throws RemoteException;

    public Integer getMgr() throws RemoteException;

    public void setMgr(Integer mgr) throws RemoteException;

    public Date getHiredate() throws RemoteException;

    public void setHiredate(Date hiredate) throws RemoteException;

    public Integer getSal() throws RemoteException;

    public void setSal(Integer sal) throws RemoteException;

    public Integer getComm() throws RemoteException;

    public void setComm(Integer comm) throws RemoteException;

    public Integer getDeptno() throws RemoteException;

    public void setDeptno(Integer deptno) throws RemoteException;
}
