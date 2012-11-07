package praktikum.fuenf;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

import org.amcgala.framework.animation.Animation;
import org.amcgala.framework.math.Vector3d;
import org.amcgala.framework.renderer.Renderer;
import org.amcgala.framework.shape.AbstractShape;
import org.amcgala.framework.shape.Circle;
import org.amcgala.framework.shape.Line;
import org.amcgala.framework.shape.shape3d.Box;
import org.amcgala.framework.shape.util.CompositeShape;


/**
 * Ein Pendel.
 *
 * @author Dominik Schilling
 */
public class Pendel extends AbstractShape {
	// Startposition
	private Vector3d sphereStartPosition = new Vector3d( 150, 150, 150 );

	// Geschwindigkeit
	private Vector3d sphereSpeed = new Vector3d( 0, 0, 0 );

	// Beschleunigungskoeff.
	private Vector3d sphereAcceleration = new Vector3d( -0.1, -0.2, -0.15 );

	// Dämpfung
	private Vector3d sphereAttenuation = new Vector3d( 0.9, 0.9, 0.9 );

	// Pendel
	private Circle pendel = new Circle( 0, 0, 0, 0 );

	// Queue
	private Queue<Vector3d> positions = new ArrayDeque<Vector3d>();

	/**
	 * Pendel initialisieren.
	 *
	 * Setzt die Animation und legt den Startpunkt/Radius des Pendels fest.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Pendel() {
		// Startposition des Pendels festlegen
		pendel.setPosition(
			sphereStartPosition.x,
			sphereStartPosition.y,
			sphereStartPosition.z
		);

		// Radius des Pendels (Kreises)
		pendel.setRadius( 50 );

		pendel.setColor( Color.red );

		this.setAnimation( new Animation( this ) {
			@Override
			public void update() {
				// Aktuelle Pendel Position
				Vector3d currentPosition = pendel.getPosition();

				// Neue x-Position
				sphereSpeed.x = sphereAcceleration.x * currentPosition.x + sphereAttenuation.x * sphereSpeed.x;
				double x = currentPosition.x + sphereSpeed.x;

				// Neue y-Position
				sphereSpeed.y = sphereAcceleration.y * currentPosition.y + sphereAttenuation.y * sphereSpeed.y;
				double y = currentPosition.y + sphereSpeed.y;

				// Neue z-Position
				sphereSpeed.z = sphereAcceleration.z * currentPosition.z + sphereAttenuation.z * sphereSpeed.z;
				double z = currentPosition.z + sphereSpeed.z;

				// Neue Pendel Position setzen
				pendel.setPosition( x, y, z );

				// Pendel Position speichern
				positions.add( pendel.getPosition() );
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
		// TODO
		Box box = new Box(
			new Vector3d( 0, 0, 0 ),
			200,
			200,
			300
		);
		//box.render(renderer);

		Vector3d
			a = new Vector3d(    0, -200, -300 ),
			b = new Vector3d(    0, -200,  300 ),
			c = new Vector3d(  300, -200,    0 ),
			d = new Vector3d( -300, -200,    0 ),
			e = new Vector3d(    0,  200, -300 ),
			f = new Vector3d(    0,  200,  300 ),
			g = new Vector3d(  300,  200,    0 ),
			h = new Vector3d( -300,  200,    0 );

		CompositeShape lines = new CompositeShape();
		lines.add( new Line( a, e ) );
		lines.add( new Line( b, f ) );
		lines.add( new Line( c, g ) );
		lines.add( new Line( d, h ) );

		lines.add( new Line( a, c ) );
		lines.add( new Line( c, b ) );
		lines.add( new Line( d, b ) );
		lines.add( new Line( a, d ) );

		lines.add( new Line( e, g ) );
		lines.add( new Line( g, f ) );
		lines.add( new Line( h, f ) );
		lines.add( new Line( e, h ) );

		lines.render(renderer);
	}

	/**
	 * Rendert die Verfolgungslinie des Pendels.
	 *
	 * @param renderer
	 */
	private void drawTraceLine( Renderer renderer ) {
		// Vorheriger Vektor speichern, fängt beim
		// Startpunkt des Pegels an
		Vector3d prev = sphereStartPosition;

		for ( Iterator<Vector3d> iter = positions.iterator(); iter.hasNext(); ) {
			// Nächsten Vektor holen
			Vector3d next = iter.next();

			// Linie zwischen alten und neuen Vektor rendern
			Line line = new Line( prev, next );
			line.setColor( Color.gray );
			line.render( renderer );

			// Alten Vektor mit neuem Vektor überschreiben
			prev = next;
		}
	}

}
