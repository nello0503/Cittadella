package entity;

public class Attivita {

	private String nome;
	private double costoMensile;

	public Attivita(String nome, double costoMensile) {
		super();
		this.nome = nome.toUpperCase();
		this.costoMensile = costoMensile;
	}

	public double getCostoMensile() {
		return costoMensile;
	}

	public void setCostoMensile(double costoMensile) {
		this.costoMensile = costoMensile;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}

}
