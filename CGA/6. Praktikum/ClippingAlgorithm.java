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

	// Ursprüngliche Startpunkt der Linie
	private Vector3d oldStart;

	// Ursprüngliche Endpunkt der Linie
	private Vector3d oldEnd;

	// Ursprüngliche Startpunkt der Linie
	private Vector3d newStart;

	// Ursprüngliche Endpunkt der Linie
	private Vector3d newEnd;

	public ClippingAlgorithm( Line oldLine, Vector3d ul, Vector3d ol, Vector3d or, Vector3d ur ) {
		this.oldStart =this.newStart = oldLine.getStart();
		this.oldEnd = this.newEnd = oldLine.getEnd();
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
	 */
	private void calculate() {
		// K0
		Vector3d E0 = this.ul;
		Vector3d n0 = new Vector3d( 0, 1, 0 );
		Vector3d t = oldStart.sub( E0 );
		System.out.println(t);

		// K1
		Vector3d E1 = this.ol;
		Vector3d n1 = new Vector3d( 1, 0, 0 );

		// K2
		Vector3d E2 = this.or;
		Vector3d n2 = new Vector3d( 0, -1, 0 );

		// K3
		Vector3d E3 = this.ur;
		Vector3d n3 = new Vector3d( -1, 0, 0 );
	}

	public Vector3d getStart() {
		return this.newStart;
	}

	public Vector3d getEnd() {
		return this.newEnd;
	}

	public Line newLine() {
		return new Line( this.newStart, this.newEnd );
	}

}
