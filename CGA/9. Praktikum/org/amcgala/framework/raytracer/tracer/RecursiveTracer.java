package org.amcgala.framework.raytracer.tracer;

import java.util.Collection;

import org.amcgala.Scene;
import org.amcgala.framework.raytracer.RGBColor;
import org.amcgala.framework.raytracer.Ray;
import org.amcgala.framework.raytracer.ShadingInfo;
import org.amcgala.framework.shape.Shape;

/**
 * Rekursiver Raytracer, der für die Berechnung von Reflexionen verwendet werden kann.
 *
 * @author Robert Giacinto
 * @since 2.1
 */
public class RecursiveTracer implements Tracer {
	private int maxDepth;

	/**
	 * Erzeugt einen neuen rekursiv arbeitenden Tracer. Die Rekursionstiefe bestimmt, wie oft von einem Schnittpunkt ein
	 * neuer Strahl in die Szene geschickt wird.
	 *
	 * @param maxDepth die Anzahl von Bounds innerhalb der Szene
	 */
	public RecursiveTracer(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	@Override
	public RGBColor trace(Ray ray, Scene scene) {
		return trace(ray, scene, 0);
	}

	@Override
	public RGBColor trace(Ray ray, Scene scene, int depth) {

		// Ist die maximale Rekursionstiefe erreicht?
		if( depth > maxDepth ) {
			return scene.getBackground();
		}

		ShadingInfo result = null;
		Collection<Shape> shapes = scene.getShapes();
		for ( Shape shape : shapes ) {
			ShadingInfo temp = new ShadingInfo();

			temp.depth = depth; // Rekursionstiefe
			temp.scene = scene; // aktuelle Szene
			temp.tracer = this; // Referenz auf den Tracer

			if ( shape.hit( ray, temp ) ) {
				if ( result == null || temp.t < result.t) {
					result = temp;
				}
			}
		}

		return result == null ? scene.getBackground() : result.color;

	}
}