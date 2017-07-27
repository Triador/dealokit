package servlets;

import base.AccountService;
import base.Context;
import base.DBService;
import dbService.dataSets.UsersDataSet;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String sessionId = request.getSession().getId();

        if (name == null || password == null || sessionId == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UsersDataSet dataSet = dbService.getUser(name);

        if (dataSet == null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Давай еще");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (!dataSet.getPassword().equals(password)) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        JSONObject jsonObject = new JSONObject();
        if ((dataSet.getParty() == null)) {
            jsonObject.put("user", dbService.getAllUsers());
        } else {
            jsonObject.put("admin", dbService.getAllUsers());
        }
        response.setContentType("text/x-json;charset=UTF-8");
        response.getWriter().write(jsonObject.toJSONString());
        response.getWriter().flush();
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
