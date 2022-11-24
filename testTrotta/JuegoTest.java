package testTrotta;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import avion.AvionSimple;
import copControl.Dificultad;
import copControl.Juego;
import copControl.Jugador;
import copControl.Mapa;
import copControl.Nivel;
import copControl.Posicion;
import pista.Helipuerto;
import pista.Pista;
import pista.PistaSimple;

public class JuegoTest {
	

	private Mapa mapaDeJuego = new Mapa();
	
	// Niveles de Dificultad
	
	private Dificultad dificultadNivelUno;
	private Dificultad dificultadNivelDos;
	private Dificultad dificultadNivelTres;
	
	// --- Niveles 
	
	private Nivel nivelUno;
	private Nivel nivelDos;
	private Nivel nivelTres;
	
	private List<Nivel> niveles;
	
	// --- Aviones
	
	private AvionSimple aSimple;		// Hace referencia a un Avion Simple
	private AvionSimple aSimple2;		// Hace referencia a un Avion Simple
	
	private Posicion inicial;
	private Posicion fin;
		
	//--- Pistas
	
	private Pista pista;
	private Pista pista2;
	private List <Pista> pistas;
	
	// -----------
	
	private Jugador jugador;
	Juego juego;
	

	@BeforeEach
	void setUp() throws Exception {
		
		dificultadNivelUno = new Dificultad (3,3,1);
		dificultadNivelDos = new Dificultad (3,4,2);
		dificultadNivelTres = new Dificultad (4,5,3);

		pista = new PistaSimple (new Posicion (5,5)); 
		pista2 = new Helipuerto (new Posicion (3,1));
		
		pistas = new ArrayList<Pista>();
		
		pistas.add(pista);
		pistas.add(pista2);
		
		nivelUno = new Nivel (mapaDeJuego, dificultadNivelUno);
		nivelDos = new Nivel (mapaDeJuego, dificultadNivelDos);
		nivelTres = new Nivel (mapaDeJuego, dificultadNivelTres);
		
		niveles = new ArrayList<Nivel> ();
		
		niveles.add(nivelUno);
		niveles.add(nivelDos);
		niveles.add(nivelTres);
			
		 inicial = new Posicion (1,1);
		 fin = new Posicion (5,5);
		
		 aSimple = new AvionSimple(inicial,fin,mapaDeJuego);
		 
		 jugador = new Jugador ("Jugador 1");
		 juego = new Juego (jugador, niveles);
 }

	@Test
	void hayUnAvionEnElMapa() {
		
		mapaDeJuego.colocarAvionEnAire(aSimple);
		
		assertEquals(mapaDeJuego.tieneAvionesVolando(),true);
		
	}
	
	@Test
	
	void estaEnElNivelUno() {

		Nivel nivelActual = juego.getNivelActual();
		
		assertTrue(nivelActual == nivelUno,"Error - El juego debería estar en el primer nivel");
		
	}
	
	@Test
	
	void noAterrizoNingunAvion() {
		
		mapaDeJuego.colocarAvionEnAire(aSimple);
		mapaDeJuego.colocarAvionEnAire(aSimple2);
		
		int avionesAterrizados = juego.getCantidadAvionesAterrizados();
		
		assertTrue(avionesAterrizados == 0,"Error - Aún no aterrizo ningun avión");	// No debería haber ningún avión volando.
		
	}
	
	@Test
	
	void elJuegoFinalizo () {
				
		assertEquals(juego.esJuegoGanado(), true);		// El juego finalizó.
		
	}
	
	@Test
	
	void siguienteNivel() {
		
		juego.avanzarNivel();
		Nivel nivelActual = juego.getNivelActual();
		
		assertFalse(nivelActual == nivelUno, "no debería encontrarse en el Nivel Uno.");	// Debería encontrarse en un nivel distinto al primer nivel.
		
	}
	

}