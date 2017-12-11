package at.fhooe.mcm.cas.aal.parser;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.contexttype.ContextElementType;
import at.fhooe.mcm.cas.contexttype.ContextFuel;
import at.fhooe.mcm.cas.contexttype.ContextPosition;
import at.fhooe.mcm.cas.contexttype.ContextTemperature;
import at.fhooe.mcm.cas.contexttype.ContextTimeOfTheDay;

public class CustomContextElementJsonDeserializer implements JsonDeserializer<ContextElement>
{
	
	
    @Override
    public ContextElement deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
                              throws JsonParseException
    {
    	ContextElement contextElement = null;
    	
    	JsonObject jobject = je.getAsJsonObject();
    	JsonObject contextElementObj = jobject.get(ContextElementType.START_TAG).getAsJsonObject();
    	String contextKey = contextElementObj.get(ContextElementType.CONTEXT_KEY).getAsString();
    	
    	JsonObject contextValueObj = contextElementObj.get(ContextElementType.CONTEXT_VALUE).getAsJsonObject();
    	
    	switch(contextKey) {
	    	case ContextElementType.CONTEXT_ELEMENT_FUEL: {
	    		ContextFuel contextFuel = new ContextFuel();
	    		contextFuel.setFuelStatus(contextValueObj.get(ContextElementType.ContextElementFuel.FUEL_STATUS).getAsInt());
	    		
	    		contextElement = contextFuel;
	    		break;
	    	}
	    	case ContextElementType.CONTEXT_ELEMENT_POSITION: {
	    		ContextPosition contextPosition = new ContextPosition();
	    		contextPosition.setCoordinateType(contextValueObj.get(ContextElementType.ContextElementPosition.COORDINATE_TYPE).getAsString());
	    		contextPosition.setxValue(contextValueObj.get(ContextElementType.ContextElementPosition.X_VALUE).getAsDouble());
	    		contextPosition.setyValue(contextValueObj.get(ContextElementType.ContextElementPosition.Y_VALUE).getAsDouble());
	    		
	    		
	    		contextElement = contextPosition;
	    		break;
	    	}
	    	case ContextElementType.CONTEXT_ELEMENT_TEMPERATURE: {
	    		ContextTemperature contextTemperature = new ContextTemperature();
	    		contextTemperature.setTemperature(contextValueObj.get(ContextElementType.ContextElementTemperature.TEMPERATURE).getAsInt());
	    		
	    		contextElement = contextTemperature;
	    		break;
	    	}
	    	case ContextElementType.CONTEXT_ELEMENT_TIME_OF_THE_DAY: {
	    		ContextTimeOfTheDay contextTimeOfTheDay = new ContextTimeOfTheDay();
	    		
	    		// time
	    		JsonObject timeObj = contextValueObj.get(ContextElementType.ContextElementTimeOfTheDay.TIME).getAsJsonObject();
	    		contextTimeOfTheDay.setHour(timeObj.get(ContextElementType.ContextElementTimeOfTheDay.Time.HOUR).getAsInt());
	    		contextTimeOfTheDay.setMinuts(timeObj.get(ContextElementType.ContextElementTimeOfTheDay.Time.HOUR).getAsInt());
	    		
	    		// date
	    		JsonObject dateObj = contextValueObj.get(ContextElementType.ContextElementTimeOfTheDay.DATE).getAsJsonObject();
	    		contextTimeOfTheDay.setYear(dateObj.get(ContextElementType.ContextElementTimeOfTheDay.Date.YEAR).getAsInt());
	    		contextTimeOfTheDay.setMonth(dateObj.get(ContextElementType.ContextElementTimeOfTheDay.Date.MONTH).getAsInt());
	    		contextTimeOfTheDay.setDay(dateObj.get(ContextElementType.ContextElementTimeOfTheDay.Date.DAY).getAsInt());
	    		
	    		contextElement = contextTimeOfTheDay;
	    		break;
	    	}
        	
    	} // end switch
    	
		setCommonParameters(contextElement, contextElementObj);
        return contextElement;
    }

	private void setCommonParameters(ContextElement contextElement, JsonObject jobject) {
		contextElement.setId(jobject.get("contextId").getAsInt());
		contextElement.setKey(jobject.get("contextKey").getAsString());
	}
}
