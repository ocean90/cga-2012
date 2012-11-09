package praktikum.sechs;

import org.amcgala.framework.math.Vector3d;
import org.amcgala.framework.shape.Line;

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

	private boolean aussen;

	final class LineOutside extends Exception {
		public LineOutside() {}
		public LineOutside( String s ) {
			super( s );
		}
	}


	public ClippingAlgorithm( Line oldLine, Vector3d ul, Vector3d ol, Vector3d or, Vector3d ur ) {
		this.p0 = oldLine.getStart();
		this.p1 = oldLine.getEnd();
		this.ul = ul;
		this.ol = ol;
		this.or = or;
		this.ur = ur;

		this.calculate();
	}

	/**
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

		// K0
		Vector3d E0 = this.ul;
		Vector3d n0 = new Vector3d( 0, 1, 0 );
		double t0 = p0.sub( E0 ).dot( n0 ) / p0.sub( p1 ).dot( n0 );

		if ( p1.sub( p0 ).dot( n0 ) > 0 ) { // Betreten
			if( t0 <= 1 && t0 >= 0)
				p0 = p0.add( p1.sub( p0 ).times( t0 ) );
			else if ( t0 > 1 )
				aussen = true;
		} else if( p1.sub( p0 ).dot( n0 ) < 0  ) { // Verlassen
			if( t0 <= 1 && t0 >= 0 ) {
				p1 = p0.add( p1.sub( p0 ).times( t0 ) );
				aussen = false;
			} else if ( t0 < 0 )
				aussen = true;
		}

		// K1
		Vector3d E1 = this.ol;
		Vector3d n1 = new Vector3d( 1, 0, 0 );
		double t1 = p0.sub( E1 ).dot( n1 ) / p0.sub( p1 ).dot( n1 );

		if ( p1.sub( p0 ).dot( n1 ) > 0 ) {
			if( t1 <= 1 && t1 >= 0) {
				p0 = p0.add( p1.sub( p0 ).times( t1 ) );
				aussen = false;
			} else if ( t1 > 1 )
				aussen = true;
		} else if( p1.sub( p0 ).dot( n1 ) < 0  ) {
			if( t1 <= 1 && t1 >= 0 ) {
				p1 = p0.add( p1.sub( p0 ).times( t1 ) );
				aussen = false;
			} else if ( t1 < 0 )
				aussen = true;
		}

		// K2
		Vector3d E2 = this.or;
		Vector3d n2 = new Vector3d( 0, -1, 0 );

		double t2 = p0.sub( E2 ).dot( n2 ) / p0.sub( p1 ).dot( n2 );

		if ( p1.sub( p0 ).dot( n2 ) > 0 ) {
			if( t2 <= 1 && t2 >= 0) {
				p0 = p0.add( p1.sub( p0 ).times( t2 ) );
				aussen = false;
			} else if ( t2 > 1 )
				aussen = true;
		} else if( p1.sub( p0 ).dot( n2 ) < 0  ) {
			if( t2 <= 1 && t2 >= 0 ) {
				p1 = p0.add( p1.sub( p0 ).times( t2 ) );
				aussen = false;
			} else if ( t2 < 0 )
				aussen = true;
		}

		// K3
		Vector3d E3 = this.ur;
		Vector3d n3 = new Vector3d( -1, 0, 0 );
		double t3 = p0.sub( E3 ).dot( n3 ) / p0.sub( p1 ).dot( n3 );

		if ( p1.sub( p0 ).dot( n3 ) > 0 ) {
			if( t3 <= 1 && t3 >= 0) {
				p0 = p0.add( p1.sub( p0 ).times( t3 ) );
				aussen = false;
			} else if ( t3 > 1 )
				aussen = true;
		} else if( p1.sub( p0 ).dot( n3 ) < 0  ) {
			if( t3 <= 1 && t3 >= 0 ) {
				p1 = p0.add( p1.sub( p0 ).times( t3 ) );
				aussen = false;
			} else if ( t3 < 0 )
				aussen = true;
		}

	}

	public Vector3d getStart() throws LineOutside {
		if ( aussen )
			throw new LineOutside();

		return this.p0;
	}

	public Vector3d getEnd() throws LineOutside {
		if ( aussen )
			throw new LineOutside();

		return this.p1;
	}

	public Line newLine() throws LineOutside {
		if ( aussen )
			throw new LineOutside();

		return new Line( this.p0, this.p1 );
	}

}
