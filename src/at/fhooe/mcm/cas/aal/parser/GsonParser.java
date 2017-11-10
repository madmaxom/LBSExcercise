package at.fhooe.mcm.cas.aal.parser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import at.fhooe.mcm.cas.aal.ContextElement;

public class GsonParser implements IContextParser {

	@Override
	public List<ContextElement> parse(String context) {
		List<ContextElement> elements = new ArrayList<ContextElement>();
		
		Gson gson = new Gson();
		
		// single object
		// String json = {\"s1\":\"1\",\"s2\":\"2\"};
		// ContextElement obj = gson.fromJson(json, ContextElement.class);
		// elements.add(obj);
		
		// collection
		String json = "[{\"s1\":\"1\",\"s2\":\"2\"}, {\"s1\":\"3\",\"s2\":\"4\"}]";
		Type collectionType = new TypeToken<Collection<ContextElement>>(){}.getType();
		Collection<ContextElement> objList = gson.fromJson(json, collectionType);
		elements.addAll(objList);


		return elements;
	}

}
