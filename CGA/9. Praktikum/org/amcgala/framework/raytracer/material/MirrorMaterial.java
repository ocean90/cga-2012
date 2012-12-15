package org.amcgala.framework.raytracer.material;

import org.amcgala.framework.math.MathConstants;
import org.amcgala.framework.math.Vector3d;
import org.amcgala.framework.raytracer.RGBColor;
import org.amcgala.framework.raytracer.Ray;
import org.amcgala.framework.raytracer.ShadingInfo;

/**
 * Spiegelndes Material für den Raytracer.
 *
 * @author Robert Giacinto
 * @since 2.1
 */
public class MirrorMaterial extends Material {
	private float reflectionCoefficient;
	private float refractionCoefficient;
	private RGBColor baseColor;

	/**
	 * Ein reflektives Material, das die Umgebung spiegelt.
	 *
	 * @param reflectionCoefficient der Reflektionskoeffizient
	 * @param refractionCoefficient der Transmissivitätskoeffizient
	 * @param baseColor             die Grundfarbe des Materials
	 */
	public MirrorMaterial( float reflectionCoefficient, float refractionCoefficient, RGBColor baseColor ) {
		this.reflectionCoefficient = reflectionCoefficient;
		this.refractionCoefficient = refractionCoefficient;

		if ( this.reflectionCoefficient + this.refractionCoefficient > 1 )
			throw new IllegalArgumentException( "Reflektionskoeffizient + Transmissivitätskoeffizient muss kleiner 1 sein!" );

		this.baseColor = baseColor;
	}

	@Override
	public RGBColor getColor( ShadingInfo hit ) {


		return baseColor.times(
			1 - reflectionCoefficient - refractionCoefficient
		).add(
			getReflectedRGBColor( hit ).times( reflectionCoefficient )
		).add(
			//getRefractedRGBColor( hit ).times( refractionCoefficient )
			getRefractedRGBColor2( hit ) != null ? getRefractedRGBColor2( hit ).times( refractionCoefficient ) : new RGBColor( 0, 0, 0 )
		);

	}

	/**
	 * Farbe für Reflektionen
	 *
	 * @param hit
	 * @return
	 */
	RGBColor getReflectedRGBColor( ShadingInfo hit ) {
		Vector3d reflectedDirection = hit.ray.direction.add(
			hit.normal.times( 2 ).times( hit.normal.dot( hit.ray.direction ) * -1 )
		).normalize();

		Ray reflectedRay = new Ray(
			hit.hitPoint.travel(
				reflectedDirection,
				MathConstants.EPSILON
			),
			reflectedDirection
		);

		return hit.tracer.trace(
			reflectedRay,
			hit.scene,
			hit.depth + 1
		);
	}

	/**
	 * Farbe für Spiegelung
	 *
	 * @author: Robert Giacinto
	 * @param hit
	 * @return
	 */
	RGBColor getRefractedRGBColor( ShadingInfo hit ) {
		double n1 = 1;
		double n2 = 1.5; // @TODO Parameter?
		double n = n1 / n2;
		Vector3d i = hit.ray.direction.normalize();
		double costhetai = i.dot( hit.normal.times( -1 ) );
		double sin2thetat = Math.pow( n, 2 ) * ( 1 - Math.pow( costhetai, 2 ) );

		Vector3d t0 = i.times( n );
		Vector3d t1 = hit.normal.times(  n * costhetai + Math.sqrt( 1 - sin2thetat ) );
		Vector3d refractedDirection = t0.sub( t1 );

		Ray refractedRay = new Ray(
			hit.hitPoint.travel(
				refractedDirection,
				MathConstants.EPSILON
			),
			refractedDirection
		);

		return hit.tracer.trace(
			refractedRay,
			hit.scene,
			hit.depth + 1
		);
	}

	/**
	 * PDF: http://www.flipcode.com/archives/reflection_transmission.pdf
	 * @param hit
	 * @return
	 */
	RGBColor getRefractedRGBColor2( ShadingInfo hit ) {
		double n1 = 1;
		double n2 = 1.5; // @TODO Parameter?
		double n= n1 / n2;
		Vector3d i = hit.ray.direction.normalize();
		double cos1 = hit.normal.times( -1 ).dot( i );
		double sinT2 = n * n * ( 1.0 - cos1 * cos1 );

		if ( sinT2 > 1.0 )
			return null;

		// Änderungen gegenüber PDF: Mit cos1 noch multiplizieren!
		Vector3d tmp = hit.normal.times( n * cos1 + Math.sqrt( 1.0 - sinT2 ) );
		Vector3d refractedDirection = i.times( n ).sub( tmp );

		Ray refractedRay = new Ray(
			hit.hitPoint.travel(
				refractedDirection,
				MathConstants.EPSILON
			),
			refractedDirection
		);

		return hit.tracer.trace(
			refractedRay,
			hit.scene,
			hit.depth + 1
		);
	}
}
