package at.fhooe.mcm.cas.rule.container;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import at.fhooe.mcm.cas.ContextSituation;
import at.fhooe.mcm.cas.compiler.treenode.NodeError;
import at.fhooe.mcm.cas.compiler.treenode.TreeNode;
import at.fhooe.mcm.cas.contexttype.ContextElement;
import at.fhooe.mcm.cas.contexttype.ContextFuel;
import at.fhooe.mcm.cas.contexttype.ContextTemperature;
import at.fhooe.mcm.cas.compiler.generated.*;
import at.fhooe.mcm.cas.compiler.generated.Compiler;

public class RuleContainer {
	private TreeNode mConditionRoot = null;
	private Action mAction = null;
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
	
	public boolean valid(ContextSituation _sit) {
		if(_sit == null) {
			return false;
		}
		if(mConditionRoot != null) {
			for(ContextElement ce : _sit.mContextElements) {
				try {
					Object[] obj = null;
					
					String valueStr = "";
					if(ce instanceof ContextTemperature) {
						Object value = ((ContextTemperature)ce).getTemperature();
						obj = new Object[] { value };	
						valueStr = value.toString();
					} else if (ce instanceof ContextFuel) {
						
					} else {
						// System.out.print("unknown context type");
						return false;
					}
					
					
					System.out.println();
					System.out.print("Evaluating: Rule: ");
					System.out.print(mCondition);
					System.out.print(", Value: ");
					System.out.print(valueStr);
					
					
					mConditionRoot.setVariableParameters(obj);
					boolean valid = (boolean) mConditionRoot.calculate();
					System.out.print(" -> " + valid);
					
					
				} catch (NodeError e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		return true;
	}
	
	public void execute() {
		
	}
}