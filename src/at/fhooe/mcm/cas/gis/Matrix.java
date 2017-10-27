package at.fhooe.mcm.cas.gis;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

/**
 * Handles matrix operations and calculations.
 * 
 * @author Oliver
 *
 */
public class Matrix {

	private double[][] mMatrixArr;
	
	/**
	 * Erstellt eine Einheitsmatrix.
	 */
	public Matrix() {
		this(1, 0, 0, 0, 1, 0, 0, 0, 1);
	}
	
	/**
	 * Erstellt Matrix mit gegebenen Werten.
	 * 
	 * @param _m11
	 * @param _m12
	 * @param _m13
	 * @param _m21
	 * @param _m22
	 * @param _m23
	 * @param _m31
	 * @param _m32
	 * @param _m33
	 */
	public Matrix(double _m11, double _m12, double _m13,
				double _m21, double _m22, double _m23,
				double _m31, double _m32, double _m33) {
		mMatrixArr = new double[3][3];
		
		mMatrixArr[0][0] = _m11;
		mMatrixArr[0][1] = _m12;
		mMatrixArr[0][2] = _m13;
		mMatrixArr[1][0] = _m21;
		mMatrixArr[1][1] = _m22;
		mMatrixArr[1][2] = _m23;
		mMatrixArr[2][0] = _m31;
		mMatrixArr[2][1] = _m32;
		mMatrixArr[2][2] = _m33;
	}
	
	/**
	 * Erstellt Matrix mit übergebenen Werten in Form eines Arrays.
	 * @param _matrixArr übergebene Matrix als Array
	 */
	public Matrix(double[][] _matrixArr) {
		mMatrixArr = _matrixArr; 
	}
	
	/**
	 * Gibt die den Matrix-Array zurück.
	 * 
	 * @return Matrix als Array
	 */
	public double[][] getMatrixArr() {
		return mMatrixArr;
	}
	
	/**
	 * Liefert eine String - Repräsentation der Matrix
	 * 
	 * @return Ein String mit dem Inhalt der Matrix
	 * @see java.lang.String
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (int r = 0; r < mMatrixArr.length; r++) {
			for (int c = 0; c < mMatrixArr[0].length; c++) {
				String val = String.valueOf(mMatrixArr[r][c]);
				buf.append("[" + String.valueOf(r) + 
						"][" + String.valueOf(c) + 
						"] = " + val);
				buf.append("\n");
			}
		}
		return buf.toString();
	}

	/**
	 * Liefert die Invers - Matrix der Transformationsmatrix
	 * 
	 * @return Die Invers - Matrix
	 */
	public Matrix invers() {
		double[][] invers = new double[3][3];
		
		double scalar = 1 / determinant3(mMatrixArr);
		
		double[][] tmp = new double[2][2];
		// first row
		tmp[0][0] = mMatrixArr[1][1];
		tmp[0][1] = mMatrixArr[1][2];
		tmp[1][0] = mMatrixArr[2][1];
		tmp[1][1] = mMatrixArr[2][2];
		invers[0][0] = determinant2(tmp);
		
		tmp[0][0] = mMatrixArr[0][2];
		tmp[0][1] = mMatrixArr[0][1];
		tmp[1][0] = mMatrixArr[2][2];
		tmp[1][1] = mMatrixArr[2][1];
		invers[0][1] = determinant2(tmp);
		
		tmp[0][0] = mMatrixArr[0][1];
		tmp[0][1] = mMatrixArr[0][2];
		tmp[1][0] = mMatrixArr[1][1];
		tmp[1][1] = mMatrixArr[1][2];
		invers[0][2] = determinant2(tmp);
		
		// second row
		tmp[0][0] = mMatrixArr[1][2];
		tmp[0][1] = mMatrixArr[1][0];
		tmp[1][0] = mMatrixArr[2][2];
		tmp[1][1] = mMatrixArr[2][0];
		invers[1][0] = determinant2(tmp);
		
		tmp[0][0] = mMatrixArr[0][0];
		tmp[0][1] = mMatrixArr[0][2];
		tmp[1][0] = mMatrixArr[2][0];
		tmp[1][1] = mMatrixArr[2][2];
		invers[1][1] = determinant2(tmp);
		
		tmp[0][0] = mMatrixArr[0][2];
		tmp[0][1] = mMatrixArr[0][0];
		tmp[1][0] = mMatrixArr[1][2];
		tmp[1][1] = mMatrixArr[1][0];
		invers[1][2] = determinant2(tmp);
		
		// third row
		tmp[0][0] = mMatrixArr[1][0];
		tmp[0][1] = mMatrixArr[1][1];
		tmp[1][0] = mMatrixArr[2][0];
		tmp[1][1] = mMatrixArr[2][1];
		invers[2][0] = determinant2(tmp);
		
		tmp[0][0] = mMatrixArr[0][1];
		tmp[0][1] = mMatrixArr[0][0];
		tmp[1][0] = mMatrixArr[2][1];
		tmp[1][1] = mMatrixArr[2][0];
		invers[2][1] = determinant2(tmp);
		
		tmp[0][0] = mMatrixArr[0][0];
		tmp[0][1] = mMatrixArr[0][1];
		tmp[1][0] = mMatrixArr[1][0];
		tmp[1][1] = mMatrixArr[1][1];
		invers[2][2] = determinant2(tmp);
		
		// multiply every value with scalar
		for (int r = 0; r < invers.length; r++) {
			for (int c = 0; c < invers.length; c++) {
				invers[r][c] *= scalar;
			}
		}
		
		Matrix inversM = new Matrix(invers);
		return inversM;
	}
	
