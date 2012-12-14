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

		// Reflektion

		// ToDo: Eigene Methode
		/*Vector3d reflectedDirection = reflector( hit.normal, hit.ray.direction.normalize() );
		RGBColor reflColor = hit.tracer.trace(
			new Ray(
				hit.hitPoint,        // Neuer Schnittpunkt
				reflectedDirection   // Neue Richtung
			),
			hit.scene,               // Selbe Szene
			hit.depth + 1            // Tiefe erhöhen
		);*/

		RGBColor hitColor = baseColor.times( 1 - reflectionCoefficient - refractionCoefficient ).add(
			getReflectedRGBColor( hit ).times( reflectionCoefficient )
		);

		// Brechung

		/*Vector3d refractedDirection = refractor( hit.normal, hit.ray.direction.normalize() );
		if ( refractedDirection != null ) {
			RGBColor refrColor = hit.tracer.trace(
				new Ray(
					hit.hitPoint,
					refractedDirection
				),
				hit.scene,
				hit.depth + 1
			);

			hitColor.add( refrColor.times( refractionCoefficient ) );
		}*/

		hitColor.add( getRefractedRGBColor( hit ).times( refractionCoefficient ) );

		return hitColor;
	}

	/**
	 * Farbe für Reflektionen
	 *
	 * @param hit
	 * @return
	 */
	RGBColor getReflectedRGBColor( ShadingInfo hit ) {
		double c = hit.normal.dot( hit.ray.direction.times( -1 ) );
		Vector3d reflectedDirection = hit.ray.direction.sub( hit.normal.times( -2 * c ) );

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
	 * @author: Robert
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
		Vector3d t = t0.sub( t1 );

		Ray refractedRay = new Ray(
			hit.hitPoint.travel(
				t,
				MathConstants.EPSILON
			),
			t
		);

		return hit.tracer.trace(
			refractedRay,
			hit.scene,
			hit.depth + 1
		);
	}

	/**
	 * PDF: http://www.flipcode.com/archives/reflection_transmission.pdf
	 * @param normal
	 * @param incident
	 * @return
	 * @TODO entfernen
	 */
	Vector3d reflector( Vector3d normal, Vector3d incident ) {
		double cos1 = normal.dot( incident );
		return incident.sub( normal.times( 2 * cos1 ) );
	}

	/**
	 * PDF: http://www.flipcode.com/archives/reflection_transmission.pdf
	 * @param normal
	 * @param incident
	 * @return
	 * @TODO entfernen
	 */
	Vector3d refractor( Vector3d normal, Vector3d incident ) {
		double n1 = 1;
		double n2 = 1.5;
		double n= n1/n2;
		double cos1 = normal.times(-1).dot( incident );
		double sinT2 = n * n * ( 1.0 - cos1 * cos1 );
		if ( sinT2 > 1.0 )
			return null;

		// Mit cos1
		Vector3d tmp = normal.times( n * cos1 + Math.sqrt( 1.0 - sinT2 ) );
		return incident.times( n ).sub( tmp );
	}
}
