package praktikum.sechs;

import org.amcgala.framework.math.Vector3d;

/**
 * Helfer Klasse zur Speicherung von zwei Punkten.
 * Sind beide Punkte gespeichert, wird der nächste Punkt
 * im ersten Punkt gespeichert und der alte zweite Punkt
 * gelöscht.
 *
 * @author Dominik Schilling
 *
 */
final class Points {
	private Vector3d point1 = null;
	private Vector3d point2 = null ;

	public void add( Vector3d point ) {
		if ( null == point1 ) {
			point1 = point;
		} else if ( null == point2 ) {
			point2 = point;
		} else {
			point1 = point;
			point2 = null;
		}
	}

	public boolean available() {
		return null != point1 && null != point2;
	}

	public Vector3d getPoint1() {
		return point1;
	}

	public Vector3d getPoint2() {
		return point2;
	}

	public void destroy() {
		point1 = null;
		point2 = null;
	}
}
