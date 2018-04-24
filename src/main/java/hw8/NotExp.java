package hw8;

public class NotExp extends CompositeExp{

	private BooleanExp right;
	private String CodeStr;
	// Rep invariant:
	// left == null && right != null && CodeStr = CompositeExp.not	
	// Abstraction Function: private BooleanExp right = BooleanExp right in the constructor
	// BooleanExp left is used in the super constructor of CompositeExp to set left = null.
	
	/**
	 * @param: BooleanExp left
	 * @param: BooleanExp right
	 * @effects: Changes value of private BooleanExp right to BooleanExp right, CodeStr=CompositeExp.not
	 * @effects: super of CompositeExp constructor is called, left = null, exprCode = BooleanExp.NOT
	 * 			 and right = right.
	 * @modifies: private BooleanExp right and String CodeStr are modified, 
	 * 			  super of CompositeExp is called and variables left, right, exprCode are modified
	 */
	public NotExp(BooleanExp left, BooleanExp right) // the Constructor for NotExp
	{
		super(BooleanExp.NOT, left, right);
		this.right = right;
		CodeStr = CompositeExp.not;
	}
	
	/**
	 * @param context
	 * @return boolean value of the enclosed expression, evaluated in Context context.
	 * 		   This is done by evaluating the left Boolean expression and anding it with the right one.
	 */
	public boolean evaluate(Context context) //Returns the negation of the right expression evaluation
	{
		return !this.getRight().evaluate(context);
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
		right.accept(v);
		v.visit(this);
	}
}
