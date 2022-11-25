package testTrotta;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import avion.Avion;
import avion.AvionPesado;
import avion.AvionSimple;
import avion.Helicoptero;
import copControl.Dificultad;
import copControl.Juego;
import copControl.Jugador;
import copControl.Mapa;
import copControl.Nivel;
import copControl.Posicion;
import pista.Helipuerto;
import pista.Pista;
import pista.PistaSimple;


	public class NivelTest {
		

		private Mapa mapaDeJuego = new Mapa();
		
		// Niveles de Dificultad
		
		private Dificultad dificultadNivelUno;
		private Dificultad dificultadNivelDos;
		private Dificultad dificultadNivelTres;
		
		// --- Niveles de Juego
		
		private Nivel nivelUno;
		private Nivel nivelDos;
		private Nivel nivelTres;
		
		private List<Nivel> niveles;
		
		// --- Aviones
		
		private AvionSimple aSimple;			// Hace referencia a un Avion Simple
		private Helicoptero helicoptero;		// Hace referencia a un Helicoptero
		private AvionPesado aPesado;			// Hace referencia a un Avion Pesado
		
		private Posicion inicial;
		private Posicion fin;
			
		//--- Pistas
		
		private Pista pista;
		private Pista pista2;
		private List <Pista> pistas;
		
		// -----------
		
		private Jugador jugador;
		private Juego juego;

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
			 aSimple = new AvionSimple(new Posicion (3,1), new Posicion (5,2),mapaDeJuego);
			 aSimple = new AvionSimple(new Posicion (3,4),new Posicion (4,1),mapaDeJuego);
			 
			 jugador = new Jugador ("Jugador 1");
			 juego = new Juego (jugador, niveles);
			
		}

		@Test
		void huboColision () {
			
			nivelUno.colocarAvionEnAire(aSimple);
			nivelUno.colocarAvionEnAire(aPesado);
			nivelUno.colocarAvionEnAire(helicoptero);
			
			List <Avion> listaAviones = new ArrayList <Avion>();
			
			listaAviones.add(aPesado);
			listaAviones.add(aSimple);
			
			nivelUno.huboChoque();
			
			assertEquals(nivelUno.huboChoque(), true, "Error - Debería indicar que no hubo colision.");	
			
		}
		
		@Test
		
		void hayAvionesVolando() {
			
			nivelUno.colocarAvionEnAire(aSimple);
			nivelUno.colocarAvionEnAire(aPesado);
			nivelUno.colocarAvionEnAire(helicoptero);
			
			nivelUno.tieneAvionesVolando();
			
			assertEquals(nivelUno.tieneAvionesVolando(), true, "Error - Actualmente hay aviones en el aire.");
			
			
		}
		
		@Test
		
		void verificarAterrizajeDeAvion () {
			
			mapaDeJuego.setPistas(pistas);
			nivelUno.colocarAvionEnAire(aSimple);
			
			nivelUno.aterrizarAviones();
			
			assertEquals(nivelUno.tieneAvionesVolando(), true, "Error - Debe haber al menos un avion que haya aterrizado.");
			
			
		}

}
