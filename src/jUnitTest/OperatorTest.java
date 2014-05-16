package jUnitTest;

import org.junit.Test;

import modele.Operator;
import modele.Variable;

public class OperatorTest
{
	private Operator operator;
	private Variable leftVariable;
	private Variable rightVariable;

	@Test
	public void inferiorTest()
	{
		setOperator(Operator.INFERIOR);

	}

	public Operator getOperator()
	{
		return operator;
	}

	public void setOperator(Operator operator)
	{
		this.operator = operator;
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

	public void setRightVariable(Variable rightVariable)
	{
		this.rightVariable = rightVariable;
	}
}
