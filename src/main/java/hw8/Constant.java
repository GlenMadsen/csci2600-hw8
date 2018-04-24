package hw8;

public class Constant extends BooleanExp{
	private Boolean value;
	
	// Rep invariant:
	// value != null	
	// Abstraction Function: private Boolean value = Boolean value in the constructor	
	/**
	 * @param: Boolean value
	 * @effects: Changes value of private Boolean value to value
	 * @modifies: private Boolean value is modified
	 */
	public Constant(Boolean value) // The Constructor
	{
		this.value = value;
	}
	/**
	 * @return value of Boolean value
	 */
	public Boolean getValue() {  // A getter for the value
		return value.booleanValue();
	}
	/**
	 * @return: String corresponding to Preorder of this Constant
	 */
	@Override
	public String toString() {  // Changes value to a string and returns
		return String.valueOf(getValue());
	}
	/**
	 * @param context
	 * @return boolean value of of the constant, in other words the Boolean value variable
	 */
	@Override
	public boolean evaluate(Context context)   // returns the value
	{
		return this.getValue();
	}
	/**
	 * @return: String corresponding to Preorder of this Constant, in other words the value variable
	 */
	@Override
	public String printPreorder() {  // Same as toString
		return toString();
	}
	/**
	 * @return: String corresponding to Inorder of this Constant, in other words the value variable
	 */
	@Override
	public String printInorder() {  // Defined as the same thing as toString, but needs a different name
		return String.valueOf(this.getValue());
	}
	/**
	 * @param: v of the Visitor class type
	 * @effects: Calls visit on Visitor v
	 * @modifies: One of Visitor v's sub-type's data structures after calling visit
	 */
	@Override           // Accept method for the visitor class
	public void accept(Visitor v) {
		v.visit(this);
	}
}
