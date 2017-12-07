package at.fhooe.mcm.cas.rule.container;

import java.util.List;

public class Action {

	private String clazz;
	private String method;
	private List<Parameter> parameters;
	
	public List<Parameter> getParameter() {
		return parameters;
	}
	public void setParameter(List<Parameter> parameter) {
		this.parameters = parameter;
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	
	
}
