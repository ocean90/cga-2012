package praktikum.raytracer;

import org.amcgala.Framework;
import org.amcgala.Scene;
import org.amcgala.framework.math.Vector3d;
import org.amcgala.framework.raytracer.RGBColor;
import org.amcgala.framework.raytracer.material.MirrorMaterial;
import org.amcgala.framework.scenegraph.Node;
import org.amcgala.framework.shape.shape3d.Sphere;

/**
 * Dieser Test sollte eine Kugel raytracen.
 */
public class RaySphereTest {
	private Node sphereNode = new Node("sphere node");
	private Scene scene = new Scene("raytracer");

	public RaySphereTest() {
		Framework framework = Framework.createInstance(600, 600);
		sphereNode = new Node("sphere node");

		scene.setBackground( new RGBColor( 0, 0, 0 ) );

		//testOne();
		//testTwo();
		testThree();

		framework.addScene(scene);
	}

	public static void main(String[] args) {
		new RaySphereTest();
	}

	private void testOne() {
		Sphere sphere11 = new Sphere(new Vector3d(-120, 150, -30), 10);
		sphere11.setColor( new RGBColor( .4f, .5f, 1 ) ); // lila
		sphere11.setMaterial( new MirrorMaterial( 0.25f, sphere11.getRGBColor() ) );
		scene.add(sphere11, sphereNode);

		Sphere sphere = new Sphere(new Vector3d(-200, 150, -220), 100);
		sphere.setColor( new RGBColor( 0, 1, 0 ) ); // Green
		sphere.setMaterial( new MirrorMaterial( 0.25f, sphere.getRGBColor() ) );
		scene.add(sphere, sphereNode);

		Sphere sphere1 = new Sphere(new Vector3d(-90, 150, -80), 30);
		sphere1.setColor( new RGBColor( 0, 1, 1 ) ); // Türkis
		sphere1.setMaterial( new MirrorMaterial( 0.25f, sphere1.getRGBColor() ) );
		scene.add(sphere1, sphereNode);


		Sphere sphere2 = new Sphere(new Vector3d(150, 100, -80), 50);
		sphere2.setColor( new RGBColor( 0, 1, 0 ) ); // Green
		sphere2.setMaterial( new MirrorMaterial( 0.25f, sphere2.getRGBColor() ) );
		scene.add(sphere2, sphereNode);

		Sphere sphere3 = new Sphere(new Vector3d( 0, 0, -80), 80);
		sphere3.setColor( new RGBColor( 0, 0, 1 ) ); // Blue
		sphere3.setMaterial( new MirrorMaterial( 0.25f, sphere3.getRGBColor() ) );
		scene.add(sphere3, sphereNode);

		for ( int i = 0; i < 7; i++) {
			Sphere sphereB = new Sphere(new Vector3d(-250 + i * 80, -100 - i * 20, -90), 30);
			if ( i % 2 == 0 ) {
				sphereB.setColor( new RGBColor( 1, 0, 0 ) );
			} else {
				sphereB.setColor( new RGBColor( 1, 1, 0 ) );
			}
			sphereB.setMaterial( new MirrorMaterial( 0.25f, sphereB.getRGBColor() ) );
			scene.add(sphereB, sphereNode);
		}
	}

	private void testTwo() {
		Sphere sphere = new Sphere(new Vector3d(-310, -100, -80), 50);
		sphere.setColor( new RGBColor( 0, 1, 0 ) ); // Green
		sphere.setMaterial( new MirrorMaterial( 0.25f, sphere.getRGBColor() ) );
		scene.add(sphere, sphereNode);

		Sphere sphere2 = new Sphere(new Vector3d(100, 110, -90), 40);
		sphere2.setColor( new RGBColor( 1, 0, 0 ) ); // Red
		sphere2.setMaterial( new MirrorMaterial( 0.25f, sphere2.getRGBColor() ) );
		scene.add(sphere2, sphereNode);

		Sphere sphere3 = new Sphere(new Vector3d(-110, -40, -100), 100);
		sphere3.setColor( new RGBColor( 0, 0, 1 ) ); // Blue
		sphere3.setMaterial( new MirrorMaterial( 0.25f, sphere3.getRGBColor() ) );
		scene.add(sphere3, sphereNode);

		Sphere sphere4 = new Sphere(new Vector3d(220, 70, -80), 40);
		sphere4.setColor( new RGBColor( 1, 1, 0 ) ); // Yellow small
		sphere4.setMaterial( new MirrorMaterial( 0.25f, sphere4.getRGBColor() ) );
		scene.add(sphere4, sphereNode);

		Sphere sphere5 = new Sphere(new Vector3d(-110, 190, -90), 70);
		sphere5.setColor( new RGBColor( 1, 0, 1 ) ); // Pink
		sphere5.setMaterial( new MirrorMaterial( 0.25f, sphere5.getRGBColor() ) );
		scene.add(sphere5, sphereNode);

		Sphere sphere6 = new Sphere(new Vector3d(110, -100, -90), 80);
		sphere6.setColor( new RGBColor( .5f, .5f, .5f ) ); // Grey
		sphere6.setMaterial( new MirrorMaterial( 0.25f, sphere6.getRGBColor() ) );
		scene.add(sphere6, sphereNode);

		for ( int i = 0; i < 8; i++) {
			Sphere sphereB = new Sphere(new Vector3d(-260 + i * 80, -240, -90), 30);
			sphereB.setColor( new RGBColor( .8f, .6f, .4f ) ); // Brown
			sphereB.setMaterial( new MirrorMaterial( 0.25f, sphereB.getRGBColor() ) );
			scene.add(sphereB, sphereNode);
		}
	}

	private void testThree() {
		Sphere sphere = new Sphere(new Vector3d(-240, 150, -100), 50);
		sphere.setColor( new RGBColor( 0, 1, 0 ) ); // Green
		sphere.setMaterial( new MirrorMaterial( 0.25f, sphere.getRGBColor() ) );
		scene.add(sphere, sphereNode);

		Sphere sphere1 = new Sphere(new Vector3d(-120, 100, -100), 50);
		sphere1.setColor( new RGBColor( 1, 0, 0 ) ); // red
		sphere1.setMaterial( new MirrorMaterial( 0.25f, sphere1.getRGBColor() ) );
		scene.add(sphere1, sphereNode);

		Sphere sphere2 = new Sphere(new Vector3d(0, 150, -100), 50);
		sphere2.setColor( new RGBColor( 0, 0, 1 ) ); // blue
		sphere2.setMaterial( new MirrorMaterial( 0.25f, sphere2.getRGBColor() ) );
		scene.add(sphere2, sphereNode);

		Sphere sphere3 = new Sphere(new Vector3d(120, 50, -100), 50);
		sphere3.setColor( new RGBColor( .2f, .2f, .2f ) ); // Black
		sphere3.setMaterial( new MirrorMaterial( 0.25f, sphere3.getRGBColor() ) );
		scene.add(sphere3, sphereNode);

		Sphere sphere4 = new Sphere(new Vector3d(240, 150, -100), 50);
		sphere4.setColor( new RGBColor( 1, 1, 0 ) ); // yellow
		sphere4.setMaterial( new MirrorMaterial( 0.25f, sphere4.getRGBColor() ) );
		scene.add(sphere4, sphereNode);

		Sphere sphere5 = new Sphere(new Vector3d(0, 0, -300), 100);
		sphere5.setColor( new RGBColor( .1f, .5f, .6f ) ); // grey
		sphere5.setMaterial( new MirrorMaterial( 0.25f, sphere5.getRGBColor() ) );
		scene.add(sphere5, sphereNode);
	}
}