	/**
	 * Liefert die Scalar - Matrix der Transformationsmatrix
	 * 
	 * @param scalar Wert zum Mulitplizerien
	 * @return die Scalar - Matrix
	 */
	public Matrix multiplyScalar(double scalar) {
		double[][] newMatrix = mMatrixArr;
		for (int r = 0; r > mMatrixArr.length; r++) {
			for (int c = 0; c > mMatrixArr.length; c++) {
				newMatrix[r][c] *= scalar;
			}
		}
		return new Matrix(newMatrix);
	}
	
	/**
	 * Hilfsmethode zum Berechnen der Determinante einer 2x2 Matrix
	 * 
	 * @param m 2x2 Matrix
	 * @return die Determinante
	 */
	private double determinant2(double[][] m) {
		double det = m[0][0] * m[1][1] - m[0][1] * m[1][0];
		return det;
	}
	
	/**
	 * Hilfsmethode zum Berechnen der Determinante einer 3x3 Matrix
	 * 
	 * @param m 3x3 Matrix
	 * @return die Determinante
	 */
	private double determinant3(double[][] m) {
		double det = m[0][0] * m[1][1] * m[2][2];
		det	+= m[0][1] * m[1][2] * m[2][0];
		det += m[0][2] * m[1][0] * m[2][1];
		det -= m[0][0] * m[1][2] * m[2][1];
		det -= m[0][1] * m[1][0] * m[2][2];
		det -= m[0][2] * m[1][1] * m[2][0];
		return det;
	}
	
	/**
	 * Liefert eine Matrix, die das Ergebnis einer Matrizen - multiplikation
	 * zwischen dieser und der übergebenen Matrix ist
	 * 
	 * @param _other Die Matrix mit der Multipliziert werden soll
	 * @return Die Ergebnismatrix der Multiplikation
	 */
	public Matrix multiply(Matrix _other) {
		
		double[][] newMatrix = new double[3][3];
		double[][] otherMatrix = _other.mMatrixArr;
		newMatrix[0][0] = mMatrixArr[0][0] * otherMatrix[0][0]
				+ mMatrixArr[0][1] * otherMatrix[1][0]
				+ mMatrixArr[0][2] * otherMatrix[2][0];
		
		newMatrix[0][1] = mMatrixArr[0][0] * otherMatrix[0][1]
				+ mMatrixArr[0][1] * otherMatrix[1][1]
				+ mMatrixArr[0][2] * otherMatrix[2][1];
		
		newMatrix[0][2] = mMatrixArr[0][0] * otherMatrix[0][2]
				+ mMatrixArr[0][1] * otherMatrix[1][2]
				+ mMatrixArr[0][2] * otherMatrix[2][2];
		
		newMatrix[1][0] = mMatrixArr[1][0] * otherMatrix[0][0]
				+ mMatrixArr[1][1] * otherMatrix[1][0]
				+ mMatrixArr[1][2] * otherMatrix[2][0];
		
		newMatrix[1][1] = mMatrixArr[1][0] * otherMatrix[0][1]
				+ mMatrixArr[1][1] * otherMatrix[1][1]
				+ mMatrixArr[1][2] * otherMatrix[2][1];
		
		newMatrix[1][2] = mMatrixArr[1][0] * otherMatrix[0][2]
				+ mMatrixArr[1][1] * otherMatrix[1][2]
				+ mMatrixArr[1][2] * otherMatrix[2][2];
		
		newMatrix[2][0] = mMatrixArr[2][0] * otherMatrix[0][0]
				+ mMatrixArr[2][1] * otherMatrix[1][0]
				+ mMatrixArr[2][2] * otherMatrix[2][0];
		
		newMatrix[2][1] = mMatrixArr[2][0] * otherMatrix[0][1]
				+ mMatrixArr[2][1] * otherMatrix[1][1]
				+ mMatrixArr[2][2] * otherMatrix[2][1];
		
		newMatrix[2][2] = mMatrixArr[2][0] * otherMatrix[0][2]
				+ mMatrixArr[2][1] * otherMatrix[1][2]
				+ mMatrixArr[2][2] * otherMatrix[2][2];
		
		return new Matrix(newMatrix);
	}

