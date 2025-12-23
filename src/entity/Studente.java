package entity;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Studente {

	private StringProperty nome = new SimpleStringProperty();
	private StringProperty cognome = new SimpleStringProperty();
	private final IntegerProperty matricola;
	private List<Attivita> listaAttivita;
	private static int nextMatricola = 0;

	public Studente(String nome, String cognome, List<Attivita> attivita) {
		
		this.nome = new SimpleStringProperty(nome);
		this.cognome = new SimpleStringProperty(cognome);
		this.matricola = new SimpleIntegerProperty(++nextMatricola);
		this.listaAttivita = new ArrayList<>(attivita);
	}

	public String getNome() {
		return nome.get();
	}

	public String getCognome() {
		return cognome.get();
	}

	public int getMatricola() {
		return matricola.get();
	}

	public StringProperty nomeProperty() {
		return nome;
	}

	public StringProperty cognomeProperty() {
		return cognome;
	}

	public IntegerProperty matricolaProperty() {
		return matricola;
	}

	public List<Attivita> getListaAttivita() {
		return listaAttivita;
	}

	public String printListaAttivita() {
		if (listaAttivita == null || listaAttivita.isEmpty()) {
			return "Nessuna attività";
		}
		StringBuilder sb = new StringBuilder();
		for (Attivita a : listaAttivita) {
			sb.append("• ").append((a.getNome())).append("\n");
		}
		return sb.toString();
	}

	public double calcolaRetta() {
		double totale = 0.0;
		for (Attivita a : this.listaAttivita) {
			totale += a.getCostoMensile();
		}
		return totale;
	}

}
