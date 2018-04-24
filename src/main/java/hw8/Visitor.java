package hw8;

public abstract class Visitor {
	// Abstract Class and Abstract Methods
	// Object should match one of the the RepInvariants of the subclasses
	public abstract void visit(Constant e);
	public abstract void visit(VarExp e);
	public abstract void visit(NotExp e);
	public abstract void visit(AndExp e);
	public abstract void visit(OrExp e);
}
