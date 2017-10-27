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
		Mediator mediator = new Mediator(tp);
		
		// init components
		IComponent gisComponent = new GISComponent();
		IComponent gpsComponent = new GPSComponent();
		
		// register components to mediator
		mediator.register(gisComponent);
		mediator.register(gpsComponent);
	}
}
