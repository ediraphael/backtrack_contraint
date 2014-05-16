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

	public void reduceDomainVariables()
	{
		operator.reduceDomains(leftVariable, rightVariable);
	}

	public Variable getLeftVariable()
	{
		return leftVariable;
	}

	public void setLeftVariable(Variable leftVariable)
	{
		this.leftVariable = leftVariable;
	}

	public Variable getRightVariable()
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

	public void setRightVariable(Variable rightVariable)
	{
		this.rightVariable = rightVariable;
	}

	@Override
	public String toString()
	{
		return leftVariable.getName() + " " + operator + " " + rightVariable.getName();
	}
}
