package at.fhooe.mcm.cas;


import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashSet;
import java.util.Set;

import at.fhooe.mcm.cas.gis.geomodel.GeoObject;

public class Mediator implements IMediator, WindowListener, CommunicationObserver{
	private Set<IComponent> iset = new HashSet<>();
	private TabbedPanel mTabbedPanel;
	
	public Mediator(TabbedPanel tp) {
		mTabbedPanel = tp;
	}

	public void register(IComponent icomp) {
		iset.add(icomp);
		mTabbedPanel.insertPanel(icomp);
	}
	
	public void unregister(IComponent icomp) {
		iset.remove(icomp);
		mTabbedPanel.removePanel(icomp);
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

	@Override
	public void onGeoObjectUpdated(GeoObject geoObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onContextElementUpdated(ContextElement contextElement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onContextSituationUpdated(ContextSituation contextSituation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyComponents(GeoObject geoObject, IComponent origin) {
		for(IComponent icomp: iset) {
			if(!icomp.getName().equals(origin.getName())) {
				icomp.onGeoObjectUpdated(geoObject);
			}
		}
	}

	@Override
	public void notifyComponents(ContextElement contextElement, GISComponent origin) {
		for(IComponent icomp: iset) {
			if(!icomp.getName().equals(origin.getName())) {
				icomp.onContextElementUpdated(contextElement);
			}
		}
	}

	@Override
	public void notifyComponents(ContextSituation contextSituation, GISComponent origin) {
		for(IComponent icomp: iset) {
			if(!icomp.getName().equals(origin.getName())) {
				icomp.onContextSituationUpdated(contextSituation);
			}
		}
	}
}

