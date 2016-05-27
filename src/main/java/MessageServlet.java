import store.MessageRepository;
import store.UsersRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("text");
        String addr = req.getParameter("addr");

        MessageRepository storage = new MessageRepository();
        storage.outputMessage(addr,text);
        resp.sendRedirect(getServletContext().getContextPath() + "/homePage.jsp");
    }
}
