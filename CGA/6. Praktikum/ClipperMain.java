package praktikum.sechs;

import org.amcgala.Amcgala;
import org.amcgala.Scene;
import org.amcgala.framework.event.InputHandler;
import org.amcgala.framework.event.MouseClickedEvent;
import org.amcgala.framework.scenegraph.Node;

import com.google.common.eventbus.Subscribe;



public class ClipperMain extends Amcgala implements InputHandler {
	// Szene
	private Scene scene = new Scene( "ClipperScene" );

	// Knoten
	private Node node = new Node( "ClipperNode" );

	private Clipper clipper =  new Clipper();

	public ClipperMain() {
		// Szene hinzufügen
		this.framework.addScene( scene );

		// Knoten hinzufügen
		this.scene.add( clipper, node );

		// Input Handler hinzufügen
		this.framework.addInputHandler( this, "ClipperInput" );
	}

	public static void main(String[] args) {
		new ClipperMain();
	}

	@Subscribe
	public void addPoint(MouseClickedEvent e) {
		clipper.addPoint(e);
	}

}
