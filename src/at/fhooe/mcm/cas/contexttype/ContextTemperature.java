package at.fhooe.mcm.cas.contexttype;

public class ContextTemperature {
    private int temperature = 0;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
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
