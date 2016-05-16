package servlets;

import beans.employee.EmpComp;
import beans.employee.EmpHome;
import checker.DigitChecker;
import employee.Employee;
import entity.Person;
import entity.PersonHome;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Fadeev on 25.10.2015.
 */
public class DataBaseServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(DataBaseServlet.class.getName());
    private EmpComp emp;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        try {
            InitialContext initialContext = new InitialContext();
            Object obj = initialContext.lookup("ejb/emp");
            EmpHome empHome = (EmpHome) PortableRemoteObject.narrow(obj, EmpHome.class);
            emp = empHome.create();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (CreateException e) {
            e.printStackTrace();
        }


        Person person = null;
        PersonHome personHome = null;
        InitialContext initialContext = null;
        try {
            initialContext = new InitialContext();
            personHome = (PersonHome) initialContext.lookup("ejb/person");
            person = personHome.findByFirstName("1");
            String name = person.getEname();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (FinderException e) {
            e.printStackTrace();
        }


        Collection<Person> employees = null;
        try {
            employees = personHome.findAll();
        } catch (FinderException e) {
            e.printStackTrace();
        }
        String parameter = req.getParameter("value");
        String show = req.getParameter("show");
        String showAll = req.getParameter("showAll");

        if (showAll != null && showAll.equals("show all")) {
            try {
                employees = personHome.findAll();
            } catch (FinderException e) {
                e.printStackTrace();
            }
        }

        if (show != null && show.equals("show")) {
            if (parameter != null) {
                if (DigitChecker.isDigit(parameter)) try {
                    person = personHome.findByPrimaryKey(Integer.parseInt(parameter));
                    employees.clear();
                    employees.add(person);
                } catch (FinderException e) {
                    e.printStackTrace();
                }
                else try {
                    person = personHome.findByFirstName(parameter);
                    employees.clear();
                    employees.add(person);
                } catch (FinderException e) {
                    e.printStackTrace();
                }
            }
        }

        List<Person> persons = new ArrayList<>();
        persons.addAll(employees);
        session.setAttribute("employees", persons);
        session.setAttribute("par", parameter);


        req.getRequestDispatcher("emp.jsp").forward(req, resp);


    }
}
