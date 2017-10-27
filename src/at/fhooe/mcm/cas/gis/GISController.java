package at.fhooe.mcm.cas.gis;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Vector;

import at.fhooe.mcm.cas.gis.geomodel.*;

/**
 * Controller handles interactions.
 * 
 * @author Oliver
 *
 */
public class GISController extends WindowAdapter
		implements ActionListener, ComponentListener, MouseListener, MouseWheelListener, MouseMotionListener, KeyListener, ItemListener {

	/**
	 * Default zoom factor.
	 */
	private static final double ZOOM_FACTOR = 1.3;
	/**
	 * Default scroll factor.
	 */
	private static final int SCROLL = 20;

	/**
	 * Default rotate angle in radiant.
	 */
	private static final double ROTATE_ALPHA = 2 * Math.PI / 180;

	/**
	 * Minimum distance from mouse start point to mouse end point to allow drag
	 * functionality.
	 */
	private static final int MIN_DRAG_DISTANCE = 10;

	/**
	 * Model to create new objects to render.
	 */
	private GISModel mModel;

	/**
	 * View
	 */
	private GISView mView;

	private boolean mCtrlKeyPressed;

	/**
	 * Mouse position when pressed
	 */
	private Point mMousePressed;
	
	/**
	 * Last mouse position in dragging mode.
	 */
	private Point mLastMouseDraggedPosition;
	
	/**
	 * Constructor, initializes member variables.
	 * 
	 * @param _m
	 *            model which provides objects to render
	 */
	public GISController(GISModel _m) {
		mModel = _m;
		mMousePressed = new Point(-1, -1);
		mLastMouseDraggedPosition = new Point(-1, -1);
		mCtrlKeyPressed = false;
	}

	/**
	 * Adds view to controller.
	 * Needed for selection (visible rectangle) and faster dragging.
	 * @param _v
	 */
	public void addView(GISView _v) {
		mView = _v;
	}

	@Override
	public void windowClosing(WindowEvent _e) {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent _e) {
		Object src = _e.getSource();

		if (src instanceof Button) {
			Button b = (Button) _e.getSource();
			switch (b.getName()) {
			case "btnLoadData": {
				System.out.println("Load data");
				mModel.loadData();
				break;
			}
			case "btnStore": {
				System.out.println("Store");
				mModel.storePicture();
				break;
			}
			case "btnZTF": {
				System.out.println("ZTF");
				mModel.zoomToFit();
				break;
			}
			case "btnZoomIn": {
				System.out.println("+");
				mModel.zoom(ZOOM_FACTOR);
				break;
			}
			case "btnZoomOut": {
				System.out.println("-");
				mModel.zoom(1 / ZOOM_FACTOR);
				break;
			}
			case "btnScrollUp": {
				System.out.println("N");
				mModel.scrollVertical(SCROLL);
				break;
			}
			case "btnScrollDown": {
				System.out.println("S");
				mModel.scrollVertical(-SCROLL);
				break;
			}
			case "btnScrollLeft": {
				System.out.println("W");
				mModel.scrollHorizontal(SCROLL);
				break;
			}
			case "btnScrollRight": {
				System.out.println("E");
				mModel.scrollHorizontal(-SCROLL);
				break;
			}
			case "btnRotateRight": {
				System.out.println("R right");
				mModel.rotate(ROTATE_ALPHA);
				break;
			}
			case "btnRotateLeft": {
				System.out.println("R left");
				mModel.rotate(-ROTATE_ALPHA);
				break;
			}
			case "btnEnablePOI": {
				System.out.println("POI");
				mModel.switchPOI();
				break;
			}
			default: {
				System.out.println("unknown button pressed...");
			}
			} // end switch
		} else if (src instanceof TextField) {
			// enter pressed (or other action)
			TextField t = (TextField) _e.getSource();
			try {
				int scale = Integer.parseInt(t.getText());
				mModel.setMapScale(scale);
			} catch (Exception _ex) {
				t.setText("invalid");
				System.out.println("Can not parse, invalid scale");
			}
		} else {
			System.out.println("unknown src pressed...");
		}
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentResized(ComponentEvent _e) {
		// get size of drawing panel and tell model
		Panel drawingPanel = (Panel) _e.getSource();
		Dimension d = drawingPanel.getSize();
		mModel.changedSize(d);
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent _e) {
		if (_e.getClickCount() == 2) {
			System.out.println("Double click");
			// double click
			Vector<GeoObject> objects = mModel.initSelection(new Point(_e.getX(), _e.getY()));
			for (GeoObject obj : objects) {
				System.out.println(obj.toString());
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent _e) {
		// save point for other functionalities
		mMousePressed.x = _e.getX();
		mMousePressed.y = _e.getY();

		// save coordinates to clipboard
		String msg = "";
		if (mCtrlKeyPressed) {
			msg = getFromClipboard();
		}
		Point worldCoord = mModel.getMapPoint(mMousePressed);
		msg += "(" + worldCoord.x + "," + worldCoord.y + ")\n";

		saveToClipboard(msg);
	}

	private String getFromClipboard() {
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable t = cb.getContents(this);
		try {
			return (String) t.getTransferData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * Saved a string to the clipboard.
	 * @param msg String to save
	 */
	private void saveToClipboard(String msg) {
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		cb.setContents(new StringSelection(msg), new StringSelection(msg));
	}

	@Override
	public void mouseReleased(MouseEvent _e) {
		// System.out.println("Mouse Released");
	
		// get delta
		int deltaX = mMousePressed.x - _e.getX();
		int deltaY = mMousePressed.y - _e.getY();
		
		switch (_e.getModifiers()) {
		case MouseEvent.BUTTON1_MASK:
			// left mouse button released
		    
	    	// clear old selections
	  		mView.eraseOldRect();

	  		// too small zoom not allowed
			if (Math.abs(deltaX) >= MIN_DRAG_DISTANCE && Math.abs(deltaY) >= MIN_DRAG_DISTANCE) {
		        Point mouseStart = new Point(mMousePressed.x, mMousePressed.y);
		        Point mouseEnd = new Point(_e.getX(), _e.getY());
		        
		        mouseStart = mModel.getMapPoint(mouseStart);
		        mouseEnd = mModel.getMapPoint(mouseEnd);
				Rectangle mapBounds = new Rectangle(mouseStart);
				mapBounds.add(mouseEnd);
		        mModel.zoomRect(mapBounds);
			}
			break;
		case MouseEvent.BUTTON3_MASK:
			mLastMouseDraggedPosition = new Point(-1, -1);
			
			// override real time dragging (mouseDragged) when finished 
			mModel.scrollVertical((-1) * deltaY);
			mModel.scrollHorizontal((-1) * deltaX);	
	        break;
		default:
			// System.out.println("Unknown Mouse-Button in released"); 
			break;
		} // end switch
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent _e) {
		System.out.println("Mouse-Wheel");
		if (_e.getWheelRotation() < 0) {
			// up/away
			mModel.zoom(ZOOM_FACTOR);
		} else {
			// down/towards
			mModel.zoom(1 / ZOOM_FACTOR);
		}

	}

	@Override
	public void mouseDragged(MouseEvent _e) {
		Point mouseStart = new Point(mMousePressed.x, mMousePressed.y);
		Point mouseCurrent = new Point(_e.getX(), _e.getY());
		
		switch (_e.getModifiers()) {
		case MouseEvent.BUTTON1_MASK:
			// left mouse-button pressed
			Rectangle dragRect = new Rectangle(mouseStart);
			dragRect.add(mouseCurrent);
			mView.drawRect(dragRect);
			break;
		case MouseEvent.BUTTON3_MASK:
			// right mouse-button pressed
			
			// real time dragging
			int deltaX = 0;
			int deltaY = 0;
			if (mLastMouseDraggedPosition.x == -1 && mLastMouseDraggedPosition.y == -1) {
				deltaX = mouseStart.x - mouseCurrent.x;
				deltaY = mouseStart.y - mouseCurrent.y;
			} else {
				deltaX = mLastMouseDraggedPosition.x - mouseCurrent.x;
				deltaY = mLastMouseDraggedPosition.y - mouseCurrent.y;
			}
			
			mView.dragCopy((-1) * deltaX, (-1) * deltaY);
			mLastMouseDraggedPosition = mouseCurrent;
			break;
		default:
			System.out.println("Unknown Mouse-Button in dragged"); 
			break;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent _e) {
		switch (_e.getKeyCode()) {
		case KeyEvent.VK_CONTROL:
			// CTRL pressed
			mCtrlKeyPressed = true;
			break;
		default:
			System.out.println("Unknown key pressed"); 
			break;
		}
	
		
	}

	@Override
	public void keyReleased(KeyEvent _e) {
		switch (_e.getKeyCode()) {
		case KeyEvent.VK_CONTROL:
			// CTRL released
			mCtrlKeyPressed = false;
			break;
		default:
			System.out.println("Unknown key released"); 
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent _e) {
		
		Component comp = (Component) _e.getSource();
		switch (comp.getName()) {
		case "chServer": {
			Choice c = (Choice) comp;
			mModel.switchServer(c.getSelectedIndex());
			break;
		}
		case "cbSticky": {
			Checkbox c = (Checkbox) comp;
			mModel.switchSticky(c.getState());
			break;
		}
		default:
			break;
		}
		
		
		
		
	}
}
