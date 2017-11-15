package at.fhooe.mcm.cas.contexttype;

public class ContextTemperature extends ContextElement {
    private int temperature = 0;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public ContextTemperature() {
    	
    }
    
    public ContextTemperature(int temperature) {

        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "ContextTemperature{" +
                "temperature=" + temperature +
                '}';
    }
}
