package praktikum.fuenf;

import org.amcgala.Amcgala;
import org.amcgala.Scene;
import org.amcgala.framework.animation.interpolation.LinearInterpolation;
import org.amcgala.framework.scenegraph.Node;
import org.amcgala.framework.scenegraph.transform.RotationY;

/**
 * Hauptprogramm für das Pendel.
 *
 * @author Dominik Schilling
 */
public class PendelMain extends Amcgala {
	// Szene
	private Scene scene = new Scene( "PendelScene" );

	// Knoten
	private Node node = new Node( "PendelNode" );

	/**
	 * Initalisieren
	 */
	public PendelMain() {
		// Szene hinzufügen
		this.framework.addScene( scene );

		// Knoten hinzufügen
		this.scene.add( new Pendel(), node );

		// Rotation um y-Achse hinzufügen
		RotationY rotation = new RotationY( 0 );
		rotation.setInterpolationPhi(
			new LinearInterpolation(
				0,
				2 * Math.PI,
				600,
				true
			)
		);
		this.node.add( rotation );

	}

	/**
	 * Gogogo.
	 *
	 * @param args
	 */
	public static void main( String[] args ) {
		// Programm starten
		new PendelMain();
	}
}
