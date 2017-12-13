package at.fhooe.mcm.cas.gis;

import java.awt.Panel;
import java.awt.Rectangle;
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

public abstract class AbstractGISView implements DataObserver, IUIView { 
	public abstract Panel getPanel();
	public abstract void createPanel();
	public abstract void drawRect(Rectangle _rect);
	public abstract void eraseOldRect();
	public abstract void dragCopy(int _deltaX, int _deltaY) ;
	public abstract void update(BufferedImage _data);
	public abstract void update(int _mapScale);
	public abstract void setWarning(List<IWarningType> warningTypes);
	public abstract void removeWarnings();
	
	protected ActionListener actionListener;
	protected ItemListener itemListener;
	protected ComponentListener componentListener;
	protected MouseListener mouseListener;
	protected MouseWheelListener mouseWheelListener;
	protected MouseMotionListener mouseMotionListener;
	protected KeyListener keyListener;
	
	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}
	
	public void setItemListener(ItemListener itemListener) {
		this.itemListener = itemListener;
	}
	
	public void setComponentListener(ComponentListener componentListener) {
		this.componentListener = componentListener;
	}
	
	public void setMouseListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}
	
	public void setMouseWheelListener (MouseWheelListener mouseWheelListener) {
		this.mouseWheelListener = mouseWheelListener;
	}
	
	public void setMouseMotionListener(MouseMotionListener mouseMotionListener) {
		this.mouseMotionListener = mouseMotionListener;
	}
	
	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
	}
}
