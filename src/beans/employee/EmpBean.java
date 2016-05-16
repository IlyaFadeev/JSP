package beans.employee;

import employee.Employee;

import javax.ejb.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Fadeev on 5/12/2016.
 */

public class EmpBean implements SessionBean {

    private javax.sql.DataSource oracleDataSource;
    private static Logger log = Logger.getLogger(EmpBean.class.getName());
    public EmpBean() throws NamingException {
        Context ctx = null;
        try {
            ctx = new InitialContext();
            oracleDataSource = (javax.sql.DataSource)ctx.lookup("jdbc/OracleConnPool");
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }


    public Connection getConnection()  {
        Connection connection = null;

        //try {

        try {
            connection = oracleDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*} catch (SQLException e) {
            e.printStackTrace();
        }*/
        return connection;
    }


    public List<Employee> searchEmpById(int id){ // Поиск работников по их номерам
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        List<Employee> employees = new ArrayList<>();
        Employee employee = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM EMP WHERE EMPNO = ?"); // Формируется sql - запрос
            preparedStatement.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        ResultSet resultSet = null;

        try {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){ // Происходит выборка данных из ответа базы данных
                employee = new Employee();
                employee.setNumber(resultSet.getInt("EMPNO"));
                employee.setName(resultSet.getString("ENAME"));
                employee.setJob(resultSet.getString("JOB"));
                employee.setMgr(resultSet.getInt("MGR"));
                employee.setHiredate(resultSet.getDate("HIREDATE"));
                employee.setSalary(resultSet.getInt("SAL"));
                employee.setComm(resultSet.getInt("COMM"));
                employee.setDeptno(resultSet.getInt("DEPTNO"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return employees;
    }


    public List<Employee> searchEmpByName(String name)  { // Поиск работников по их номерам
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        List<Employee> employees = new ArrayList<>();
        Employee employee = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM EMP WHERE ENAME = ?");
            preparedStatement.setString(1, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;

        try {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                employee = new Employee();
                employee.setNumber(resultSet.getInt("EMPNO"));
                employee.setName(resultSet.getString("ENAME"));
                employee.setJob(resultSet.getString("JOB"));
                employee.setMgr(resultSet.getInt("MGR"));
                employee.setHiredate(resultSet.getDate("HIREDATE"));
                employee.setSalary(resultSet.getInt("SAL"));
                employee.setComm(resultSet.getInt("COMM"));
                employee.setDeptno(resultSet.getInt("DEPTNO"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
                log.info("Connection close!"); // Закрывается соединение с базой данных
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return employees;
    }

    public List<Employee> getAllEmp(){ // Выборка всех сотрудников
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        List<Employee> employees = new ArrayList<>();
        Employee employee = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM EMP");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        ResultSet resultSet = null;

        try {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                employee = new Employee();
                employee.setNumber(resultSet.getInt("EMPNO"));
                employee.setName(resultSet.getString("ENAME"));
                employee.setJob(resultSet.getString("JOB"));
                employee.setMgr(resultSet.getInt("MGR"));
                employee.setHiredate(resultSet.getDate("HIREDATE"));
                employee.setSalary(resultSet.getInt("SAL"));
                employee.setComm(resultSet.getInt("COMM"));
                employee.setDeptno(resultSet.getInt("DEPTNO"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return employees;
    }







    public void ejbCreate() {}


    public void setSessionContext(SessionContext sessionContext) {

    }

    public void ejbRemove() throws EJBException {

    }

    public void ejbActivate() throws EJBException {

    }

    public void ejbPassivate() throws EJBException {

    }
}
