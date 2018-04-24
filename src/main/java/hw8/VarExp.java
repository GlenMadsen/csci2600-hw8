package hw8;

public class VarExp extends BooleanExp{
	private String varString;
	
	// Rep invariant:
	// varString != null	
	// Abstraction Function: private String varString = String varString in the constructor
	/**
	 * @param: Boolean value
	 * @effects: Changes value of String varString to varString
	 * @modifies: private String varString is modified
	 */
	public VarExp(String varString)  // the Constructor
	{
		this.varString = varString;
	}
	/**
	 * @return: value of varString
	 */
	public String getVarString() { // A getter for varString
		return varString;
	}
	/**
	 * @return: String corresponding to Preorder of this VarExp, in other words VarString variable
	 */
	@Override
	public String toString() { // Same as the getter since it is already a string, but uses the getter
		return getVarString();
	}
	/**
	 * @param context
	 * @return boolean value of the enclosed expression, evaluated in Context context.
	 */
	@Override
	public boolean evaluate(Context context) // Uses context lookup to change string to a boolean
	{
		return context.lookup(this.getVarString());
	}
	/**
	 * @return: String corresponding to Preorder of this VarExp, in other words VarString variable
	 */
	@Override
	public String printPreorder() { // Defined as the same thing as toString, but needs a different name
		return this.getVarString();
	}
	/**
	 * @return: String corresponding to Inorder of this VarExp, in other words VarString variable
	 */
	@Override
	public String printInorder() { // Defined as the same thing as toString, but needs a different name
		return this.getVarString();
	}
	/**
	 * @param: v of the Visitor class type
	 * @effects: Calls visit on Visitor v
	 * @modifies: One of Visitor v's sub-type's data structures after calling visit
	 */
	@Override
	public void accept(Visitor v) { // An accept method for the visitor class
		v.visit(this);
	}
}
