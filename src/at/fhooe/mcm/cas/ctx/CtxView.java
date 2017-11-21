package at.fhooe.mcm.cas.ctx;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.TextField;
import java.util.Date;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.contexttype.ContextFuel;
import at.fhooe.mcm.cas.contexttype.ContextHumidity;
import at.fhooe.mcm.cas.contexttype.ContextPressure;
import at.fhooe.mcm.cas.contexttype.ContextTemperature;
import at.fhooe.mcm.cas.contexttype.ContextTimeOfTheDay;

public class CtxView implements CtxObserver {

	/**
	 * Color for drawing drag rectangle.
	 */
	Color mRectColor = Color.GREEN;
	
	/**
	 * Currently visible rectangle.
	 */
	private Rectangle mDragRect;
	
	Panel mOverallPanel;
	JSpinner[] mSpinners = new JSpinner[6];
	
	/**
	 * TextField which shows map scale value.
	 */
	TextField mTextFieldMapScale;
	/**
	 * Controller which reacts on events.
	 */
	CtxController mController;
		
	/**
	 * Constructor, initializes member variables.
	 * @param _c Controller for events
	 */
	public CtxView(CtxController _c) {
		mController = _c;
		createPanel();
	}
	
	public Panel getPanel() {
		return mOverallPanel;
	}
	
	/**
	 * Creates frame with panel and button, and sets some listeners.
	 */
	public void createPanel() {
		mOverallPanel = new Panel(new GridBagLayout());
		
		Label transFrequ = new Label("Transmission Frequency [Sec.]");
		mOverallPanel.add(transFrequ);
		
		JSpinner js = new JSpinner();
		js.setModel(new SpinnerNumberModel(10,1,20,1));
		((JSpinner.DefaultEditor) js.getEditor()).getTextField().setEditable(false);
		js.addChangeListener((ChangeListener) mController);
		js.setName("TransFrequ");
		mOverallPanel.add((java.awt.Component)js);

		Label timeOfTheDay = new Label("Time");
		mOverallPanel.add(timeOfTheDay);
		JSpinner dateSpinner = new JSpinner();
		SpinnerDateModel sdm = new SpinnerDateModel();
		dateSpinner.setModel(sdm);
		dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "hh:mm a"));
		((JSpinner.DefaultEditor) dateSpinner.getEditor()).getTextField().setEditable(false);
		dateSpinner.addChangeListener((ChangeListener) mController);
		dateSpinner.setName("TimeOfTheDay");
		mOverallPanel.add((java.awt.Component)dateSpinner);
		
		Label temperature = new Label("Temperature[°C]");
		mOverallPanel.add(temperature);
		JSpinner tempSpinner = new JSpinner();
		tempSpinner.setModel(new SpinnerNumberModel(1,-50,+50,1));
		((JSpinner.DefaultEditor) tempSpinner.getEditor()).getTextField().setEditable(false);
		tempSpinner.addChangeListener((ChangeListener) mController);
		tempSpinner.setName("Temperature");
		mOverallPanel.add((java.awt.Component)tempSpinner);
		
		Label fuel = new Label("Fuel[L]");
		mOverallPanel.add(fuel);
		JSpinner fuelSpinner = new JSpinner();
		fuelSpinner.setModel(new SpinnerNumberModel(1,0,100,1));
		((JSpinner.DefaultEditor) fuelSpinner.getEditor()).getTextField().setEditable(false);
		fuelSpinner.addChangeListener((ChangeListener) mController);
		fuelSpinner.setName("Fuel");
		mOverallPanel.add((java.awt.Component)fuelSpinner);
		
		Label humidity = new Label("Humidity[%]");
		mOverallPanel.add(humidity);
		JSpinner humiditySpinner = new JSpinner();
		humiditySpinner.setModel(new SpinnerNumberModel(50,0,100,1));
		((JSpinner.DefaultEditor) humiditySpinner.getEditor()).getTextField().setEditable(false);
		humiditySpinner.addChangeListener((ChangeListener) mController);
		humiditySpinner.setName("Humidity");
		mOverallPanel.add((java.awt.Component)humiditySpinner);
		
		Label pressure = new Label("Atmospheric pressure[mbar]");
		mOverallPanel.add(pressure);
		JSpinner pressureSpinner = new JSpinner();
		pressureSpinner.setModel(new SpinnerNumberModel(1013,900,1500,1));
		((JSpinner.DefaultEditor) pressureSpinner.getEditor()).getTextField().setEditable(false);
		pressureSpinner.addChangeListener((ChangeListener) mController);
		pressureSpinner.setName("Pressure");
		mOverallPanel.add((java.awt.Component)pressureSpinner);
		
		mSpinners[0] = js;
		mSpinners[1] = dateSpinner;
		mSpinners[2] = tempSpinner;
		mSpinners[3] = fuelSpinner;
		mSpinners[4] = humiditySpinner;
		mSpinners[5] = pressureSpinner;
	}

	@Override
	public void updateCtx(ContextElement ce) {
		switch(ce.getType()) {
		case "TimeOfTheDay":
			SpinnerDateModel sdm = new SpinnerDateModel();
			ContextTimeOfTheDay temp = (ContextTimeOfTheDay)ce;
			sdm.setValue(new Date(temp.getYear(), temp.getMonth(), temp.getDay(), temp.getHour(), temp.getMinuts()));
			mSpinners[1].setModel(sdm);
			((JSpinner.DefaultEditor) mSpinners[1].getEditor()).getTextField().setEditable(false);
			break;
		case "Temperature":
			((JSpinner.NumberEditor)mSpinners[2].getEditor()).getTextField().setText("" + ((ContextTemperature)ce).getTemperature());
			break;
		case "Fuel":
			((JSpinner.NumberEditor)mSpinners[1].getEditor()).getTextField().setText("" + ((ContextFuel)ce).getFuelStatus());
			break;
		case "Humidity":
			((JSpinner.NumberEditor)mSpinners[1].getEditor()).getTextField().setText("" + ((ContextHumidity)ce).getHumidity());
			break;
		case "Pressure":
			((JSpinner.NumberEditor)mSpinners[1].getEditor()).getTextField().setText("" + ((ContextPressure)ce).getPressure());
			break;
		default:
			//do nothing
		}
	}
}
