package praktikum.fuenf;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Iterator;

import org.amcgala.framework.animation.Animation;
import org.amcgala.framework.math.Vector3d;
import org.amcgala.framework.renderer.Renderer;
import org.amcgala.framework.shape.AbstractShape;
import org.amcgala.framework.shape.Circle;
import org.amcgala.framework.shape.Line;
import org.amcgala.framework.shape.shape3d.Box;


/**
 * Ein Pendel.
 *
 * @author Dominik Schilling
 */
public class Pendel extends AbstractShape {
	// Startposition
	private Vector3d sphereStartPosition = new Vector3d( 100, 200, 200 );

	// Geschwindigkeit
	private Vector3d sphereSpeed = new Vector3d( 0, 0, 0 );

	// Beschleunigungskoeff.
	private Vector3d sphereAcceleration = new Vector3d( -0.1, -0.2, -0.15 );

	// Dämpfung
	private Vector3d sphereAttenuation = new Vector3d( 0.9, 0.9, 0.9 );

	// Pendel
	private Circle pendel = new Circle( 0, 0, 0, 0 );

	// Queue
	private ArrayDeque<Vector3d> positions = new ArrayDeque<Vector3d>();

	/**
	 * Pendel initialisieren.
	 *
	 * Setzt die Animation und legt den Startpunkt/Radius des Pendels fest.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Pendel() {
		// Startposition des Pendels festlegen
		pendel.setPosition( sphereStartPosition );
		positions.add( pendel.getPosition() );

		// Radius des Pendels (Kreises)
		pendel.setRadius( 50 );

		// Farbe des Pendels
		pendel.setColor( Color.red );

		this.setAnimation( new Animation( this ) {
			@Override
			public void update() {
				// Aktuelle Pendel Position
				Vector3d currentPosition = pendel.getPosition();

				// Debug
				System.out.println(
					"x: " + (int) currentPosition.x +
					" y: " + (int) currentPosition.z +
					" z: " + (int) currentPosition.z
				);

				// Prüfen, ob der Pendel in der Mitte angekommen ist
				if (
					(int) currentPosition.x == 0 &&
					(int) currentPosition.y == 0 &&
					(int) currentPosition.z == 0
				) {
					// Entfernt die erste Position aus der Queue, um die
					// Verfolgungslinie wieder abzubauen
					if ( ! positions.isEmpty() )
						positions.pop();
				} else {
					// Neue Pendel Position
					Vector3d newPosition = new Vector3d( 0, 0, 0 );

					// Neue x-Position
					sphereSpeed.x = sphereAcceleration.x * currentPosition.x + sphereAttenuation.x * sphereSpeed.x;
					newPosition.x = currentPosition.x + sphereSpeed.x;

					// Neue y-Position
					sphereSpeed.y = sphereAcceleration.y * currentPosition.y + sphereAttenuation.y * sphereSpeed.y;
					newPosition.y = currentPosition.y + sphereSpeed.y;

					// Neue z-Position
					sphereSpeed.z = sphereAcceleration.z * currentPosition.z + sphereAttenuation.z * sphereSpeed.z;
					newPosition.z = currentPosition.z + sphereSpeed.z;

					// Neue Pendel Position setzen
					pendel.setPosition( newPosition );

					// Pendel Position speichern
					positions.add( pendel.getPosition() );
				}
			}
		} );
	}

	/**
	 * Ruft die einzelnen Rendermethode für
	 *  - die Box
	 *  - das Pendel
	 *  - die Traceline
	 *  auf.
	 */
	@Override
	public void render( Renderer renderer ) {
		this.drawBox( renderer );
		this.drawPendel( renderer );
		this.drawTraceLine( renderer );
	}

	/**
	 * Rendert das Pendel.
	 *
	 * @param renderer
	 */
	private void drawPendel( Renderer renderer ) {
		pendel.render( renderer );
	}

	/**
	 * Rendert die Box.
	 *
	 * @param renderer
	 */
	private void drawBox( Renderer renderer ) {
		Box box = new Box(
			new Vector3d( -200, -200, 200 ),
			400,
			400,
			400
		);
		box.render(renderer);
	}

	/**
	 * Rendert die Verfolgungslinie des Pendels.
	 *
	 * @param renderer
	 */
	private void drawTraceLine( Renderer renderer ) {
		// Vorheriger Vektor
		Vector3d prev = null;

		for ( Iterator<Vector3d> iter = positions.iterator(); iter.hasNext(); ) {
			// Nächsten Vektor holen
			Vector3d next = iter.next();

			// Linie zwischen alten und neuen Vektor rendern
			if ( prev != null ) {
				Line line = new Line( prev, next );
				line.setColor( Color.gray );
				line.render( renderer );
			}
			// Alten Vektor mit neuem Vektor überschreiben
			prev = next;
		}
	}

}
