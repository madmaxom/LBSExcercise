package at.fhooe.mcm.cas.rule.container;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import at.fhooe.mcm.cas.ContextSituation;
import at.fhooe.mcm.cas.IComponent;
import at.fhooe.mcm.cas.compiler.treenode.NodeError;
import at.fhooe.mcm.cas.compiler.treenode.TreeNode;
import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.compiler.generated.*;
import at.fhooe.mcm.cas.compiler.generated.Compiler;

public class RuleContainer {
	private TreeNode mConditionRoot = null;
	private Action mAction;
	private String mCondition;
	
	public RuleContainer(String _condition, Action _action) {
		mCondition = _condition;
		mAction = _action;
		
		InputStream input = new ByteArrayInputStream(_condition.getBytes());
		Compiler compiler = new Compiler(input);
		try {
			mConditionRoot = compiler.stmt();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public Action getAction() {
		return mAction;
	}
	
	public String getCondition() {
		return mCondition;
	}
	
	public boolean valid(ContextSituation _sit) {
		if(_sit == null) {
			System.out.println(this.getClass().getSimpleName() + ": Rule not processable. No ContextSituation");
			return false;
		}
		if(mConditionRoot != null) {
			
			// needed elements with placeholder
			List<String> neededElements = mConditionRoot.getContextElements();
			List<ContextElement> variableParameters = new ArrayList<>();
			
			for (String neededElement : neededElements) {
				
				if (neededElement != null) {
					ContextElement contextElement = getContextElement(_sit, neededElement);
					variableParameters.add(contextElement);
				} else {
					// add placeholder
					variableParameters.add(null);
				}	
			}
			
			System.out.println();
			System.out.print("Evaluating: Rule: ");
			System.out.print(mCondition);
			System.out.print(", Current params: " + variableParameters.toString());
						
			mConditionRoot.setVariableParameters(variableParameters);
			try {
				boolean valid = (boolean) mConditionRoot.calculate();
				System.out.print(" -> " + valid);
				System.out.println();
				return valid;
			} catch (NodeError e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	private ContextElement getContextElement(ContextSituation sit, String neededElement) {
		for (ContextElement c : sit.mContextElements) {
			if (c.getType().equals(neededElement)) {
				// found element of this type
				return c;
			}
		}
		System.out.println(this.getClass().getSimpleName() + ": no ContextElement found for " + neededElement);
		return null;
	}

	public void execute(IComponent comp) {
		System.out.println(this.getClass().getSimpleName() + ": Execute action " + mAction.toString());
		
		try {
			// Class to call method
			Class c = comp.getClass();
			if (!mAction.getClazz().equals(c.getName())) {
				// action not defined for this class, return
				return;
			}
			
			// Construct parameter types and value objects
			List<Parameter> paramList = mAction.getParameter();
			Class[] param = new Class[paramList.size()];
			Object[] values = new Object[paramList.size()];
			for (int i = 0; i < paramList.size(); i++) {
				Class p = Class.forName(paramList.get(i).getParameterType());
				param[i] = p;
				Class v = Class.forName(paramList.get(i).getParameterValue());
				Object obj = v.newInstance();
				values[i] = obj;
			}
			
			// get and call method
			Method m = c.getMethod(mAction.getMethod(), param);
			m.invoke(comp, values);
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}