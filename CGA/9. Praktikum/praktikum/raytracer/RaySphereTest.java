package praktikum.raytracer;

import org.amcgala.Framework;
import org.amcgala.Scene;
import org.amcgala.framework.math.Vector3d;
import org.amcgala.framework.raytracer.RGBColor;
import org.amcgala.framework.raytracer.material.MirrorMaterial;
import org.amcgala.framework.scenegraph.Node;
import org.amcgala.framework.shape.shape3d.CheckerBoard;
import org.amcgala.framework.shape.shape3d.Sphere;

/**
 * Dieser Test sollte eine Kugel raytracen.
 */
public class RaySphereTest {
	private Node sphereNode = new Node("sphere node");
	private Scene scene = new Scene("raytracer");

	public RaySphereTest() {
		Framework framework = Framework.createInstance( 600, 600 );
		sphereNode = new Node("sphere node");

		scene.setBackground( new RGBColor( 0, 0, 0 ) );

		//testOne();
		//testTwo();
		//testThree();
		//testFour();
		mickeyMouse();

		CheckerBoard checkerboard = new CheckerBoard(
			new Vector3d( 0, -300, 0 ),
			new Vector3d( 10, 0, 0 ),
			new Vector3d( 0, 0, 1 )
		);
		scene.add( checkerboard, sphereNode );

		framework.addScene( scene );
	}

	public static void main(String[] args) {
		new RaySphereTest();
	}

