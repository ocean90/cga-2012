package praktikum.sechs;

import java.awt.Color;

import org.amcgala.framework.event.MouseClickedEvent;
import org.amcgala.framework.math.Vector3d;
import org.amcgala.framework.renderer.Renderer;
import org.amcgala.framework.shape.AbstractShape;
import org.amcgala.framework.shape.Circle;
import org.amcgala.framework.shape.Line;
import org.amcgala.framework.shape.util.CompositeShape;

import praktikum.sechs.ClippingAlgorithm.LineOutside;


public class Clipper extends AbstractShape {
	public int xmin = -300;
	public int ymin = -200;
	public int xmax =  300;
	public int ymax =  200;

	// Window-Koordinate: unten links
	private Vector3d ul;

	// Window-Koordinate: oben links
	private Vector3d ol;

	// Window-Koordinate: oben rechts
	private Vector3d or;

	// Window-Koordinate: unten rechts
	private Vector3d ur;

	// Speicherplatz für die Punkte
	private Points points = new Points();

	/**
	 * Konstruktor.
	 */
	public Clipper() {
		this.setWindow();
	}

	/**
	 * Setzt die Eckpunkte des Fensters fest.
	 */
	private void setWindow() {
		// Window-Koordinate: unten links
		this.ul = new Vector3d( xmin, ymin, 0 );

		// Window-Koordinate: oben links
		this.ol = new Vector3d( xmin,  ymax, 0 );

		// Window-Koordinate: oben rechts
		this.or = new Vector3d( xmax,  ymax, 0 );

		// Window-Koordinate: unten rechts
		this.ur = new Vector3d( xmax, ymin, 0 );
	}

	/**
	 * Ruft die einzelnen Rendermethode für
	 *  - die Box
	 *  - die Punkte
	 *  - die geclippten Linien
	 *  auf.
	 */
	@Override
	public void render( Renderer renderer ) {
		this.drawBox( renderer );
		this.drawPoints( renderer );
		this.drawLine( renderer );
	}

	/**
	 * Rendert die Box.
	 *
	 * @param renderer
	 */
	private void drawBox( Renderer renderer ) {
		CompositeShape lines = new CompositeShape();
		lines.add( new Line( ul, ol ) );
		lines.add( new Line( ol, or ) );
		lines.add( new Line( or, ur ) );
		lines.add( new Line( ur, ul ) );

		lines.render(renderer);
	}

	/**
	 * Rendert die Punkte.
	 *
	 * @param renderer
	 */
	private void drawPoints( Renderer renderer ) {
		// Erster Punkt
		if ( null != points.getPoint1() ) {
			for ( int i = 0; i < 8; i++ ) {
				Circle point1 = new Circle( points.getPoint1(), i++ );
				point1.setColor( Color.red );
				point1.render(renderer);
			}
		}

		// Zweiter Punkt
		if( null != points.getPoint2() ) {
			for ( int i = 0; i < 8; i++ ) {
				Circle point2 = new Circle( points.getPoint2(), i++ );
				point2.setColor( Color.red );
				point2.render(renderer);
			}
		}
	}

	/**
	 * Rendert die geclippte Verbindungsline.
	 *
	 * @param renderer
	 */
	private void drawLine( Renderer renderer ) {
		// Nur wenn zwei Punkte vorhanden sind
		if ( points.available() ) {
			// Algorithmus aufrufen
			ClippingAlgorithm clipped = new ClippingAlgorithm(
				new Line( points.getPoint1(), points.getPoint2() ),
				ul,
				ol,
				or,
				ur
			);

			// Versuche die Linie zu zeichnen.
			// Es wird eine Exception ausgeworfen,
			// wenn die Linie außerhalb ist.
			// Ist die Linie außerhalb, dann werden
			// die Punkte gelöscht.
			try {
				clipped.newLine().render( renderer );
			} catch ( LineOutside e ) {
				points.destroy();
			}
		}
	}

	/**
	 * Fügt einen Punkt hinzu.
	 *
	 * @param e
	 */
	public void addPoint(MouseClickedEvent e) {
		points.add(
			Vector3d.createVector3d(
				e.getX(),
				e.getY(),
				0
			)
		);
	}

}
