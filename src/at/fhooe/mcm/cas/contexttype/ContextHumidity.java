package at.fhooe.mcm.cas.contexttype;

public class ContextHumidity extends ContextElement {
	private int humidity = 0;

    public ContextHumidity() {
    	setType(ContextElementType.CONTEXT_ELEMENT_HUMIDITY);
    }
    
    public ContextHumidity(int humidity) {
        this.humidity = humidity;
        setType(ContextElementType.CONTEXT_ELEMENT_HUMIDITY);
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "ContextHumidity{" +
                "humidity=" + humidity +
                '}';
    }
}
