package servlets;

import javax.print.DocFlavor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fadeev on 03.11.2015.
 */
public class ErrorHandler extends HttpServlet { // Сервлет срабатывает когда возникает какая либо ошибка
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Throwable throwable = (Throwable)
                req.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer)
                req.getAttribute("javax.servlet.error.status_code");
        String servletName = (String)
                req.getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String)
                req.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        // Предыдущий код формирует информацию о произошедшей ошибке
        // Дальше формируется ответ пользователю

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        out.print(statusCode);

        //Записывается в сессию информация об ошибке
        HttpSession session = req.getSession();
        session.setAttribute("message", throwable.getMessage());
        req.getRequestDispatcher("error.jsp").forward(req, resp); // Отображение страницы с информацией пользователю
    }
}
