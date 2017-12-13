package at.fhooe.mcm.cas.gis;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.List;

import at.fhooe.mcm.cas.IUIView;
import at.fhooe.mcm.cas.warningtype.IWarningType;


/**
 * Renders application and custom objects.
 * Gets notified when something changed.
 * 
 * @author Oliver
 *
 */
public class GISViewPedastrian extends AbstractGISView{

	/**
	 * Color for drawing drag rectangle.
	 */
	Color mRectColor = Color.GREEN;
	
	/**
	 * Currently visible rectangle.
	 */
	private Rectangle mDragRect;
	
	/**
	 * Panel to draw on.
	 */
	DrawingPanel mDrawingPanel;
	
	Panel mOverallPanel;
	
	WarningPanel mPanelWarnings;
	
	/**
	 * TextField which shows map scale value.
	 */
	TextField mTextFieldMapScale;
	
	@Override
	public Panel getPanel() {
		return mOverallPanel;
	}
	
	/**
	 * Creates frame with panel and button, and sets some listeners.
	 */
	@Override
	public void createPanel() {
	
		mOverallPanel = new Panel(new BorderLayout());
		
		mDrawingPanel = new DrawingPanel();
		
		Panel panelLoad = new Panel(new FlowLayout());
		Button btnLoadData = new Button("Load data");
		btnLoadData.setName("btnLoadData");
		btnLoadData.setBackground(Color.blue);
		panelLoad.add(btnLoadData);
        Choice chServer = new Choice();
        chServer.setName("chServer");
        chServer.add("PostGre");
        chServer.add("DummyServer");
        panelLoad.add(chServer);
        Checkbox cbSticky = new Checkbox();
        cbSticky.setName("cbSticky");
        cbSticky.setLabel("Sticky");
        panelLoad.add(cbSticky);
        Button btnStore = new Button("Store");
        btnStore.setName("btnStore");
        btnStore.setBackground(Color.blue);
        panelLoad.add(btnStore);
		
		Panel panelZoom = new Panel(new FlowLayout());
		Button btnZTF = new Button("ZTF");
		btnZTF.setName("btnZTF");
		btnZTF.setBackground(Color.blue);
		Button btnZoomIn = new Button("+");
		btnZoomIn.setName("btnZoomIn");
		btnZoomIn.setBackground(Color.blue);
		Button btnZoomOut = new Button("-");
		btnZoomOut.setName("btnZoomOut");
		btnZoomOut.setBackground(Color.blue);
		panelZoom.add(btnZTF);
		panelZoom.add(btnZoomIn);
		panelZoom.add(btnZoomOut);

		Panel panelScroll = new Panel(new GridBagLayout());
		Button btnScrollUp = new Button("N");
		btnScrollUp.setName("btnScrollUp");
		btnScrollUp.setBackground(Color.blue);
		Button btnScrollDown = new Button("S");
		btnScrollDown.setName("btnScrollDown");
		btnScrollDown.setBackground(Color.blue);
		Button btnScrollLeft = new Button("W");
		btnScrollLeft.setName("btnScrollLeft");
		btnScrollLeft.setBackground(Color.blue);
		Button btnScrollRight = new Button("E");
		btnScrollRight.setName("btnScrollRight");
		btnScrollRight.setBackground(Color.blue);
		panelScroll.add(btnScrollUp);

		GridBagConstraints c = new GridBagConstraints();
		// margin
		c.insets = new Insets(3,3,3,3);
		
		// add buttons
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		panelScroll.add(btnScrollUp, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		panelScroll.add(btnScrollDown, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		panelScroll.add(btnScrollLeft, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		panelScroll.add(btnScrollRight, c);

		Panel panelRotate = new Panel(new FlowLayout());
		Button btnRotateLeft = new Button("Rotate left");
		btnRotateLeft.setName("btnRotateLeft");
		btnRotateLeft.setBackground(Color.blue);
		panelRotate.add(btnRotateLeft);
		Button btnRotateRight = new Button("Rotate right");
		btnRotateRight.setName("btnRotateRight");
		btnRotateRight.setBackground(Color.blue);
		panelRotate.add(btnRotateRight);
		
		Panel panelMapScale = new Panel(new FlowLayout());
		Label  labelMapScale = new Label("1 :", Label.RIGHT);
	    mTextFieldMapScale = new TextField("unknown");
	    panelMapScale.add(labelMapScale);
	    panelMapScale.add(mTextFieldMapScale);
	    
	    Button btnEnablePOI = new Button("POI");
	    btnEnablePOI.setName("btnEnablePOI");
	    btnEnablePOI.setBackground(Color.blue);

		// add panels to panel
		Panel panelButtons = new Panel(new FlowLayout());
		panelButtons.add(panelLoad);
		panelButtons.add(panelZoom);
		panelButtons.add(panelScroll);
		panelButtons.add(panelRotate);
		panelButtons.add(panelMapScale);
		panelButtons.add(btnEnablePOI);
		
		mPanelWarnings = new WarningPanel(new FlowLayout());
		
		mOverallPanel.add(mPanelWarnings, BorderLayout.NORTH);
		mOverallPanel.add(mDrawingPanel, BorderLayout.CENTER);
		mOverallPanel.add(panelButtons, BorderLayout.SOUTH);
		
		// add listeners
		btnLoadData.addActionListener(actionListener);
		btnZTF.addActionListener(actionListener);
		btnZoomIn.addActionListener(actionListener);
		btnZoomOut.addActionListener(actionListener);
		btnScrollDown.addActionListener(actionListener);
		btnScrollLeft.addActionListener(actionListener);
		btnScrollRight.addActionListener(actionListener);
		btnScrollUp.addActionListener(actionListener);
		btnRotateLeft.addActionListener(actionListener);
		btnRotateRight.addActionListener(actionListener);
		btnEnablePOI.addActionListener(actionListener);
		btnStore.addActionListener(actionListener);
		
		chServer.addItemListener(itemListener);
		cbSticky.addItemListener(itemListener);
		
		mDrawingPanel.addComponentListener(componentListener);
		mDrawingPanel.addMouseListener(mouseListener);
		mDrawingPanel.addMouseWheelListener(mouseWheelListener);
		mDrawingPanel.addMouseMotionListener(mouseMotionListener);
		mTextFieldMapScale.addActionListener(actionListener);
		mDrawingPanel.addKeyListener(keyListener);

	}
	
	/**
	 * Draws a rectangle in XOR-mode on the buffered image.
	 * @param _rect Rectangle to set
	 */
	@Override
	public void drawRect(Rectangle _rect) {
		eraseOldRect();
		mDragRect = _rect;
		Graphics g = mDrawingPanel.getGraphics();
		g.setXORMode(mRectColor);
		g.fillRect(_rect.x, _rect.y, _rect.width, _rect.height);
		g.setPaintMode();
	}
	
	/**
	 * Erases old visible rectangle from buffered image.
	 */
	@Override
	public void eraseOldRect() {
		if (mDragRect != null) {
			Graphics g = mDrawingPanel.getGraphics();
			g.setXORMode(mRectColor);
			g.fillRect(mDragRect.x, mDragRect.y, mDragRect.width, mDragRect.height);
			g.setPaintMode();
			mDragRect = null;
		}
	}
	
	@Override
	public void dragCopy(int _deltaX, int _deltaY) {
		// fast copying
		Graphics g = mDrawingPanel.getGraphics();
		
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, 0, mDrawingPanel.getHeight()); // left
		g.drawLine(mDrawingPanel.getWidth()-1, 0, mDrawingPanel.getWidth()-1, mDrawingPanel.getHeight()-1); // right
		g.drawLine(0, 0, mDrawingPanel.getWidth(), 0); // top
		g.drawLine(0, mDrawingPanel.getHeight()-1, mDrawingPanel.getWidth()-1, mDrawingPanel.getHeight()-1); // bottom
		
		g.copyArea(0, 0, mDrawingPanel.getWidth(), mDrawingPanel.getHeight(), _deltaX, _deltaY);
	}
	
	@Override
	public void update(BufferedImage _data) {
		mDrawingPanel.drawBufferedImage(_data);
	}

	@Override
	public void update(int _mapScale) {
		mTextFieldMapScale.setText(String.valueOf(_mapScale));
	}
	
	@Override
	public void setWarning(List<IWarningType> warningTypes) {
		mPanelWarnings.drawWarnings(warningTypes);
	}
	
	@Override
	public void removeWarnings() {
		// clear list
		mPanelWarnings.removeWarnings();
	}
}
