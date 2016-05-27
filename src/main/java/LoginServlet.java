import store.UsersRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usr = req.getParameter("user");
        String psw = req.getParameter("pass");
        String addr = req.getParameter("addr");

        UsersRepository storage = new UsersRepository();
        storage.addUser(usr,psw,addr);
        if(storage.searchUser(usr,psw)==true){
            resp.sendRedirect(getServletContext().getContextPath() + "/homePage.jsp");
        }else
            resp.sendRedirect(getServletContext().getContextPath() + "/login.jsp");

    }
}
