package servlets;

import beans.employee.EmpComp;
import beans.employee.EmpHome;
import checker.DigitChecker;
import employee.Employee;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        }  catch (CreateException e) {
            e.printStackTrace();
        }

        List<Employee> employees = emp.getAllEmp();
        String parameter = req.getParameter("value");
        String show = req.getParameter("show");
        String showAll = req.getParameter("showAll");


        if (showAll != null &&  showAll.equals("show all")) {
            employees = emp.getAllEmp();
        }

        if (show != null && show.equals("show")) {
            if (parameter != null) {
                if (DigitChecker.isDigit(parameter)) employees = emp.searchEmpById(Integer.parseInt(parameter));
                else employees = emp.searchEmpByName(parameter);
            }
        }

        session.setAttribute("employees", employees);
        session.setAttribute("par", parameter);





        req.getRequestDispatcher("emp.jsp").forward(req, resp);



    }
}
