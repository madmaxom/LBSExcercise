package at.fhooe.mcm.cas.aal.parser;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import at.fhooe.mcm.cas.contexttype.ContextElement;

public class GsonParser implements IContextParser {

	@Override
	public List<ContextElement> parse(String context) {
		List<ContextElement> elements = new ArrayList<ContextElement>();
		
		// using custom deserializer
		Gson gson = new GsonBuilder()
		        .registerTypeAdapter(ContextElement.class,
		                new CustomContextElementJsonDeserializer())
		        .create();
		ContextElement elem = gson.fromJson(context, ContextElement.class);
		
		elements.add(elem);
		
		return elements;
	}
}
