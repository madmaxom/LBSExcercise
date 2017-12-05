package at.fhooe.mcm.cas.contexttype;

public class ContextTimeOfTheDay extends ContextElement {
    private int hour = 0;
    private int minuts = 0;
    private int year = 0;
    private int month = 0;
    private int day = 0;

    public ContextTimeOfTheDay() {
    	
    }
    
    public ContextTimeOfTheDay(int hour, int minuts, int year, int month, int day) {
        this.hour = hour;
        this.minuts = minuts;
        this.year = year;
        this.month = month;
        this.day = day;
        setType(ContextElementType.CONTEXT_ELEMENT_TIME_OF_THE_DAY);
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinuts() {
        return minuts;
    }

    public void setMinuts(int minuts) {
        this.minuts = minuts;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "ContextTimeOfTheDay{" +
                "hour=" + hour +
                ", minuts=" + minuts +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
    
    @Override
	public void setData(ContextElement ce) {
		this.setContextMetaData(ce.getContextMetaData());
		this.setId(ce.getId());
		this.setKey(ce.getKey());
		ContextTimeOfTheDay cp = (ContextTimeOfTheDay)ce;
		this.hour = cp.hour;
        this.minuts = cp.minuts;
        this.year = cp.year;
        this.month = cp.month;
        this.day = cp.day;
	}
}
