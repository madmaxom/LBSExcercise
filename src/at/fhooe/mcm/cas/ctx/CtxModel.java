package at.fhooe.mcm.cas.ctx;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.contexttype.ContextElementType;
import at.fhooe.mcm.cas.contexttype.ContextFuel;
import at.fhooe.mcm.cas.contexttype.ContextHumidity;
import at.fhooe.mcm.cas.contexttype.ContextPressure;
import at.fhooe.mcm.cas.contexttype.ContextTemperature;
import at.fhooe.mcm.cas.contexttype.ContextTimeOfTheDay;
import at.fhooe.mcm.cas.gis.DataObserver;

public class CtxModel {
	
	private int mUpdateFrequency = 10, mTemperature = 20, mFuel = 11, mHumidity = 50, mPressure = 1013;
	private Date mDate = new Date();
	
	private List<CtxObserver> mObservers;
	
	public CtxModel() {
		mObservers = new ArrayList<>();
	}

	public void addCtxObserver(CtxView v) {
		mObservers.add(v);
	}
	
	private void notifyObservers(ContextElement ce) {
		// notify
		for (CtxObserver o : mObservers) {
			o.updateCtx(ce);
		}
	}
	
	public Date getDate() {
		return mDate;
	}
	public void setDate(Date date) {
		mDate = date;
	}
	public int getUpdateFrequency() {
		return mUpdateFrequency;
	}
	public void setUpdateFrequency(int frequency) {
		mUpdateFrequency = frequency;
	}
	public int getTemperature() {
		return mTemperature;
	}
	public void setTemperature(int temperature) {
		mTemperature = temperature;
	}
	public int getFuel() {
		return mFuel;
	}
	public void setFuel(int fuel) {
		mFuel = fuel;
	}
	public int getHumidity() {
		return mHumidity;
	}
	public void setHumidity(int humidity) {
		mHumidity = humidity;
	}
	public int getPressure() {
		return mPressure;
	}
	public void setPressure(int pressure) {
		mPressure = pressure;
	}
	
	public void updateContext(ContextElement ce) {
		switch(ce.getType()) {
		case ContextElementType.CONTEXT_ELEMENT_TEMPERATURE:
			setTemperature(((ContextTemperature)ce).getTemperature());
			break;
		case ContextElementType.CONTEXT_ELEMENT_TIME_OF_THE_DAY:
			ContextTimeOfTheDay temp = (ContextTimeOfTheDay)ce;
			setDate(new Date(temp.getYear(), temp.getMonth(), temp.getDay(), temp.getHour(), temp.getMinuts()));
			break;
		case ContextElementType.CONTEXT_ELEMENT_FUEL:
			setFuel(((ContextFuel)ce).getFuelStatus());
			break;
		case ContextElementType.CONTEXT_ELEMENT_HUMIDITY:
			setHumidity(((ContextHumidity)ce).getHumidity());
			break;
		case ContextElementType.CONTEXT_ELEMENT_PRESSURE:
			setHumidity(((ContextPressure)ce).getPressure());
			break;
		}
		notifyObservers(ce);
	}
	
	public List getContext() {
		List<ContextElement> ces = new ArrayList<>();
		ces.add(new ContextTemperature(mTemperature));
		ces.add(new ContextTimeOfTheDay(mDate.getHours(),mDate.getMinutes(), mDate.getYear(), mDate.getMonth(), mDate.getDay()));
		ces.add(new ContextFuel(mFuel));
		ces.add(new ContextHumidity(mHumidity));
		ces.add(new ContextPressure(mPressure));
		return ces;
	}
}
