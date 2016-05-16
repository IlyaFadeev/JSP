package entity;

import oracle.jdbc.pool.OracleDataSource;

import javax.ejb.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

/**
 * Created by Fadeev on 4/23/2016.
 */
public class PersonBean implements EntityBean {

    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Date hiredate;
    private Integer sal;
    private Integer comm;
    private Integer deptno;

    private EntityContext entityContext;
    private DataSource oracleDataSource;
    private DataSource dataSource;


    @Override
    public void setEntityContext(EntityContext entityContext) throws EJBException {
        this.entityContext = entityContext;
        Context ctx = null;
        try {
            ctx = new InitialContext();
            oracleDataSource = (javax.sql.DataSource)ctx.lookup("jdbc/OracleConnPool");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unsetEntityContext() throws EJBException {
        entityContext = null;
    }

    @Override
    public void ejbRemove() throws RemoveException, EJBException {

    }

    @Override
    public void ejbActivate() throws EJBException {

    }

    @Override
    public void ejbPassivate() throws EJBException {

    }

    @Override
    public void ejbLoad() throws EJBException {
        empno = (Integer) entityContext.getPrimaryKey();
        Connection connection = null;
        try {
            connection = oracleDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM EMP WHERE EMPNO = ?");
            preparedStatement.setInt(1, empno);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;

        try {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setEmpno(resultSet.getInt("EMPNO"));
                setEname(resultSet.getString("ENAME"));
                setJob(resultSet.getString("JOB"));
                setMgr(resultSet.getInt("MGR"));
                setHiredate(resultSet.getDate("HIREDATE"));
                setSal(resultSet.getInt("SAL"));
                setComm(resultSet.getInt("COMM"));
                setDeptno(resultSet.getInt("DEPTNO"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void ejbStore() throws EJBException {

    }


    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Integer getSal() {
        return sal;
    }

    public void setSal(Integer sal) {
        this.sal = sal;
    }

    public Integer getComm() {
        return comm;
    }

    public void setComm(Integer comm) {
        this.comm = comm;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }


    public Integer ejbFindByPrimaryKey(Integer primaryKey) throws FinderException {
        Connection connection = null;
        try {
            connection = oracleDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM EMP WHERE EMPNO = ?");
            preparedStatement.setInt(1, primaryKey);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;

        try {
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new ObjectNotFoundException();
            }

            return primaryKey;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public Integer ejbCreate(String lastName, String firstName, String passport) throws CreateException {
        return null;
    }


    public void ejbPostCreate(String lastName, String firstName, String passport) throws CreateException {

    }

    public Integer ejbFindByFirstName(String name) throws FinderException {
        Connection connection = null;
        try {
            connection = oracleDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM EMP WHERE ENAME = ?");
            preparedStatement.setString(1, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;

        try {
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new ObjectNotFoundException();
            }

            return resultSet.getInt("EMPNO");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Collection ejbFindAll() throws FinderException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = oracleDataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM EMP");
            ResultSet resultSet = statement.executeQuery();
            Vector keys = new Vector();
            while (resultSet.next()) {
                Integer empNo = resultSet.getInt("EMPNO");
                keys.addElement(empNo);
            }
            resultSet.close();
            return keys;
        } catch (SQLException e) {
            throw new EJBException("?????? SELECT");
        } finally {
            try {
                connection.close();
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