	/**
	 * Multipliziert einen Punkt mit der Matrix und liefert das Ergebnis der
	 * Multiplikation zurück
	 * 
	 * @param _pt Der Punkt, der mit der Matrix multipliziert
	 * 
	 *            werden soll
	 * @return Ein neuer Punkt, der das Ergebnis der Multiplikation
	 *         repräsentiert
	 * @see java.awt.Point
	 */
	public Point multiply(Point _pt) {
		Point res = new Point();
		res.x = (int)(mMatrixArr[0][0] * _pt.getX() + mMatrixArr[0][1] * _pt.getY() + mMatrixArr[0][2]);
		res.y = (int)(mMatrixArr[1][0] * _pt.getX() + mMatrixArr[1][1] * _pt.getY() + mMatrixArr[1][2]);
		return res;
	}
	
	/**
	 * Multipliziert ein Rechteck mit der Matrix und liefert das Ergebnis der
	 * Multiplikation zurück
	 * 
	 * @param _rect Das Rechteck, das mit der Matrix multipliziert werden soll
	 * @return Ein neues Rechteck, das das Ergebnis der
	 * 
	 *         Multiplikation repräsentiert
	 * @see java.awt.Rectangle
	 */
	public Rectangle multiply(Rectangle _rect) {
		Point p1 = new Point();
		Point p2 = new Point();
		p1.x = (int)_rect.getMinX();
		p1.y = (int)_rect.getMinY();
		p2.x = (int)_rect.getMaxX();
		p2.y = (int)_rect.getMaxY();
		
		Point p1new = multiply(p1);
		Point p2new = multiply(p2);
		
		Rectangle res = new Rectangle(p1new);
		res.add(p2new);
		return res;
	}
	
	/**
	 * Multipliziert ein Polygon mit der Matrix und liefert das Ergebnis der
	 * Multiplikation zurück
	 * 
	 * @param _poly Das Polygon, das mit der Matrix multipliziert werden soll
	 * @return Ein neues Polygon, das das Ergebnis der Multiplikation
	 *         repräsentiert
	 * @see java.awt.Poylygon
	 */
	public Polygon multiply(Polygon _poly) {
		Polygon newPol = new Polygon();
		for (int i = 0; i < _poly.npoints; i++) {
			Point p = new Point(_poly.xpoints[i], _poly.ypoints[i]);
			Point newP = multiply(p);
			newPol.addPoint(newP.x, newP.y);
		}
		return newPol;
	}
	
	/**
	 * Liefert eine Translationsmatrix
	 * 
	 * @param _x Der Translationswert der Matrix in X - Richtung
	 * @param _y Der Translationswert der Matrix in Y - Richtung
	 * @return Die Translationsmatrix
	 */
	public static Matrix translate(double _x, double _y) {
		return new Matrix(1, 0, _x, 0, 1, _y, 0, 0, 1);
	}
	

	/**
	 * Liefert eine Translationsmatrix
	 * 
	 * @param _pt Ein Punkt, der die Translationswerte enthält
	 * @return Die Translationsmatrix
	 * @see Point
	 */
	public static Matrix translate(Point _pt) {
		return new Matrix(1, 0, _pt.x, 0, 1, _pt.y, 0, 0, 1);
	}

	/**
	 * Liefert eine Skalierungsmatrix
	 * 
	 * @param _scaleVal Der Skalierungswert der Matrix
	 * @return Die Skalierungsmatrix
	 */
	public static Matrix scale(double _scaleVal) {
		return new Matrix(_scaleVal, 0, 0, 0, _scaleVal, 0, 0, 0, 1);
	}
	
	/**
	 * Liefert eine Spiegelungsmatrix (X - Achse) @ return Die Spiegelungsmatrix
	 */
	public static Matrix mirrorX() {
		return new Matrix(1, 0, 0, 0, -1, 0, 0, 0, 1);
	}
	
	/**
	 * Liefert eine Spiegelungsmatrix (Y - Achse)
	 * 
	 * @return Die Spiegelungsmatrix
	 */
	public static Matrix mirrorY() {
		return new Matrix(-1, 0, 0, 0, 1, 0, 0, 0, 1);
	}
	