	private void testOne() {
		Sphere sphere11 = new Sphere(new Vector3d(-120, 150, -30), 10);
		sphere11.setColor( new RGBColor( .4f, .5f, 1 ) ); // lila
		sphere11.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere11.getRGBColor() ) );
		scene.add(sphere11, sphereNode);

		Sphere sphere = new Sphere(new Vector3d(-200, 150, -310), 100);
		sphere.setColor( new RGBColor( 0, 1, 0 ) ); // Green
		sphere.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere.getRGBColor() ) );
		scene.add(sphere, sphereNode);

		Sphere sphere1 = new Sphere(new Vector3d(-90, 150, -80), 30);
		sphere1.setColor( new RGBColor( 0, 1, 1 ) ); // Türkis
		sphere1.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere1.getRGBColor() ) );
		scene.add(sphere1, sphereNode);

		Sphere sphere2 = new Sphere(new Vector3d(150, 100, -80), 50);
		sphere2.setColor( new RGBColor( 0, 1, 0 ) ); // Green
		sphere2.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere2.getRGBColor() ) );
		scene.add(sphere2, sphereNode);

		Sphere sphere3 = new Sphere(new Vector3d( 0, 0, -80), 80);
		sphere3.setColor( new RGBColor( 0, 0, 1 ) ); // Blue
		sphere3.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere3.getRGBColor() ) );
		scene.add(sphere3, sphereNode);

		for ( int i = 0; i < 7; i++) {
			Sphere sphereB = new Sphere(new Vector3d(-250 + i * 80, -100 - i * 20, -90), 30);
			if ( i % 2 == 0 ) {
				sphereB.setColor( new RGBColor( 1, 0, 0 ) );
			} else {
				sphereB.setColor( new RGBColor( 1, 1, 0 ) );
			}
			sphereB.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphereB.getRGBColor() ) );
			scene.add(sphereB, sphereNode);
		}

		Sphere sphere4 = new Sphere(new Vector3d( 200, -500, 1000), 800);
		sphere4.setColor( new RGBColor( 1, 1, 1 ) ); // Weiß
		//sphere4.setMaterial( new MirrorMaterial( 0, sphere4.getRGBColor() ) );
		//scene.add( sphere4, sphereNode );

		Sphere sphere5 = new Sphere(new Vector3d( 0, 80, -200), 50);
		sphere5.setColor( new RGBColor( .5f, .5f, 0 ) ); // Orange
		sphere5.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere5.getRGBColor() ) );
		scene.add( sphere5, sphereNode );

		// Hinter sphere0
		Sphere sphere6 = new Sphere(new Vector3d(-300, 200, -320), 50);
		sphere6.setColor( new RGBColor( 1, 0, 0 ) ); // Rot
		sphere6.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere6.getRGBColor() ) );
		scene.add(sphere6, sphereNode);


	}

	private void testTwo() {
		Sphere sphere = new Sphere(new Vector3d(-310, -100, -80), 50);
		sphere.setColor( new RGBColor( 0, 1, 0 ) ); // Green
		sphere.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere.getRGBColor() ) );
		scene.add(sphere, sphereNode);

		Sphere sphere2 = new Sphere(new Vector3d(100, 110, -90), 40);
		sphere2.setColor( new RGBColor( 1, 0, 0 ) ); // Red
		sphere2.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere2.getRGBColor() ) );
		scene.add(sphere2, sphereNode);

		Sphere sphere3 = new Sphere(new Vector3d(-110, -40, -100), 100);
		sphere3.setColor( new RGBColor( 0, 0, 1 ) ); // Blue
		sphere3.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere3.getRGBColor() ) );
		scene.add(sphere3, sphereNode);

		Sphere sphere4 = new Sphere(new Vector3d(220, 70, -80), 40);
		sphere4.setColor( new RGBColor( 1, 1, 0 ) ); // Yellow small
		sphere4.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere4.getRGBColor() ) );
		scene.add(sphere4, sphereNode);

		Sphere sphere5 = new Sphere(new Vector3d(-110, 190, -90), 70);
		sphere5.setColor( new RGBColor( 1, 0, 1 ) ); // Pink
		sphere5.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere5.getRGBColor() ) );
		scene.add(sphere5, sphereNode);

		Sphere sphere6 = new Sphere(new Vector3d(110, -100, -90), 80);
		sphere6.setColor( new RGBColor( .5f, .5f, .5f ) ); // Grey
		sphere6.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere6.getRGBColor() ) );
		scene.add(sphere6, sphereNode);

		for ( int i = 0; i < 8; i++) {
			Sphere sphereB = new Sphere(new Vector3d(-260 + i * 80, -240, -90), 30);
			sphereB.setColor( new RGBColor( .8f, .6f, .4f ) ); // Brown
			sphereB.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphereB.getRGBColor() ) );
			scene.add(sphereB, sphereNode);
		}
	}

	private void testThree() {
		Sphere sphere = new Sphere(new Vector3d(-240, -250, -300), 100);
		sphere.setColor( new RGBColor( 0, 1, 0 ) ); // Green
		sphere.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere.getRGBColor() ) );
		scene.add(sphere, sphereNode);

		Sphere sphere1 = new Sphere(new Vector3d(-220, 100, -70), 50);
		sphere1.setColor( new RGBColor( 1, 0, 1 ) ); // Lila
		sphere1.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere1.getRGBColor() ) );
		scene.add(sphere1, sphereNode);

		Sphere sphere2 = new Sphere(new Vector3d(0, 150, -100), 50);
		sphere2.setColor( new RGBColor( 0, 0, 1 ) ); // Blue
		sphere2.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere2.getRGBColor() ) );
		scene.add(sphere2, sphereNode);

		Sphere sphere3 = new Sphere(new Vector3d(0, 0, -300), 40);
		sphere3.setColor( new RGBColor( 1, 0, 0 ) ); // Red
		sphere3.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere3.getRGBColor()  ) );
		scene.add(sphere3, sphereNode);

		Sphere sphere31 = new Sphere(new Vector3d(0, 0, -100), 40);
		sphere31.setColor( new RGBColor( 1, 1, 1  ) ); // White
		sphere31.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere31.getRGBColor()  ) );
		scene.add(sphere31, sphereNode);

		Sphere sphere4 = new Sphere(new Vector3d(240, 150, -50), 50);
		sphere4.setColor( new RGBColor( 1, 1, 0 ) ); // Yellow
		sphere4.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere4.getRGBColor() ) );
		scene.add(sphere4, sphereNode);

		Sphere sphere5 = new Sphere(new Vector3d( -50, 0, -200), 40);
		sphere5.setColor( new RGBColor( 0, 1, 1 ) ); // cyan
		sphere5.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere5.getRGBColor() ) );
		scene.add(sphere5, sphereNode);

		Sphere sphere6 = new Sphere(new Vector3d(200, -100, -100), 70);
		sphere6.setColor( new RGBColor( 1, 1, 1 ) ); // White
		sphere6.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere6.getRGBColor() ) );
		scene.add(sphere6, sphereNode);

		Sphere sphere7 = new Sphere(new Vector3d(250, 100, -800), 100);
		sphere7.setColor( new RGBColor( 1, 0, 0 ) ); // Red
		sphere7.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere7.getRGBColor() ) );
		scene.add(sphere7, sphereNode);
	}

	private void testFour() {
		Sphere sphere = new Sphere( new Vector3d( -300, 300, -400 ), 80 );
		sphere.setColor( new RGBColor( b2f(119), b2f(170), b2f(187) ) ); // MI Blau
		sphere.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere.getRGBColor() ) );
		scene.add( sphere, sphereNode );

		Sphere sphere1 = new Sphere( new Vector3d( -200, 100, -300 ), 80 );
		sphere1.setColor( new RGBColor( b2f(68), b2f(34), b2f(17) ) ); // MI Braun
		sphere1.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere1.getRGBColor() ) );
		scene.add( sphere1, sphereNode );

		Sphere sphere2 = new Sphere( new Vector3d( 100, 200, -200 ), 80 );
		sphere2.setColor( new RGBColor( b2f(119), b2f(204), b2f(0) ) ); // MI Grün
		sphere2.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere2.getRGBColor() ) );
		scene.add( sphere2, sphereNode );

		Sphere sphere3 = new Sphere( new Vector3d( 200, 0, -100 ), 80 );
		sphere3.setColor( new RGBColor( b2f(221), b2f(17), b2f(102) ) ); // MI Magenta
		sphere3.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere3.getRGBColor() ) );
		scene.add( sphere3, sphereNode );

		Sphere sphere4 = new Sphere( new Vector3d( 0, 0, -1700 ), 800 );
		sphere4.setColor( new RGBColor( 1, 1, 1 ) ); // Weiß
		sphere4.setMaterial( new MirrorMaterial( 0.25f, 0.4f, sphere4.getRGBColor() ) );
		scene.add( sphere4, sphereNode );
	}

	/**
	 * Byte to float
	 * @return
	 */
	private float b2f( int bytes ) {
		return ( 1.0f / 255 ) * bytes;
	}

	private void mickeyMouse() {
		scene.setBackground( new RGBColor( .5f, .5f, .5f ) );

		Sphere head = new Sphere( new Vector3d( 0, 0, -400 ), 150 );
		head.setColor( new RGBColor( 0, 0, 0 ) ); // Black
		head.setMaterial( new MirrorMaterial( 0.25f, 0.4f, head.getRGBColor() ) );
		scene.add( head, sphereNode );

		Sphere earl = new Sphere( new Vector3d( -150, 100, -400 ), 80 );
		earl.setColor( new RGBColor( .5f, .5f, 0 ) ); // Black
		earl.setMaterial( new MirrorMaterial( 0.25f, 0.4f, earl.getRGBColor() ) );
		scene.add( earl, sphereNode );

		Sphere earr = new Sphere( new Vector3d( 150, 100, -400 ), 80 );
		earr.setColor( new RGBColor( .5f, .5f, 0 ) ); // Black
		earr.setMaterial( new MirrorMaterial( 0.25f, 0.4f, earr.getRGBColor() ) );
		scene.add( earr, sphereNode );

		Sphere nose = new Sphere( new Vector3d( 0, -10, -250 ), 40 );
		nose.setColor( new RGBColor( 1, 1, 1 ) ); // White
		nose.setMaterial( new MirrorMaterial( 0.25f, 0.4f, nose.getRGBColor() ) );
		scene.add( nose, sphereNode );

		Sphere eyel = new Sphere( new Vector3d( -70, 50, -300 ), 30 );
		eyel.setColor( new RGBColor( 0, 0, 1 ) ); // Blue
		eyel.setMaterial( new MirrorMaterial( 0.25f, 0.4f, eyel.getRGBColor() ) );
		scene.add( eyel, sphereNode );

		Sphere eyer = new Sphere( new Vector3d( 70, 50, -300 ), 30 );
		eyer.setColor( new RGBColor( 0, 0, 1 ) ); // Blue
		eyer.setMaterial( new MirrorMaterial( 0.25f, 0.4f, eyer.getRGBColor() ) );
		scene.add( eyer, sphereNode );

		Sphere body = new Sphere( new Vector3d( 0, -250, -450 ), 200 );
		body.setColor( new RGBColor( .3f, 0, 0 ) ); // Red
		body.setMaterial( new MirrorMaterial( 0.25f, 0.4f, body.getRGBColor() ) );
		scene.add( body, sphereNode );
	}
}
