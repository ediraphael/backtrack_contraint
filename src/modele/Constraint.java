package modele;

public class Constraint
{
	private int leftVariable;
	private int rightVariable;
	private Operator operator;

	public int getFirstVar()
	{
		return leftVariable;
	}

	public void setFirstVar(int firstVar)
	{
		this.leftVariable = firstVar;
	}

	public int getSecondVar()
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

	public void setSecondVar(int secondVar)
	{
		this.rightVariable = secondVar;
	}
}
