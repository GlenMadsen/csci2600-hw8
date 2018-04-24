package hw8;

public abstract class CompositeExp extends BooleanExp{
	// Variables for necessary and commonly used Strings during printing
	final static String and = "AND";
	final static String or = "OR";
	final static String not = "NOT";
	
	private BooleanExp left;
	private BooleanExp right;
	private int exprCode;
	// Rep invariant:
	// Must match one CompositeExp's sub-classes RepInvariants, exprCode is BooleanExp.AND, OR, or NOT
	// Abstraction Function: private BooleanExp right = BooleanExp right in the constructor,
	// private BooleanExp left = BooleanExp left in the constructor, private int exprCode is exprCode
	/**
	 * @param: int exprCode
	 * @param: BooleanExp left
	 * @param: BooleanExp right
	 * @effects: Changes value of private BooleanExp right to BooleanExp right, 
	 * 			 private int exprCode=exprCode
	 * @modifies: private BooleanExp right, BooleanExp left, and int exprCode are modified, 
	 */
	
	public CompositeExp(int exprCode, BooleanExp left, BooleanExp right) // The Constructor
	{
		this.left = left;
		this.right = right;
		this.exprCode = exprCode;
	}
	
	// Abstract Function for getCodeStr.
	public abstract String getCodeStr();
	public abstract boolean evaluate(Context c);
	
	// Notes about the CompositeExp Class:
		// It would have been very easy to have CompositeExp do everything for And, Or, and Not except
		// have those classes have a constructor and something to return essentially their respective 
		// operators. To avoid doing such a thing but maintaining the design templates desired of us,
		// I decided to move some reused methods to CompositeExp to reduce code duplication, but leave
		// more individual methods such as the the getting the CodeStr, the accept methods, and 
		// obviously the evaluate to the sub-classes. I could have left some more getters in there too,
		// but the question on Piazza said to find a balance, also wanted to try to avoid code
		// duplication but am uncertain how hard of a line that is, hopefully leaving getCodeStr won't
		// ruin that for me. Anyway it follows the design templates and finds a balance.
	// End Notes.
	
//=========================== Non Abstract ====================================================
	/**
	 *@returns: int corresponding to the value of exprCode
	 */
	public int getExpressionCode() // Getter for exprCode
	{
		return exprCode;
	}
	
	/**
	 *@returns: BooleanExp corresponding to the value of left
	 */
	public BooleanExp getLeft() { // Getter for the left variable
		return left;
	}
	
	/**
	 *@returns: Boolean false if left == null, Boolean true otherwise
	 */
	public Boolean hasLeft() { // Checks if left is null or not
		if(this.left == null)
		{
			return false;
		}
		return true;
	}
	
	/**
	 *@returns: BooleanExp corresponding to the value of right
	 */
	public BooleanExp getRight() { // Getter for the right variable
		return right;
	}
	/**
	 * @return: Converts the CompositeExp to a string corresponding to the Preorder of the BooleanExp
	 */
	public String toString() // Converts the CompositeExp to a String, and everything to the left/right
	{
		StringBuffer result = new StringBuffer();
		if(hasLeft())
		{							          
			result.append(getCodeStr()).append(" "); 
			result.append(left.toString());
			result.append(" "); 
		}
		else { result.append(getCodeStr()).append(" "); }
		result.append(right.toString());
		return result.toString();
	}
	
	/**
	 * @return: String corresponding to Preorder of this BooleanExp, same as toString
	 */
	public String printPreorder() { // The same as toString
		return this.toString();
	}
	
	/**
	 * @return: String corresponding to Inorder of this CompositeExp
	 */
	public String printInorder() {
		StringBuffer result = new StringBuffer(); // Creates a String Buffers
		if(hasLeft()) // To save code and avoid duplication, checks if there is a left variable
		{   // If so it checks left is an instance of CompositeExp, if it is then it knows the compare
			if(left instanceof CompositeExp) // hierarchies, and if needed, add parenthesis
			{
				if (((CompositeExp) left).exprCode > exprCode) {
					// if left operand is an expression of equal or lower precedence, parens are needed 
					result.append("("); result.append(left.printInorder()); result.append(")");
				}
				else{ result.append(left.printInorder());}
			}
			// otherwise, i.e., of higher precedence, no parens needed
			else{ result.append(left.printInorder());}
			result.append(" ").append(getCodeStr()).append(" ");// Appends CodeStr in middle if left e.
		} // If it does not have a left then it needs to start with its CodeStr, so add it
		else { result.append(getCodeStr()).append(" ");}
		if(right instanceof CompositeExp) // Does the same checks about Composite as before with right
		{
			if (((CompositeExp) right).exprCode >= exprCode) {
				result.append("("); result.append(right.printInorder()); result.append(")");
			}
			else{ result.append(right.printInorder());}
		}
		else {
			result.append(right.printInorder()); 
		} // Finally converts the String Buffer to a string an returns
		return result.toString();
	}
}
