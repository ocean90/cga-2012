package praktikum.drei;

import java.awt.Color;
import java.math.RoundingMode;

import org.amcgala.Framework;
import org.amcgala.framework.renderer.Renderer;
import org.amcgala.framework.shape.AbstractShape;
import org.amcgala.framework.shape.Line;
import org.amcgala.framework.shape.shape2d.Point2d;
import org.amcgala.framework.shape.shape2d.Text;

import com.google.common.math.DoubleMath;

/**
 * Abstract Graph mit einer Abstrakt definierten Funktion.
 *
 * @author  Dennis Meyer
 *          Dominik Schilling
 */
public abstract class Graph extends AbstractShape {

	/**
	 * Anfangswert für X
	 */
	public double xMin = -10;

	/**
	 * Endwert für X
	 */
	public double xMax = 10;

	/**
	 * Anfangswert für Y
	 */
	public double yMin = -8;

	/**
	 * Endwert für Y
	 */
	public double yMax = 8;

	/**
	 * X Intervall
	 */
	public double xSteps = 0.01;

	/**
	 * Viewport-Ausdehnung: X-Minimum
	 */
	public int viewXMin = - ( Framework.getInstance().getWidth() / 2 ) + 30;

	/**
	 * Viewport-Ausdehnung: X-Maximum
	 */
	public int viewXMax = ( Framework.getInstance().getWidth() / 2 ) - 30;

	/**
	 * Viewport-Ausdehnung: Y-Minimum
	 */
	public int viewYMin = - ( Framework.getInstance().getHeight() / 2 ) + 30;

	/**
	 * Viewport-Ausdehnung: Y-Maximum
	 */
	public int viewYMax = ( Framework.getInstance().getHeight() / 2 ) - 50; // Höhe "Title Bar" abziehen

	/**
	 * Speichert den X-Wert des vorherigen Punktes
	 * Wird für drawFunctionWithLines benötigt.
	 */
	private double prevPointX;

	/**
	 * Speichert den Y-Wert des vorherigen Punkte.
	 * Wird für drawFunctionWithLines benötigt.
	 */
	private double prevPointY;

	/**
	 * Overflow für das Koordinantensystem.
	 */
	private double lineOverflow = 10;

	/**
	 * Wenn true wird Funktion mit Linien gezeichnet, ansonsten
	 * mit Punkten.
	 */
	public boolean useLines = true;

	/**
	 * Wenn true wird ein Grid im 0.5er Intervall angelegt.
	 */
	public boolean showGrid = true;

	/**
	 * BETA: Deaktiviert das "Clipping".
	 */
	private boolean noClipping = false;

	/**
	 * Konstruktor: Standardwerte
	 */
	public Graph() {}

	/**
	 * Konstruktor: Mit xMin und xMax.
	 */
	public Graph( double xMin, double xMax ) {
		this.xMin = xMin;
		this.xMax = xMax;
	}

	/**
	 * Konstruktor: Mit xMin, xMax, yMin und yMax.
	 */
	public Graph( double xMin, double xMax, double yMin, double yMax ) {
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
	}

	/**
	 * Rendern.
	 */
	@Override
	public void render( Renderer renderer ) {
		// Koordinatensystem zeichnen
		this.drawCoordinateSystem( renderer );

		if ( this.useLines() ) {
			// Graph mit Linien zeichnen
			this.drawFunctionWithLines( renderer );
		} else {
			// Graph mit Punkten zeichnen
			this.drawFunctionWithPoints( renderer );
		}

		// Beschriftung
		this.drawTexts( renderer );
	}

	/**
	 * Zeichnet das Koordinatensystem.
	 *
	 * @param renderer
	 */
	private void drawCoordinateSystem( Renderer renderer ) {
		// Double zu Int abrunden
		int xMinInt = DoubleMath.roundToInt( xMin, RoundingMode.HALF_DOWN );
		int xMaxInt = DoubleMath.roundToInt( xMax, RoundingMode.HALF_DOWN );
		int yMinInt = DoubleMath.roundToInt( yMin, RoundingMode.HALF_DOWN );
		int yMaxInt = DoubleMath.roundToInt( yMax, RoundingMode.HALF_DOWN );

		// Grid zeichnen
		if ( showGrid ) {

			// 0.5er Intervall X-Achse
			for ( double j = xMinInt; j <= xMaxInt; j += 0.5 ) {
				Line half = new Line(
					transformX( j ),
					transformY( yMin ) - lineOverflow,
					transformX( j ),
					transformY( yMax ) + lineOverflow
				);
				half.setColor( Color.lightGray );
				half.render( renderer );
			}

			// 0.5er Intervall Y-Achse
			for ( double j = yMinInt; j <= yMaxInt; j += 0.5 ) {
				Line half = new Line(
					transformX( xMin ) - lineOverflow,
					transformY( j ),
					transformX( xMax ) + lineOverflow,
					transformY( j )
				);
				half.setColor( Color.lightGray );
				half.render( renderer );
			}
		}

		// 1er Intervall X-Achse
		for ( int i = xMinInt; i <= xMaxInt; i++ ) {
			Line one = new Line(
				transformX( i ),
				transformY( 0 ) - 5,
				transformX( i ),
				transformY( 0 ) + 5
			);
			one.render( renderer );
		}

		// 1er Intervall Y-Achse
		for ( int i = yMinInt; i <= yMaxInt; i++ ) {
			Line one = new Line(
				transformX( 0 ) - 5,
				transformY( i ),
				transformX( 0 ) + 5,
				transformY( i )
			);
			one.render( renderer );
		}

		// X-Achse zeichnen
		Line xAchse = new Line(
			transformX( xMin ) - lineOverflow,
			transformY( 0 ),
			transformX( xMax ) + lineOverflow,
			transformY( 0 )
		);
		xAchse.render( renderer );

		// Y-Achse zeichnen
		Line yAchse = new Line(
			transformX( 0 ),
			transformY( yMin ) - lineOverflow,
			transformX( 0 ),
			transformY( yMax ) + lineOverflow
		);
		yAchse.render( renderer );
	}

