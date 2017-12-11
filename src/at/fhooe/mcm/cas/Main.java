package at.fhooe.mcm.cas;

import javax.swing.JFrame;



public class Main {
	public static void main(String[] args) {

		// tab panel
		TabbedPanel tp = new TabbedPanel();
		tp.setSize(1000, 800);
        tp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tp.setVisible(true);
        
        // mediator, holding all components
		IMediator mediator = new Mediator(tp);
		
//		// init components
//		IComponent gisComponent = new GISComponent(mediator, "Map");
//		IComponent aalComponent = new AALComponent(mediator, "AAL");
//		IComponent gpsComponent = new GPSComponent(mediator, "GPS");
//		IComponent poiComponent = new POIComponent(mediator, "POI");
//		IComponent conComponent = new ContextMgmtComponent(mediator, "Context");
//		IComponent ruleComponent = new RuleComponent(mediator, "Rule");
//		
//		// register components to mediator
//		mediator.register(gisComponent);
//		mediator.register(aalComponent);
//		mediator.register(gpsComponent);
//		mediator.register(poiComponent);
//		mediator.register(conComponent);
//		mediator.register(ruleComponent);
		
		mediator.initComponents("files/components/ComponentComposition.xml"); //init components from file
	}
}
