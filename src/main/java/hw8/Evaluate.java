package hw8;

import java.util.Stack;

public class Evaluate extends Visitor{
	private Context context;
	private Stack<Boolean> values;
	private Boolean temp1;
	private Boolean temp2;
	// Rep invariant:
	// context != null, values == Stack<Boolean>
	// Abstraction Function: private Context context = context from the constructor,
	// value is created by looking up the values of individual variable expressions with visiting and
	// will either contain 0 values on initialization, many values while visiting is occurring, and
	// 1 value afterwards. temp1 and temp2 are private booleans used to manipulate data and nothing more
	
	/**
	 * @param: Context c
	 * @effects: Sets private Context context = c, creates a new Stack<Boolean> and sets values = to it
	 * @modifies: private Context context is modified, private Stack<Boolean> values is modified 	
	*/
	public Evaluate(Context c) // Constructor for Evaluate
	{
		context = c;
		values = new Stack<Boolean>();
	}
	
	/**
	 * @param: VarExp e
	 * @effects: Uses Context to lookup value of VarExp e and add the evaluated expression to values.
	 * @modifies: private Stack<Boolean> values is modified 
	*/
	public void visit(VarExp e)//Looks up value of VarStrings private variable using getter, then pushes
	{ 
		values.push(context.lookup(e.getVarString()));
	}
	
	/**
	 * @param: Constant e
	 * @effects: Uses Context to lookup value of Constant e, adds the evaluated expression to values.
	 * @modifies: private Stack<Boolean> values is modified 
	*/
	public void visit(Constant e) // Looks up value of Constants value using getter, then pushes it
	{ 
		values.push(e.getValue());
	}
	
	/**
	 * @param: AndExp e
	 * @effects: Pops two most recent values off the stack and ands them together to evaluate the
	 * 			 expression of AndExp e, adds that new value to the stack
	 * @modifies: private Stack<Boolean> values is modified 
	*/
	public void visit(AndExp e) // Pops off last two values added, ands them, and pushes that new value
	{ 
		temp1 = values.pop();
		temp2 = values.pop();
		values.push(temp1 && temp2);
	}
	
	/**
	 * @param: OrExp e
	 * @effects: Pops two most recent values off the stack and ors them together to evaluate the
	 * 			 expression of OrExp e, adds that new value to the stack
	 * @modifies: private Stack<Boolean> values is modified 
	*/
	public void visit(OrExp e) // Pops off last two values added, ors them, and pushes that new value
	{ 
		temp1 = values.pop();
		temp2 = values.pop();
		values.push(temp1 || temp2);
	}
	
	/**
	 * @param: NotExp e
	 * @effects: Pops the most recent value off the stack then negates it and re-adds it to the stack to
	 *			 evaluate the expression of NotExp e.
	 * @modifies: private Stack<Boolean> values is modified 
	*/
	public void visit(NotExp e) // Pops off last value added, negates it, and pushes that new value
	{ 
		values.push(!values.pop());
	}
	
	/**
	 * @returns: Returns the head of the stack without removing (should be the only value or null)
	*/
	public Boolean getResult() // Peeks to get the top and returns it
	{
		return values.peek();
	}
}
