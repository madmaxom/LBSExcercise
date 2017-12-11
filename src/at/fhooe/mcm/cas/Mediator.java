package at.fhooe.mcm.cas;


import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashSet;
import java.util.Set;

import at.fhooe.mcm.cas.compfactory.ComponentFactory;
import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.gis.geomodel.GeoObject;
import at.fhooe.mcm.cas.rule.container.RuleEvaluator;

public class Mediator implements IMediator, WindowListener{
	private Set<IComponent> iset = new HashSet<IComponent>();
	private TabbedPanel mTabbedPanel;
	private ComponentFactory mComponentFactory;
	
	public Mediator(TabbedPanel tp) {
		mTabbedPanel = tp;
		mComponentFactory = new ComponentFactory();
	}

	public void register(IComponent icomp) {
		icomp.setMediator(this);
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
	public void notifyComponents(GeoObject geoObject, IComponent origin) {
		for(IComponent icomp: iset) {
			if(!icomp.getName().equals(origin.getName())) {
				icomp.onGeoObjectUpdated(geoObject);
			}
		}
	}

	@Override
	public void notifyComponents(GPSPosition gpsPosition, IComponent origin) {
		for(IComponent icomp: iset) {
			if(!icomp.getName().equals(origin.getName())) {
				icomp.onGPSPositionUpdated(gpsPosition);
			}
		}
	}

	@Override
	public void notifyComponents(ContextSituation contextSituation, IComponent origin) {
		for(IComponent icomp: iset) {
			if(!icomp.getName().equals(origin.getName())) {
				icomp.onContextSituationUpdated(contextSituation);
			}
		}
	}

	@Override
	public void notifyComponents(ContextElement contextElement, IComponent origin) {
		for(IComponent icomp: iset) {
			if(!icomp.getName().equals(origin.getName())) {
				icomp.onContextElementUpdated(contextElement);
			}
		}
		
	}


	@Override
	public void notifyComponents(RuleEvaluator ruleEvaluator, IComponent origin) {
		for(IComponent icomp: iset) {
			if(!icomp.getName().equals(origin.getName())) {
				icomp.onRuleEvaluatorUpdated(ruleEvaluator);
			}
		}
	}
	
	@Override
	public void initComponents(String filename) {
		//TODO: implement getting content of file
		String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
				"<components xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"../../src/at/fhooe/mcm/cas/componentCompositionDataModel/ComponentComposition.xsd\">\n" +
				"<component name=\"at.fhooe.mcm.cas.GISComponent\"/>\n" +
				"<component name=\"at.fhooe.mcm.cas.POIComponent\"/>\n" +
				"<component name=\"at.fhooe.mcm.cas.GPSComponent\"/>\n" +
				"</components>";
		input = mComponentFactory.getComponentCompositionFromFile(filename);
		IComponent[] components = mComponentFactory.buildComponents(input);
		for (IComponent component : components) {
			register(component);
			component.setMediator(this);
		} // for each component
	}
}

