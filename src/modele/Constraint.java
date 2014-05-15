package modele;

public class Constraint
{
	private Variable leftVariable;
	private Variable rightVariable;
	private Operator operator;

	public Constraint(Variable leftVariable, Variable rightVariable, Operator operator)
	{
		this.leftVariable = leftVariable;
		this.rightVariable = rightVariable;
		this.operator = operator;
	}

	public boolean checkInstance()
	{
		return operator.execute(leftVariable, rightVariable);
	}

	public boolean checkIfPossible()
	{
		return operator.checkIfPossible(leftVariable, rightVariable);
	}
	
	public Domain reduceDomainVariables()
	{
		return null;
	}

	public Variable getFirstVar()
	{
		return leftVariable;
	}

	public void setFirstVar(Variable firstVar)
	{
		this.leftVariable = firstVar;
	}

	public Variable getSecondVar()
	{
		return rightVariable;
	}

	public Operator getOperator()
	{
		return operator;
	}

	public void setOperator(Operator operator)
	{
		this.operator = operator;
	}

	public void setSecondVar(Variable secondVar)
	{
		this.rightVariable = secondVar;
	}
}
