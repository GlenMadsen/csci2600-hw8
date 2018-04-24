package hw8;

import java.util.Stack;

public class PrintInorder extends Visitor{
	private Stack<String> values;
	private StringBuffer temp1;
	private StringBuffer temp2;
	// Rep invariant:
	// values == Stack<String>
	// Abstraction Function: private Context context = context from the constructor,
	// value is created by looking up the values of individual variable expressions with visiting and
	// will either contain 0 values on initialization, many values while visiting is occurring, and
	// 1 value afterwards. temp1 and temp2 are private StringBuffers used to manipulate data only.
	
	/**
	 * @param: Context c
	 * @effects: Creates a new Stack<String> and sets values = to it
	 * @modifies: Private Stack<String> values is modified 	
	*/
	public PrintInorder() // Constructor for PrintInorder
	{
		values = new Stack<String>();
	}
	
	/**
	 * @param: VarExp e
	 * @effects: Gets the value of VarExp using getters and adds it to values.
	 * @modifies: private Stack<String> values is modified 
	*/
	public void visit(VarExp e) // Gets the String from VarExp e using the getter and pushes it
	{ 
		values.push(e.getVarString());
	}
	
	/**
	 * @param: Constant e
	 * @effects:Uses String.valueOf to get a string out of a boolean from Constant e then add it values
	 * @modifies: private Stack<String> values is modified 
	*/
	public void visit(Constant e)//Gets the String from VarExp e using the getter/valueOf and pushes it
	{ 
		values.push(String.valueOf(e.getValue()));
	}
	
	// Notes about the PrintInOrder Class:
		// I could have template-d it so that there was less code duplication, mostly between AndExp,
		// OrExp, and NotExp, this would have been done similarly to PrintInOrder in CompositieExp.
		// However the HW8 html did not specify they had to be template-d and it would have required
		// some drastic, possibly unwanted changes to evaluate as well (some code nonsense). As such 
	    // I left it as is, which is correct, but not as short as it could be.
	// End Notes.
	
	/**
	 * @param: AndExp e
	 * @effects: Pops two most recent values off the stack and determines the necessary parenthesis
	 * 			 or otherwise to build the String expression of AndExp e, adds that new value to the 
	 * 			 stack
	 * @modifies: private Stack<String> values is modified 
	*/
	public void visit(AndExp e)
	{ 
		temp1 = new StringBuffer(); // Uses string buffers to perform intermediary String construction
		temp2 = new StringBuffer();
		if(e.getRight() instanceof CompositeExp)
		{ // Determines if parenthesis need to be added, then constructs the necessary String
			if (((CompositeExp) e.getRight()).getExpressionCode() > BooleanExp.AND) {
				// if left operand is an expression of equal or lower precedence, parens are needed 
				temp1.append("(").append(values.pop()).append(")");
			} // Pops values to get necessary elements to use in String construction
			else{ temp1.append(values.pop());}
		}
		else {
			// otherwise, i.e., of higher precedence, no parens needed
			temp1.append(values.pop()); 
		}
		// The same as before but with the left and 2nd last used value
		if(e.getLeft() instanceof CompositeExp)
		{
			if (((CompositeExp) e.getLeft()).getExpressionCode() >= BooleanExp.AND) {
				temp2.append("(").append(values.pop()).append(")");
			}
			else{ temp2.append(values.pop());}
		}
		else {
			temp2.append(values.pop()); 
		} // Appends the Strings in reverse (correct) order because of how they were removed from
		temp2.append(" AND ").append(temp1); //The Stack, and places ' OR ' in the middle, pushes final
		values.push(temp2.toString()); // value to values
	}
	
	/**
	 * @param: OrExp e
	 * @effects: Pops two most recent values off the stack and determines the necessary parenthesis
	 * 			 or otherwise to build the String expression of OrExp e, adds that new value to the 
	 * 			 stack
	 * @modifies: private Stack<String> values is modified 
	*/
	public void visit(OrExp e)
	{ 
		temp1 = new StringBuffer(); // Uses string buffers to perform intermediary String construction
		temp2 = new StringBuffer();
		if(e.getRight() instanceof CompositeExp)
		{ // Determines if parenthesis need to be added, then constructs the necessary String
			if (((CompositeExp) e.getRight()).getExpressionCode() > BooleanExp.OR) {
				// if left operand is an expression of equal or lower precedence, parens are needed 
				temp1.append("(").append(values.pop()).append(")");
			} // Pops values to get necessary elements to use in String construction
			else{ temp1.append(values.pop());}
		}
		else {
			// otherwise, i.e., of higher precedence, no parens needed
			temp1.append(values.pop()); 
		}
		// The same as before but with the left and 2nd last used value
		if(e.getLeft() instanceof CompositeExp)
		{
			if (((CompositeExp) e.getLeft()).getExpressionCode() >= BooleanExp.OR) {
				temp2.append("(").append(values.pop()).append(")");
			}
			else{ temp2.append(values.pop());}
		}
		else {
			temp2.append(values.pop()); 
		}	// Appends the Strings in reverse (correct) order because of how they were removed from
		temp2.append(" OR ").append(temp1); // The Stack, and places ' OR ' in the middle, pushes final
		values.push(temp2.toString()); // value to values
	}
	
	/**
	 * @param: NotExp e
	 * @effects: Pops most recent values off the stack and determines the necessary parenthesis
	 * 			 or otherwise to build the String expression of NotExp e, adds that new value to the 
	 * 			 stack
	 * @modifies: private Stack<String> values is modified 
	*/
	public void visit(NotExp e)
	{ 
		temp1 = new StringBuffer();	// Uses a string buffer to perform intermediary String construction
		temp1.append("NOT "); // Does the necessary steps to determine if parenthesis need to be added
		if(e.getRight() instanceof CompositeExp) // And then constructs the String
		{   // Pops values off to get the values that need to be 'Negated'
			if ( ((CompositeExp) e.getRight()).getExpressionCode() >= BooleanExp.NOT) {
				temp1.append("(").append(values.pop()).append(")");
			}
			else {temp1.append(values.pop());}
		}
		else {
			temp1.append(values.pop()); 
		}	// Pushes the final string back into values
		values.push(temp1.toString());	
	}
	
	/**
	 * @returns: Returns the head of the stack without removing (should be the only value or null)
	*/
	public String getResult() // Peeks to get the top and returns that String
	{
		return values.peek();
	}
}
