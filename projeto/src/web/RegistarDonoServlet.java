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
import java.util.Date;

@WebServlet(name = "RegistarDonoServlet", urlPatterns = {"/RegistarDono"})
public class RegistarDonoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        JSONObject mensagem = new JSONObject();
        JSONObject parameters = Util.parseBody(request.getReader());

        Date date = Util.parseDate((String) parameters.get("dataNasc"),"dd/MM/yyyy");
        if(date != null) {
            String email = (String) parameters.get("email");
            boolean result = FacadeBeans.registarUtilizador((String) parameters.get("nome"), email, date, (String) parameters.get("contacto"), Boolean.parseBoolean((String) parameters.get("jardim")), (String) parameters.get("morada"), Util.hash((String) parameters.get("password")), (String) parameters.get("avatar"), "dono", (String) parameters.get("concelho"), (String) parameters.get("distrito"));

            if (result) {
                String token = FacadeBeans.setToken(email);
                mensagem.put("token", token);
                mensagem.put("tipo","dono");
            }

            mensagem.put("success", result);
        }
        else {
            // Data inválida
            mensagem.put("success", false);
        }

        out.print(mensagem);
        out.flush();
    }

}