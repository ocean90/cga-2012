package praktikum.sechs;

import org.amcgala.framework.math.Vector3d;
import org.amcgala.framework.shape.Line;

/**
 * Implementierung des Clipping Algorithmus für Linien
 * zwischen zwei Punkten in einer Box.
 *
 * @author Dominik Schilling
 *
 */
public class ClippingAlgorithm {
	// Window-Koordinate: unten links
	private Vector3d ul;

	// Window-Koordinate: oben links
	private Vector3d ol;

	// Window-Koordinate: oben rechts
	private Vector3d or;

	// Window-Koordinate: unten rechts
	private Vector3d ur;

	// Startpunkt der Linie
	private Vector3d p0;

	// Endpunkt der Linie
	private Vector3d p1;

	// Befindet sich die Linie außerhalb
	private boolean LineOutside = false;

	/**
	 * Benutzerdefinierte Exception
	 *
	 */
	final class LineOutside extends Exception {
		public LineOutside() {}
		public LineOutside( String s ) {
			super( s );
		}
	}

	/**
	 * Algorithmus initalisieren.
	 *
	 * @param oldLine Alte Linie
	 * @param ul      Window-Koordinate
	 * @param ol      Window-Koordinate
	 * @param or      Window-Koordinate
	 * @param ur      Window-Koordinate
	 */
	public ClippingAlgorithm( Line oldLine, Vector3d ul, Vector3d ol, Vector3d or, Vector3d ur ) {
		this.p0 = oldLine.getStart();
		this.p1 = oldLine.getEnd();
		this.ul = ul;
		this.ol = ol;
		this.or = or;
		this.ur = ur;

		// Algorithmus starten
		this.calculate();
	}

	/**
	 * Clipping Algorithmus auf Basis der
	 * parametrischen Geradendarstellung.
	 *
	 * Cyrus–Beck Algorithmus.
	 *
	 * p( t ) = p0 + t * ( p1 − p0 )
	 *
	 *     ( p0 - E ) * n
	 * t = ---------------
	 *     ( p0 - p1 ) * n
	 *
	 *
	 *     ( E - p0 ) * n
	 * t = ---------------
	 *     ( p1 - p0 ) * n
	 *
	 * Das Fenster wird betreten: (p1 -  p0) * n > 0
	 * Das Fenster wird verlassen: (p1  - p0) * n < 0
	 */
	private void calculate() {

		// K0 - Unten
		Vector3d E0 = ul;
		Vector3d n0 = new Vector3d( 0, 1, 0 );
		double t0 = p0.sub( E0 ).dot( n0 ) / p0.sub( p1 ).dot( n0 );

		if ( p1.sub( p0 ).dot( n0 ) > 0 ) { // Betreten
			if ( t0 <= 1 && t0 >= 0)
				p0 = p0.add( p1.sub( p0 ).times( t0 ) );
			else if ( t0 > 1 )
				LineOutside = true;
		} else if ( p1.sub( p0 ).dot( n0 ) < 0  ) { // Verlassen
			if ( t0 <= 1 && t0 >= 0 )
				p1 = p0.add( p1.sub( p0 ).times( t0 ) );
			else if ( t0 < 0 )
				LineOutside = true;
		}

		// K1 - Links
		Vector3d E1 = ol;
		Vector3d n1 = new Vector3d( 1, 0, 0 );
		double t1 = p0.sub( E1 ).dot( n1 ) / p0.sub( p1 ).dot( n1 );

		if ( p1.sub( p0 ).dot( n1 ) > 0 ) {
			if( t1 <= 1 && t1 >= 0)
				p0 = p0.add( p1.sub( p0 ).times( t1 ) );
			else if ( t1 > 1 )
				LineOutside = true;
		} else if ( p1.sub( p0 ).dot( n1 ) < 0  ) {
			if ( t1 <= 1 && t1 >= 0 )
				p1 = p0.add( p1.sub( p0 ).times( t1 ) );
			else if ( t1 < 0 )
				LineOutside = true;
		}

		// K2 - oben
		Vector3d E2 = or;
		Vector3d n2 = new Vector3d( 0, -1, 0 );

		double t2 = p0.sub( E2 ).dot( n2 ) / p0.sub( p1 ).dot( n2 );

		if ( p1.sub( p0 ).dot( n2 ) > 0 ) {
			if ( t2 <= 1 && t2 >= 0)
				p0 = p0.add( p1.sub( p0 ).times( t2 ) );
			else if ( t2 > 1 )
				LineOutside = true;
		} else if( p1.sub( p0 ).dot( n2 ) < 0  ) {
			if ( t2 <= 1 && t2 >= 0 )
				p1 = p0.add( p1.sub( p0 ).times( t2 ) );
			else if ( t2 < 0 )
				LineOutside = true;
		}

		// K3 - rechts
		Vector3d E3 = ur;
		Vector3d n3 = new Vector3d( -1, 0, 0 );
		double t3 = p0.sub( E3 ).dot( n3 ) / p0.sub( p1 ).dot( n3 );

		if ( p1.sub( p0 ).dot( n3 ) > 0 ) {
			if ( t3 <= 1 && t3 >= 0)
				p0 = p0.add( p1.sub( p0 ).times( t3 ) );
			else if ( t3 > 1 )
				LineOutside = true;
		} else if( p1.sub( p0 ).dot( n3 ) < 0  ) {
			if ( t3 <= 1 && t3 >= 0 )
				p1 = p0.add( p1.sub( p0 ).times( t3 ) );
			else if ( t3 < 0 )
				LineOutside = true;
		}

	}

	/**
	 * Gibt den neuen Startpunkt aus.
	 *
	 * @return Startpunkt
	 *
	 * @throws LineOutside Wenn Linie außerhalb des Windows.
	 */
	public Vector3d getStart() throws LineOutside {
		if ( LineOutside )
			throw new LineOutside();

		return p0;
	}

	/**
	 * Gibt den neuen Endpunkt aus.
	 *
	 * @return Endpunkt
	 *
	 * @throws LineOutside  LineOutside Wenn Linie außerhalb des Windows.
	 */
	public Vector3d getEnd() throws LineOutside {
		if ( LineOutside )
			throw new LineOutside();

		return p1;
	}

	/**
	 * Gibt die neue Linie aus.
	 *
	 * @return Neue Linie
	 *
	 * @throws LineOutside Wenn Linie außerhalb des Windows.
	 */
	public Line newLine() throws LineOutside {
		if ( LineOutside )
			throw new LineOutside();

		return new Line( getStart(), getEnd() );
	}

}
