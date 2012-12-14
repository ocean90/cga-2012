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
		double b = ray.direction.dot( temp ) * 2;
		double c = temp.dot( temp ) - radius * radius;
		double discriminant = b * b - 4.0 * a * c;

		if ( discriminant >= 0 ) {
			double e = Math.sqrt( discriminant );
			double denominator = 2.0 * a;

			double t1 = ( -b - e ) / denominator;
			double t2 = ( -b + e ) / denominator;
			if ( t1 > MathConstants.EPSILON ) {
				shadingInfo.t = t1;
			} else if ( t2 > MathConstants.EPSILON ) {
				shadingInfo.t = t1;
			} else {
				return false;
			}

			shadingInfo.normal = temp.add( ray.direction.times( shadingInfo.t ) ).times( 1 / radius );
			shadingInfo.hitPoint = ray.origin.add( ray.direction.times( shadingInfo.t ) );

			if ( shadingInfo.tracer != null && shadingInfo.tracer instanceof RecursiveTracer  )
				shadingInfo.color = material != null ? getMaterial().getColor( shadingInfo ) : getRGBColor();
			else
				shadingInfo.color = getRGBColor();

			return true;
		}

		return false;
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
