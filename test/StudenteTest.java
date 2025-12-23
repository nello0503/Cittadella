import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


import entity.Attivita;
import entity.Studente;

public class StudenteTest {
	
	private Attivita nuoto;
	private Attivita calcio;
	private List<Attivita> lista;
	private Studente s1;
	

	@Before
	public void setUp() throws Exception {
		nuoto = new Attivita("Nuoto", 50.0);
		calcio = new Attivita("Calcio", 30.0);
		lista = new ArrayList<>();
		lista.add(nuoto);
		lista.add(calcio);
		s1 = new Studente("Aniello", "Matrisciano", lista);
						
	}

	@Test
	public void calcolaRettaTest() {
		assertEquals(80.0, s1.calcolaRetta(), 0.0001);
		
	}

}
