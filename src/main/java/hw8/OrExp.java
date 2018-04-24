package hw8;

public class OrExp extends CompositeExp{

	private BooleanExp left;
	private BooleanExp right;
	private String CodeStr;
	// Rep invariant:
	// left != null && right != null && CodeStr = CompositeExp.or	
	// Abstraction Function: private BooleanExp right = BooleanExp right in the constructor,
	// private BooleanExp left = BooleanExp left in the constructor, BooleanExp left & right are used 
	// in the super constructor of CompositeExp to set variables left and right to non-null values.
	/**
	 * @param: BooleanExp left
	 * @param: BooleanExp right
	 * @effects: Changes value of private BooleanExp right to BooleanExp right, CodeStr=CompositeExp.or
	 * @effects: super of CompositeExp constructor is called, left = left, exprCode = BooleanExp.OR
	 * 			 and right = right.
	 * @modifies: private BooleanExp right, BooleanExp left, and String CodeStr are modified, 
	 * 			  super of CompositeExp is called and variables left, right, exprCode are modified
	 */
	
	public OrExp(BooleanExp left, BooleanExp right) // The Constructor for OrExp
	{
		super(BooleanExp.OR, left, right);
		this.left = left;
		this.right = right;
		CodeStr = CompositeExp.or;
	}
	
	/**
	 * @param context
	 * @return boolean value of the enclosed expression, evaluated in Context context.
	 * 		   This is done by evaluating the left Boolean expression and anding it with the right one.
	 */
	public boolean evaluate(Context context) // Evaluates left and right then or's them together and
											 // returns the result of the evaluation of that expression
	{
		return this.getLeft().evaluate(context) || this.getRight().evaluate(context);
	}
	
	/**
	 *@returns: String corresponding to the value of CodeStr
	 */
	@Override
	public String getCodeStr() { // Getter for CodeStr variable
		return CodeStr;
	}
	
	/**
	 * @param: v of the Visitor class type
	 * @effects: Calls visit on Visitor v
	 * @modifies: One of Visitor v's sub-type's data structures after calling visit
	 */
	@Override
	public void accept(Visitor v) { // Accepts a visitor then calls left and right accepts, also visits
		left.accept(v);
		right.accept(v);
		v.visit(this);
	}
}
