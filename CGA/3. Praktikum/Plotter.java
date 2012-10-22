package praktikum.drei;

import java.util.Scanner;

import org.amcgala.Framework;
import org.amcgala.Scene;
import org.amcgala.framework.scenegraph.Node;

/**
 * Hauptprogramm für den Plotter.
 */
public class Plotter {

	private Scene scene = new Scene("PlotterScene");
	private Node graphNode = new Node("GraphNode");

	protected final Framework framework = Framework.createInstance(1024, 800);

	private static Scanner in = new Scanner( System.in );
	private static int eingabe;

	/**
	 * Graph 1:
	 * f(x) = sin(x), x = 0...2*pi
	 */
	private Graph graph1 = new Graph( -Math.PI, Math.PI, -1, 1 ) {
		@Override
		public double function(double x) {
			return Math.sin( x );
		}
		@Override
		public String toString() {
			return "f(x)=sin(x)";
		}
	};

	/**
	 * Graph 2:
	 * f(x) = tan(x), x = -3/4*pi..3/4*pi
	 */
	private Graph graph2 = new Graph( -0.75*Math.PI, 0.75*Math.PI ) {
		@Override
		public double function(double x) {
			return Math.tan( x );
		}
		@Override
		public String toString() {
			return "f(x)=tan(x)";
		}
	};

	/**
	 * Graph 3:
	 * f(x) = sin( tan(x) ), x = 0..pi
	 */
	private Graph graph3 = new Graph( 0, Math.PI, -2, 2 ) {
		@Override
		public double function(double x) {
			return Math.sin( Math.tan( x ) );
		}
		@Override
		public double getXSteps() {
			return 0.0001;
		}
		@Override
		public String toString() {
			return "f(x)=sin(tan(x))";
		}
	};

	/**
	 * Graph 4:
	 * f(x) = sqrt(cos(x))*cos(200*x)+sqrt(abs(x))-0.7)*(4-x*x)^0.01, x = pi/2..pi/2
	 */
	private Graph graph4 = new Graph( -Math.PI/2, Math.PI/2, -1.5, 1.5 ) {
		@Override
		public double function(double x) {
			return ( Math.sqrt( Math.cos( x ) ) * Math.cos( 200 * x ) + Math.sqrt( Math.abs( x ) ) - 0.7 ) * Math.pow( 4- x * x, 0.01);
		}
		@Override
		public double getXSteps() {
			return 0.0001;
		}
		@Override
		public String toString() {
			return "f(x)=sqrt(cos(x))*cos(200*x)+sqrt(abs(x))-0.7)*(4-x*x)^0.01";
		}
	};

	public Plotter() {
		/**
		 * Graphenbeispiele
		 */
		switch ( eingabe ) {
			case 1:
				// fügt ein neues Element mit Knotenpunkt zum Scenengraphen hinzu
				this.scene.add(graph1, graphNode);
				break;
			case 2:
				// fügt ein neues Element mit Knotenpunkt zum Scenengraphen hinzu
				this.scene.add(graph2, graphNode);
				break;
			case 3:
				// fügt ein neues Element mit Knotenpunkt zum Scenengraphen hinzu
				this.scene.add(graph3, graphNode);
				break;
			case 4:
				// fügt ein neues Element mit Knotenpunkt zum Scenengraphen hinzu
				this.scene.add(graph4, graphNode);
				break;
		}

		// fügt eine neue Scene hinzu
		this.framework.addScene(scene);

		// startet das Framework
		this.framework.start();
	}

	public static void main(String[] args) {
		System.out.print("Graph 1, 2 oder 3: ");
		eingabe = in.nextInt();

		// Erstellt den Plotter
		new Plotter();
	}
}
