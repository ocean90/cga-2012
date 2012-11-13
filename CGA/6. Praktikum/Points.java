package praktikum.sechs;

import org.amcgala.framework.math.Vector3d;

/**
 * Helfer Klasse zur Speicherung von zwei Punkten.
 * Sind beide Punkte gespeichert, wird der neue Punkt
 * im ersten Punkt gespeichert und der zweite Punkt
 * gelöscht.
 *
 * @author Dominik Schilling
 *
 */
final class Points {
	// Punkt 1
	private Vector3d point1 = null;

	// Punkt 2
	private Vector3d point2 = null;

	/**
	 * Fügt einen Punkt hinzu.
	 *
	 * @param point Ein Punkt vom Typ Vector3d
	 */
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

	/**
	 * Prüft, ob beide Punkte gesetzt sind.
	 *
	 * @return true, wenn beide Punkte vorhanden sind, sonst false
	 */
	public boolean available() {
		return null != point1 && null != point2;
	}

	/**
	 * Gibt den ersten Punkt zurück.
	 *
	 * @return null oder Punkt als Vector3d
	 */
	public Vector3d getPoint1() {
		return point1;
	}

	/**
	 * Gibt den zweiten Punkt zurück.
	 *
	 * @return null oder Punkt als Vector3d
	 */
	public Vector3d getPoint2() {
		return point2;
	}

	/**
	 * Löscht beide Punkte.
	 */
	public void destroy() {
		point1 = null;
		point2 = null;
	}
}
