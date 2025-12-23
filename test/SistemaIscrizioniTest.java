import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import control.SistemaIscrizioni;
import entity.Studente;
import entity.Attivita;

public class SistemaIscrizioniTest {

	private SistemaIscrizioni sistema;
	private Attivita nuoto;
	private List<Attivita> listaAttivita;
	private Studente s1;
	
	@Before
	public void setup() {
		 sistema = new SistemaIscrizioni();
		 
	     nuoto = new Attivita("Nuoto", 50.0);
	     
	     sistema.addAttivita(nuoto);
	     listaAttivita = new ArrayList<Attivita>();
	     listaAttivita.add(nuoto);
	     s1 = new Studente("Aniello", "Matrisciano", listaAttivita);
	     sistema.addStudente(s1);
		
		
	}
	@Test
	public void addStudenteTest() {
		int sizeBefore = sistema.getListaStudenti().size();
		Studente nuovo = new Studente("Mario", "Rossi", listaAttivita);
	    sistema.addStudente(nuovo);	
		assertNotNull(sistema.cercaStudente(nuovo.getMatricola()));
		assertEquals(sizeBefore + 1, sistema.getListaStudenti().size());
	}
	
	@Test
	public void cercaStudenteTest_RitornaStudente() {
		
		Studente trovato = sistema.cercaStudente(s1.getMatricola());
		assertNotNull(trovato);	
	}
	
	@Test
	public void cercaStudenteTest_RitornaNull() {
		
		Studente trovato = sistema.cercaStudente(2);
		assertNull(trovato);	
	}
	
	@Test
	public void addAttivitaTest() {	
		assertTrue(sistema.getListaAttivita().contains(nuoto));	
	}
	
	@Test
	public void esisteStudenteTest_RitornaTrue() {	
		Studente s1 = new Studente("Giuseppe", "Rossi", listaAttivita);
		sistema.addStudente(s1);
		
	    boolean risultato = sistema.esisteStudente("Giuseppe", "Rossi");
	    assertTrue(risultato);
		
	}
	
	@Test
	public void esisteStudenteTest_RitornaFalse() {	
		Studente s1 = new Studente("Giuseppe", "Rossi", listaAttivita);
		sistema.addStudente(s1);
		
	    boolean risultato = sistema.esisteStudente("Giuseppe", "Bianchi");
	    assertFalse(risultato);	
	}
	
	
	
	

}
