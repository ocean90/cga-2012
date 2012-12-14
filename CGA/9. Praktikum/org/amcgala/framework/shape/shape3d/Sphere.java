package org.amcgala.framework.shape.shape3d;

import org.amcgala.framework.math.MathConstants;
import org.amcgala.framework.math.Vector3d;
import org.amcgala.framework.raytracer.RGBColor;
import org.amcgala.framework.raytracer.Ray;
import org.amcgala.framework.raytracer.ShadingInfo;
import org.amcgala.framework.raytracer.tracer.RecursiveTracer;
import org.amcgala.framework.shape.AbstractShape;

/**
 * Eine Kugel, über einen Raytracer dargestellt werden kann.
 *
 * @author Robert Giacinto
 * @since 2.1
 */
public class Sphere extends AbstractShape {
	private Vector3d center;
	private double radius;


	public Sphere(Vector3d center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	@Override
	public boolean hit(Ray ray, ShadingInfo shadingInfo) {
		shadingInfo.ray = ray;
		shadingInfo.label = getLabel();

		// Siehe PDF aus ILIAS.
		Vector3d temp = ray.origin.sub( center );
		double a = ray.direction.dot( ray.direction );
		double b = temp.times( 2.0 ).dot( ray.direction );
		double c = temp.dot( temp ) - radius * radius;
		double disc = b * b - 4.0 * a * c;

		if ( disc < 0.0 ) {
			return false;
		} else {
			double e = Math.sqrt( disc );
			double denom = 2.0 * a;

			double t1 = ( -b - e ) / denom;
			double t2 = ( -b + e ) / denom;
			if ( t1 > MathConstants.EPSILON ) {
				shadingInfo.t = t1;
			} else if ( t2 > MathConstants.EPSILON ) {
				shadingInfo.t = t1;
			} else {
				return false;
			}

			shadingInfo.normal = ( temp.add( ray.direction.times( shadingInfo.t ) ) ).times( 1/radius );
			shadingInfo.hitPoint = ray.origin.add( ray.direction.times( shadingInfo.t ) );

			if ( shadingInfo.tracer != null && shadingInfo.tracer instanceof RecursiveTracer  )
				shadingInfo.color = material != null ? getMaterial().getColor( shadingInfo ) : getRGBColor();
			else
				shadingInfo.color = getRGBColor();

			return true;
		}
	}

	public RGBColor getRGBColor() {
		return color;
	}

	public Vector3d getCenter() {
		return center;
	}

	public void setCenter(Vector3d center) {
		this.center = center;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
}