	/**
	 * Liefert eine Rotationsmatrix
	 * 
	 * @param _alpha Der Winkel (in rad), um den rotiert werden soll
	 * @return Die Rotationsmatrix
	 */
	public static Matrix rotate(double _alpha) {
		return new Matrix(Math.cos(_alpha), Math.sin(_alpha) * (-1), 0, Math.sin(_alpha), Math.cos(_alpha), 0, 0, 0, 1);
	}


	/**
	 * Liefert den Faktor, der benötigt wird, um das _world - Rechteck in das
	 * _win - Rechteck zu skalieren (einzupassen) bezogen auf die X - Achse
	 * Breite
	 * 
	 * @param _world Das Rechteck in Weltkoordinaten
	 * @param _win Das Rechteck in Bildschirmkoordinaten
	 * @return Der Skalierungsfaktor
	 * @see Rectangle
	 */
	public static double getZoomFactorX(Rectangle _world, Rectangle _win) {
		double zoomFactorX = (double) _win.width / (double) _world.width;
		return zoomFactorX;
	}
	
	/**
	 * Liefert den Faktor, der benötigt wird, um das _world - Rechteck in das
	 * _win -Rechteck zu skalieren (einzupassen) bezogen auf die Y-Achse Höhe
	 * 
	 * @param _world Das Rechteck in Weltkoordinaten
	 * @param _win Das Rechteck in Bildschirmkoordinaten
	 * @return Der Skalierungsfaktor
	 * @see Rectangle
	 */
	public static double getZoomFactorY(Rectangle _world, Rectangle _win) {
		double zoomFactorY = (double) _win.height / (double) _world.height;
		return zoomFactorY;
	}
	
	/**
	 * Liefert eine Matrix, die alle notwendigen Transformationen beinhaltet
	 * (Translation, Skalierung, Spiegelung und Translation), um ein _ world -
	 * Rechteck in ein _win - Rechteck abzubilden
	 * 
	 * @param _world Das Rechteck in Weltkoordinaten
	 * @param _win Das Rechteck in Bildschirmkoordinaten
	 * @return Die Transformationsmatrix
	 * @see Rectangle
	 */
	public static Matrix zoomToFit(Rectangle _world, Rectangle _win) {
		// translate to origin, scale, mirror, translate back to middle of window

		// calculate values
		double zoomX = getZoomFactorX(_world, _win);
		double zoomY = getZoomFactorY(_world, _win);
		double scaleVal = Math.min(zoomX, zoomY);
		double t1X = _world.getCenterX();
		double t1Y = _world.getCenterY();
		double t2X = _win.getCenterX();
		double t2Y = _win.getCenterY();
		
		// calculate the steps
		Matrix t1 = translate(-t1X, -t1Y);
		Matrix s  = scale(scaleVal);
		Matrix m  = mirrorX();
		Matrix t2 = translate(t2X, t2Y);
		
		// multiply the other way around
		Matrix res = t2.multiply(m.multiply(s.multiply(t1)));
		return res;
	}
	
	/**
	 * Liefert eine Matrix, die eine vorhandene Transformations - matrix
	 * erweitert, um an einem bestimmten Punkt um einen bestimmten Faktor in die
	 * Karte hinein - bzw. heraus zu zoomen
	 * 
	 * @param _old Die zu erweiternde Transformationsmatrix
	 * @param _zoomPt Der Punkt an dem gezoomt werden soll
	 * @param _zoomScale Der Zoom - Faktor um den gezoomt werden soll
	 * @return Die neue Transformationsmatrix
	 * @see Point
	 */
	public static Matrix zoomPoint(Matrix _old, Point _zoomPt, double _zoomScale) {
		// translate to origin, scale, translate back
		
		// calculate values
		double t1X = _zoomPt.getX();
		double t1Y = _zoomPt.getY();
		
		// calculate the steps
		Matrix t1 = translate(-t1X, -t1Y);
		Matrix s  = scale(_zoomScale);
		Matrix t2 = translate(t1X, t1Y);
		
		// multiply the other way around
		Matrix res = t2.multiply(s.multiply(t1.multiply(_old)));
		return res;
	}
	
	public GeoDoublePoint multiply(GeoDoublePoint _pt) {
		double srcx = _pt.mX;
		double srcy = _pt.mY;
		double destx = (mMatrixArr[0][0] * srcx + mMatrixArr[0][1] * srcy);
		double desty = (mMatrixArr[1][0] * srcx + mMatrixArr[1][1] * srcy);
		return new GeoDoublePoint(destx, desty);
	}
}
