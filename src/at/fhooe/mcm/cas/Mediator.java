package at.fhooe.mcm.cas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashSet;
import java.util.Set;

public class Mediator implements IMediator, WindowListener{
	private Set<IComponent> iset = new HashSet<>();
	
	public Mediator() {
		
	}
	
	public void register(IComponent icomp) {
		iset.add(icomp);
	}
	
	public void unregister(IComponent icomp) {
		iset.remove(icomp);
	}
	
	public void sendGeoObject(GeoObject geoObject){
		for(IComponent ic : iset) {
			ic.onGeoObjectUpdated(geoObject);
		}
	}
	
	public void sendContextElement(ContextElement contextElement){
		for(IComponent ic : iset) {
			ic.onContextElementUpdated(contextElement);
		}
	}
	
	public void sendContextSituation(ContextSituation contextSituation){
		for(IComponent ic : iset) {
			ic.onContextSituationUpdated(contextSituation);
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

