package org.amcgala.framework.shape.shape3d;

import org.amcgala.framework.math.Vector3d;
import org.amcgala.framework.raytracer.RGBColor;
import org.amcgala.framework.raytracer.Ray;
import org.amcgala.framework.raytracer.ShadingInfo;
import org.amcgala.framework.shape.AbstractShape;

/**
 * Ein Schachbrett
 *
 * @author Dominik Schilling
 */
public class CheckerBoard extends AbstractShape {
	private Vector3d origin;
	private Vector3d direct1;
	private Vector3d direct2;

	/**
	 * Drei Vektoren, origin, 2 richtung
	 * @param center
	 * @param radius
	 */
	public CheckerBoard( Vector3d origin, Vector3d direct1, Vector3d direct2) {
		this.origin = origin;
		this.direct1 = direct1;
		this.direct2 = direct2;
	}

	@Override
	public boolean hit( Ray ray, ShadingInfo shadingInfo ) {
		shadingInfo.ray = ray;
		shadingInfo.label = getLabel();

		Vector3d normal = direct1.cross( direct2 );
		double abstand = - normal.dot( origin );
		double nenner = normal.dot( ray.direction );

		if ( nenner != 0 ) {
			double t = (- normal.dot( ray.origin ) - abstand ) / nenner;

			if ( t >= 0 ) {
				shadingInfo.t = t;
				shadingInfo.hitPoint = ray.origin.travel( ray.direction, shadingInfo.t );
				shadingInfo.normal = normal;

				int size = 600;
				int maximum = size/2;

				if ( shadingInfo.hitPoint.x > 0 ) { // Rechte Seite
					if ( shadingInfo.hitPoint.x % size < maximum ) {
						if ( Math.abs( shadingInfo.hitPoint.z ) % size < maximum )
							shadingInfo.color = new RGBColor( 1, 1, 1 ); // Weiß
						else
							shadingInfo.color = new RGBColor( 0, 0, 0 ); // Black
					} else {
						if ( Math.abs( shadingInfo.hitPoint.z ) % size < maximum )
							shadingInfo.color = new RGBColor( 0, 0, 0 ); // Black
						else
							shadingInfo.color = new RGBColor( 1, 1, 1 ); // Weiß
					}
				} else { // Linke Seite
					if ( Math.abs( shadingInfo.hitPoint.x ) % size < maximum ) {
						if ( Math.abs( shadingInfo.hitPoint.z ) % size < maximum )
							shadingInfo.color = new RGBColor( 0, 0, 0 ); // Black
						else
							shadingInfo.color = new RGBColor( 1, 1, 1 ); // Weiß
					} else {
						if ( Math.abs( shadingInfo.hitPoint.z ) % size < maximum )
							shadingInfo.color = new RGBColor( 1, 1, 1 ); // Weiß
						else
							shadingInfo.color = new RGBColor( 0, 0, 0 ); // Black
					}
				}

				return true;
			}
		}

		return false;
	}

}