	/**
	 * Beschriftet das Koordinatensystem.
	 *
	 * @param renderer
	 */
	private void drawTexts( Renderer renderer ) {
		// X
		Text xAchseText = new Text(
			"X",
			transformX( xMax ),
			transformY( 0 )
		);
		xAchseText.render( renderer );

		// Y
		Text yAchseText = new Text(
			"Y",
			transformX( 0 ) + 5,
			transformY( yMax )
		);
		yAchseText.render( renderer );

		// Nullpunkt
		Text nullPunktText = new Text( "0", transformX( 0 ) + 5, transformY( 0 ) - 25 );
		nullPunktText.render( renderer );

		// X-Achse
		for ( int i = DoubleMath.roundToInt( xMin, RoundingMode.DOWN ); i <= DoubleMath.roundToInt( xMax, RoundingMode.HALF_DOWN ); i++ ) {
			if ( 0 == i )
				continue;

			Text xOne = new Text(
				Integer.toString( i ) ,
				transformX( i ) - 8, // 8 = Hälfte von 16, Letter = 16x16px
				transformY( 0 ) - 25
			);
			xOne.render( renderer );
		}

		// Y-Achse
		for ( int i = DoubleMath.roundToInt( yMin, RoundingMode.HALF_DOWN ); i <= DoubleMath.roundToInt( yMax, RoundingMode.HALF_DOWN ); i++ ) {
			if ( 0 == i )
				continue;

			Text yOne = new Text(
				Integer.toString( i ) ,
				transformX( 0 ) - 30,
				transformY( i ) - 8 // 8 = Hälfte von 16, Letter = 16x16px
			);
			yOne.render( renderer );
		}

		// Funktion
		Text functionText = new Text(
			this.toString(),
			Framework.getInstance().getWidth()/2 - 16 * this.toString().length(),
			Framework.getInstance().getHeight()/2 - 60
		);
		functionText.render( renderer );
	}

	/**
	 * Zeichnet die Funktion mit Hilfe von Linien.
	 * Letzter Punkt wird immer in prevPointX und prevPointY gespeichert
	 * und dann mit dem neuen Punkt verbunden.
	 *
	 * @param renderer
	 */
	private void drawFunctionWithLines( Renderer renderer ) {
		for ( double x = xMin; x < xMax; x += getXSteps() ) {
			if ( x == xMin ) { // Beim ersten Durchgang den Wert nur speichern
				prevPointX = x;
				prevPointY = function( x );
			} else {
				if ( // "Clipping"
						noClipping ||
						( prevPointY < yMax && prevPointY > yMin &&
						function( x ) < yMax && function( x ) > yMin )
				) {
					// Vorherigen Punkt mit neuem Punkt mit einer Linie verbinden
					Line line = new Line(
						transformX( prevPointX ),
						transformY( prevPointY ),
						transformX( x ),
						transformY( function( x ) )
					);

					// Mit Farbe Blau rendern
					line.setColor( Color.blue );
					line.render( renderer );
				}

				// Punkt speichern
				prevPointX = x;
				prevPointY = function( x );
			}
		}
	}

	/**
	 * Zeichnet die Funktion mit Hilfe von Punkten.
	 *
	 * @param renderer
	 */
	private void drawFunctionWithPoints( Renderer renderer ) {
		for ( double x = xMin; x < xMax; x += getXSteps() ) {
			Point2d point = new Point2d(
				transformX( x ),
				transformY( function( x ) )
			);

			// Mit Farbe Blau rendern
			point.setColor( Color.blue );
			point.render( renderer );
		}
	}

	/**
	 * Window-Viewport-Transformation für X
	 *
	 * @param  double x  X-Wert, der transformiert werden soll
	 * @return double    Transformierter X-Wert
	 */
	private double transformX( double x ) {
		double _x = x - xMin;
		double _u = _x * ( ( viewXMax - viewXMin ) / ( xMax - xMin ) );
		double u = _u + viewXMin;
		return u;
	}

	/**
	 * Window-Viewport-Transformation für Y
	 *
	 * @param  double y  Y-Wert, der transformiert werden soll
	 * @return double    Transformierter Y-Wert
	 */
	private double transformY( double y ) {
		double _y = y - yMin;
		double _v = _y * ( ( viewYMax - viewYMin ) / ( yMax - yMin ) );
		double v = _v + viewYMin;
		return v;
	}

	/**
	 * Getter für xSteps.
	 *
	 * @return X Intervall
	 */
	public double getXSteps() {
		return xSteps;
	}

	/**
	 * Getter für useLines.
	 *
	 * @return true, wenn Linien, false, wenn Punkte
	 */
	public boolean useLines() {
		return useLines;
	}

	/**
	 * Abstrakte mathematische Funktion.
	 *
	 * @param  x Berechnungswert
	 * @return   Ergebnis
	 */
	public abstract double function( double x );

	/**
	 * Mathematische Funktion als String ausgeben.
	 *
	 * @return Funktion als String
	 */
	public abstract String toString();
}
