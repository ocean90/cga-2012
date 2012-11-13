package praktikum.sechs;

import org.amcgala.Amcgala;
import org.amcgala.Scene;
import org.amcgala.framework.event.InputHandler;
import org.amcgala.framework.event.MouseClickedEvent;
import org.amcgala.framework.scenegraph.Node;

import com.google.common.eventbus.Subscribe;


/**
 * Hauptprogramm für das Clippen von Linien zwischen zwei
 * Punkten.
 *
 * @author Dominik Schilling
 *
 */
public class ClipperMain extends Amcgala implements InputHandler {
	// Szene
	private Scene scene = new Scene( "ClipperScene" );

	// Knoten
	private Node node = new Node( "ClipperNode" );

	// Clipper
	private Clipper clipper =  new Clipper();

	/**
	 * Kontruktor.
	 */
	public ClipperMain() {
		// Szene hinzufügen
		this.framework.addScene( scene );

		// Knoten hinzufügen
		this.scene.add( clipper, node );

		// Input Handler hinzufügen
		this.framework.addInputHandler( this, "ClipperInput" );
	}

	/**
	 * Gogogo.
	 *
	 * @param args
	 */
	public static void main( String[] args ) {
		new ClipperMain();
	}

	/**
	 * Händelt das Hinzufügen eines Punktens bei einem
	 * Mausklick.
	 *
	 * @param e
	 */
	@Subscribe
	public void addPoint( MouseClickedEvent e ) {
		clipper.addPoint( e );
	}

}
