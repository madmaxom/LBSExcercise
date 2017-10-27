package at.fhooe.mcm.cas;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		Mediator mediator = new Mediator();
		TabbedPanel tp = new TabbedPanel();
		tp.setSize(640, 480);
        tp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tp.setVisible(true);
	}
}
