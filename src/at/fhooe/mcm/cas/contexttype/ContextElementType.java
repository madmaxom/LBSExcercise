package at.fhooe.mcm.cas.contexttype;

public class ContextElementType {
	public static final String START_TAG = "contextElement";
	public static final String CONTEXT_ID = "contextId";
	public static final String CONTEXT_KEY = "contextKey";
	public static final String CONTEXT_VALUE = "contextValue";
	
	public static final String CONTEXT_ELEMENT_FUEL = "fuel";
	public static final String CONTEXT_ELEMENT_POSITION = "position";
	public static final String CONTEXT_ELEMENT_TEMPERATURE = "temperature";
	public static final String CONTEXT_ELEMENT_TIME_OF_THE_DAY = "timeOfTheDay";
	public static final String CONTEXT_ELEMENT_HUMIDITY = "humidity";
	public static final String CONTEXT_ELEMENT_PRESSURE = "pressure";
	
	
	public class ContextElementFuel {
		public static final String FUEL_STATUS = "fuelStatus";
	}
	
	public class ContextElementPosition {
		public static final String COORDINATE_TYPE = "coordinateType";
		public static final String X_VALUE = "xValue";
		public static final String Y_VALUE = "yValue";
	}
	
	public class ContextElementTemperature {
		public static final String TEMPERATURE = "temperature";
	}
	
	public class ContextElementTimeOfTheDay {
		public static final String TIME = "time";
		public static final String DATE = "date";
		
		public class Time {
			public static final String HOUR = "hour";
			public static final String MINUTES = "minuts";
		}
		public class Date {
			public static final String YEAR = "year";
			public static final String MONTH = "month";
			public static final String DAY = "day";
		}
	}
}
