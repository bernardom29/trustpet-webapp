package web;

import beans.FacadeBeans;
import com.google.gson.Gson;
import main.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "ConsultarPerfilServlet", urlPatterns = {"/ConsultarPerfil"})
public class ConsultarPerfilServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        JSONObject mensagem = new JSONObject();
        String token = request.getHeader("Token");
        String email = FacadeBeans.validarToken(token);

        if (email != null) {
            consultarPerfil(mensagem, email);
        } else {
            mensagem.put("success", false);
        }
        out.print(mensagem);
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        JSONObject mensagem = new JSONObject();
        JSONObject parameters = Util.parseBody(request.getReader());

        String token = request.getHeader("Token");
        String email = FacadeBeans.validarToken(token);
        String emailConsulta = (String) parameters.get("emailConsulta");

        if (email != null) {
            consultarPerfil(mensagem, emailConsulta);
        } else {
            mensagem.put("success", false);
        }

        out.print(mensagem);
        out.flush();
    }

    private void consultarPerfil(JSONObject mensagem, String emailConsulta) {
        String tipo = FacadeBeans.tipoUtilizador(emailConsulta);

        if (tipo != null) {
            Utilizador utilizador = FacadeBeans.consultarPerfil(emailConsulta, tipo);
            if (utilizador != null) {
                List<Review> reviewsList = FacadeBeans.consultarReviews(emailConsulta, tipo);
                mensagem.put("reviews", Util.parseReviews(reviewsList));

                if (tipo.equals("petsitter")) {
                    Petsitter petsitter = (Petsitter) utilizador;
                    mensagem.put("utilizador", Util.parsePetsitter(petsitter));
                    mensagem.put("servicos", Util.parseServicosPetsitterMap(FacadeBeans.getServicosPetsitter(emailConsulta)));
                } else if (tipo.equals("dono")) {
                    Dono dono = (Dono) utilizador;
                    mensagem.put("utilizador", Util.parseDono(dono));
                }
                mensagem.put("success", true);
            } else {
                mensagem.put("success", false);
            }
        } else {
            mensagem.put("success", false);
        }
    }
}