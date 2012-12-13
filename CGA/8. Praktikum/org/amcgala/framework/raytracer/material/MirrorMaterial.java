package org.amcgala.framework.raytracer.material;

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
	private RGBColor baseColor;

	/**
	 * Ein reflektives Material, das die Umgebung spiegelt.
	 *
	 * @param reflectionCoefficient der Reflektionskoeffizient
	 * @param baseColor             die Grundfarbe des Materials
	 */
	public MirrorMaterial(float reflectionCoefficient, RGBColor baseColor) {
		this.reflectionCoefficient = reflectionCoefficient;
		this.baseColor = baseColor;
	}

	@Override
	public RGBColor getColor(ShadingInfo hit) {

		// http://www.cs.unc.edu/~rademach/xroads-RT/RTarticle.html
		// c1 = -dot_product( N, V )
		// Rl = V + (2 * N * c1 )
		Vector3d reflectedDirection = hit.ray.direction.add(
			( hit.normal.times( 2 ) ).times(
					hit.ray.direction.dot( hit.normal ) * -1
			)
		);

		// vom reflektierten Strahl "aufgesammelten" Farbe
		RGBColor hitColor = hit.tracer.trace(
				new Ray(
					hit.hitPoint,        // Neuer Schnittpunkt
					reflectedDirection   // Neue Richtung
				),
				hit.scene,               // Selbe Szene
				++hit.depth              // Tiefe erhöhen
			);

		/*
		 * Die von der Kugel zum Betrachter geschickte Intensität und Farbe bestimmt sich aus den Materialeigenschaften
		 * der Kugel und setzt sich aus der Eigenfarbe (= a) der getroffenen Kugel sowie der vom reflektierten Strahl
		 * "aufgesammelten" Farbe und Intensität (= b) zusammen. Der Reflektionskoeffizient (= r) der getroffenen Kugel
		 * bestimmt, wie diese Intensitäten gemischt werden, nämlich (1-r) * a + r * b.
		 */
		return baseColor.times( 1 - reflectionCoefficient ).add(
			hitColor.times( reflectionCoefficient )
		);




	}
}
