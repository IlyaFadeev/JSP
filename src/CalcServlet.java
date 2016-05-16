import beans.employee.EmpHome;
import beans.employee.calc.CalcComp;
import beans.employee.calc.CalcHome;
import beans.employee.calc.MathBean;

import javax.ejb.CreateException;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.*;

/**
 * Created by Fadeev on 12.10.2015.
 */
public class CalcServlet extends HttpServlet {
    private CalcComp math;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("math") == null) {
            try {
                InitialContext initialContext = new InitialContext();
                Object obj = initialContext.lookup("ejb/math");
                CalcHome calcHome = (CalcHome) PortableRemoteObject.narrow(obj, CalcHome.class);
                math = calcHome.create();
            } catch (NamingException e) {
                e.printStackTrace();
            } catch (CreateException e) {
                e.printStackTrace();
            }
            req.getSession().setAttribute("math", math);
        }
        else {
            math = (CalcComp) req.getSession().getAttribute("math");
        }


        String memoryStatus = "";
        String first = req.getParameter("firstNumber");
        String second = req.getParameter("secondNumber");
        String operation = req.getParameter("operation");

        if (first == null) first = "1";
        if (second == null) second = "1";
        if (operation == null) operation = "+";


        math.setA(Float.parseFloat(first));
        math.setB(Float.parseFloat(second));

        if (operation.equals("save")){
            math.save();
            memoryStatus = "is full";
        }
        else if (operation.equals("extract")){
            math.extractData();
            memoryStatus = "extracted";
    }
        else if (operation.equals("reset")){
            math.reset();
            memoryStatus = "is reset";
        }
        else {

            try {

                req.setAttribute("res", String.valueOf(math.getRes(operation)));

                if (math.isMemoryReset()){
                    memoryStatus = "is reset";
                }
                else {
                    memoryStatus = "is full";
                }

            } catch (Exception e) {
                System.out.println("Enter number!");
            }
        }

        req.getSession().setAttribute("f", math.getA());
        req.getSession().setAttribute("s", math.getB());
        req.getSession().setAttribute("status", memoryStatus);
        req.getSession().setAttribute("math", math);

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
