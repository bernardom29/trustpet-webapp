package web;

import beans.FacadeBeans;
import main.Administrador;
import main.FacadeDAOs;
import org.json.JSONObject;
import org.orm.PersistentException;
import org.orm.PersistentSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "InativarUtilizadorServlet", urlPatterns = {"/InativarUtilizador"})
public class InativarUtilizadorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        JSONObject mensagem = new JSONObject();
        JSONObject parameters = Util.parseBody(request.getReader());

        boolean autenticacao = FacadeBeans.autenticarAdministrador((String) parameters.get("email"), Util.hash((String) parameters.get("password")));
        if(autenticacao) {
            boolean result = FacadeBeans.editarDados(null, (String) parameters.get("email"), null, null, false, null, null, null, null, null, false);
            mensagem.put("success", result);
        }
        else {
            mensagem.put("success", false);
        }
        out.print(mensagem);
        out.flush();
    }
}