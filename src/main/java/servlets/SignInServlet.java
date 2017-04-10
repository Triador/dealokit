package servlets;

import base.AccountService;
import base.Context;
import base.DBService;
import dbService.dataSets.UsersDataSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by antonandreev on 08/04/2017.
 */
public class SignInServlet extends HttpServlet {
    private DBService dbService;
    private AccountService accountService;

    public SignInServlet(Context context) {
        dbService = context.get(DBService.class);
        accountService = context.get(AccountService.class);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String sessionId = request.getSession().getId();

        if (login == null || password == null || sessionId == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UsersDataSet dataSet = dbService.getUser(login);

        if (dataSet == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (!dataSet.getPassword().equals(password)) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        accountService.addSession(sessionId, dataSet);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Рады вас снова видеть" + dataSet.getLogin());
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
