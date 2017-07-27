package servlets;

import base.Context;
import base.DBService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by antonandreev on 08/04/2017.
 */
public class SignUpServlet extends HttpServlet {
    private DBService dbService;

    public SignUpServlet(Context context) {
        dbService = context.get(DBService.class);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        if (name == null || password == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        long id = dbService.insertUser(name, password);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Спасибо за регистрацию " + dbService.getUser(id).getName());
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
