package hw8;

/**
 * 
 * BooleanExp is an immutable class. It represents boolean constants, boolean variables,
 * Or boolean expressions, And boolean expressions, and Not boolean expressions.
 *
 */
// Object must fit Rep Invariant of one of BooleanExp's sub-classes.

public abstract class BooleanExp {
	// Some defined constants for hierarchies, not necessarily all used with current functionality
	final static int CONST = 0;
	final static int VAR = 1;
	final static int OR = 4;
	final static int AND = 3;
	final static int NOT = 2;
	
	// Abstract functions shared by all methods.
	public abstract boolean evaluate(Context c);
	public abstract String printPreorder();
	public abstract String printInorder();
	public abstract void accept(Visitor v);
	public abstract String toString();
	
}