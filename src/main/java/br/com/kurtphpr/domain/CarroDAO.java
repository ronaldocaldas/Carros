package br.com.kurtphpr.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

@Component
public class CarroDAO extends HibernateDAO<Carro> {

	public CarroDAO() {
		super(Carro.class);
	}

	// Consulta carro pelo Id
	public Carro getCarroById(Long id) {
		//O hibernate consulta automaticamente o pelo Id
		return super.get(id);
	}

	//Busca um carro pelo nome 
	public List<Carro> findByName(String nome) {
		Query q = getSession().createQuery("from Carro where lower(nome) like lower(?)");
		q.setString(0, "%" + nome + "%");
		return q.list();
	}

	//Busca carro pelo tipo
	public List<Carro> findByTipo(String tipo) {
		Query q = getSession().createQuery("from Carro where tipo=?");
		q.setString(0, tipo);
		return q.list();
	}

	//Consulta todos os carros
	public List<Carro> getCarros() {
		Query q = getSession().createQuery("from Carro");
		List<Carro> carros  = q.list();
		return carros;
	}

	//Insere ou atualiza um carro
	public void salvar(Carro c){
		super.save(c);
	}

	//Deleta o carro pelo Id
	public boolean delete(Long id) {
		Carro c = get(id);
		delete(c);
		return true;
	}
}
