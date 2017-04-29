package br.com.kurtphpr.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.kurtphpr.domain.Carro;
import br.com.kurtphpr.domain.CarroService;
import br.com.kurtphpr.domain.ListaCarros;
import br.com.kurtphpr.domain.Response;
import br.com.kurtphpr.util.RegexUtil;
import br.com.kurtphpr.util.ServletUtil;

@WebServlet("/carros/*")
public class CarrosServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CarroService carroService = new CarroService();

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Cria o carro
		Carro carro = getCarroFromRequest(req);
		//Salva o carro
		carroService.save(carro);
		//Escreve o JSON com o novo carro salvo 
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(carro);
		ServletUtil.writeJson(resp, json);
		
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
	
		String requestURL = req.getRequestURI();
		Long id = RegexUtil.matchId(requestURL);
		if (id != null) {
			// Informa o ID
			Carro carro = carroService.getCarro(id);
			if (carro != null) {
				escreveObjectJSON(response, carro);
			} else {
				response.sendError(404, "Carro não encontrado!");
			}
	
		} else {
			List<Carro> carros = carroService.getCarros();
			ListaCarros lista = new ListaCarros();
			lista.setCarros(carros);
			/*
			 * // Gera o XML String xml = JAXBUtil.toXML(lista);
			 * ServletUtil.writeXML(response, xml);
			 */
	
			/*
			 * //Gera o JSON String json = JAXBUtil.toJSON(lista);
			 * ServletUtil.writeJson(response, json);
			 */
	
			escreveObjectJSON(response, lista);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse response) throws IOException {
		String requestURL = req.getRequestURI();
		Long id = RegexUtil.matchId(requestURL);
		
		if(id != null){
			carroService.delete(id);
			Response r = Response.Ok("Carro com id="+id+" excluido com sucesso!");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(r);
			ServletUtil.writeJson(response, json);
		}else{
			//URL invalida
			response.sendError(404, "URL inválida!");
		}
		
		
	}
	
	//Lê os parâmetros da request e cria o objeto
	private Carro getCarroFromRequest(HttpServletRequest req) {
		Carro c = new Carro();
		String id  = req.getParameter("id");
		if(id != null){
			//Se informou o id, busca o object do banco de dados 
			c = carroService.getCarro(Long.parseLong(id));
		}
		
		c.setNome(req.getParameter("nome"));
		c.setDesc(req.getParameter("descricao"));
		c.setUrlFoto(req.getParameter("url_foto"));
		c.setUrlVideo(req.getParameter("url_video"));
		c.setLatitude(req.getParameter("latitude"));
		c.setLongitude(req.getParameter("longitude"));
		c.setTipo(req.getParameter("tipo"));
		return c;
		
	}

	private void escreveObjectJSON(HttpServletResponse response, Object object) throws IOException {
		// Gera o JSON com GSON
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(object);

		// Escreve o JSON na response do servlet com application/json
		ServletUtil.writeJson(response, json);
	}
}
