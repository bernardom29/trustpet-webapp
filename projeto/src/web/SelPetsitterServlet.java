package web;

import beans.FacadeBeans;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SelPetsitterServlet", urlPatterns = {"/SelPetsitter"})
public class SelPetsitterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        JSONObject mensagem = new JSONObject();
        JSONObject parameters = Util.parseBody(request.getReader());
        String email = (String) parameters.get("email");
        int idPedido = Integer.parseInt((String) parameters.get("idPedido"));

        if(email!=null && idPedido != 0) {
            boolean result = FacadeBeans.concluirPedido(email, idPedido);
            mensagem.put("success", result);
        }

        out.print(mensagem);
        out.flush();
    }
}