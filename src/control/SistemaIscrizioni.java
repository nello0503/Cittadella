package control;

import entity.Studente;

import java.util.ArrayList;
import java.util.List;
import entity.Attivita;

public class SistemaIscrizioni {

	private List<Studente> listaStudenti;
	private List<Attivita> listaAttivita;

	public SistemaIscrizioni() {
		this.listaStudenti = new ArrayList<>();
		this.listaAttivita = new ArrayList<>();
	}

	public void addStudente(Studente s) {
		if (esisteStudente(s.getNome(), s.getCognome())) {
			System.out.println("Matricola già presente nel sistema!");
		} else {
			this.listaStudenti.add(s);
			System.out.println("Nuovo studente iscritto: " + s.getNome() + " " + s.getCognome() + "\n");
		}

	}

	public Studente cercaStudente(int matricola) {
		for (Studente s : listaStudenti) {
			if (s.getMatricola() == matricola) {
				return s;
			}
		}
		return null;
	}

	public void addAttivita(Attivita a) {
		if (!listaAttivita.contains(a)) {
			this.listaAttivita.add(a);
		} else {
			System.out.println("Attività gia presente nel sistema");
		}

	}

	public boolean esisteStudente(String nome, String cognome) {
		for (Studente s : listaStudenti) {
			if (s.getNome().equals(nome) && s.getCognome().equals(cognome))
				return true;
		}
		return false;
	}

	public List<Attivita> getListaAttivita() {
		return this.listaAttivita;
	}

	public List<Studente> getListaStudenti() {
		return this.listaStudenti;
	}

}
