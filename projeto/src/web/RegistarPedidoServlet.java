package web;

import beans.FacadeBeans;
import com.google.gson.Gson;
import main.*;
import org.json.JSONArray;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet(name = "RegistarPedidoServlet", urlPatterns = {"/RegistarPedido"})
public class RegistarPedidoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        JSONObject mensagem = new JSONObject();
        JSONObject parameters = Util.parseBody(request.getReader());

        Date dataInicio = Util.parseDate((String) parameters.get("dataInicio"), "dd/MM/yyyy HH:mm");
        Date dataFim = Util.parseDate((String) parameters.get("dataFim"), "dd/MM/yyyy HH:mm");
        JSONArray animais = (JSONArray) parameters.get("animais");

        String token = request.getHeader("Token");
        String email = FacadeBeans.validarToken(token);

        if (email!= null && dataInicio != null && dataFim != null && animais != null) {
            int idPedido;
            if(parameters.keySet().contains("idPedido") && !(parameters.get("idPedido").equals(""))) {
                // Edição de pedido
                idPedido = FacadeBeans.editarPedido(Integer.parseInt((String) parameters.get("idPedido")),dataInicio,dataFim);
            }
            else {
                // Novo pedido
                idPedido = FacadeBeans.registarPedido(email, dataInicio, dataFim);
            }

            if (idPedido != -1) {
                // Calcular serviços
                List<Integer> idAnimal = new ArrayList<>();
                for(int i = 0; i < animais.length(); i++) {
                    idAnimal.add(animais.getInt(i));
                }

                Map<Animal, List<Servico>> servicos = FacadeBeans.getServicosPedido(idAnimal);
                JSONArray servicosArray = Util.parseAnimalServicosMap(servicos);

                mensagem.put("servicos",servicosArray);
                mensagem.put("idPedido", idPedido);
                mensagem.put("success", true);
            } else {
                mensagem.put("success", false);
            }
        } else {
            mensagem.put("success", false);
        }

        out.print(mensagem);
        out.flush();
    }
}