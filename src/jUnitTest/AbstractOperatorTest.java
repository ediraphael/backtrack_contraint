package jUnitTest;

import static org.junit.Assert.fail;
import modele.Domain;
import modele.DomainBoundaryException;
import modele.Operator;
import modele.Variable;

public abstract class AbstractOperatorTest
{
	private Operator operator;
	private Variable leftVariable;
	private Variable rightVariable;

	public void reduceDomains()
	{
		try
		{
			operator.reduceDomains(leftVariable, rightVariable);
		} catch (DomainBoundaryException e)
		{
			fail("Error during reducing domain");
		}
	}

	public void initLeftVariable(String name, int bottomBoundary, int upperBoundary)
	{
		this.leftVariable = new Variable(name, initDomain(bottomBoundary, upperBoundary));
	}

	public void initRightVariable(String name, int bottomBoundary, int upperBoundary)
	{
		this.rightVariable = new Variable(name, initDomain(bottomBoundary, upperBoundary));
	}

	public Domain initDomain(int bottomBoundary, int upperBoundary)
	{
		try
		{
			return new Domain(bottomBoundary, upperBoundary);
		} catch (DomainBoundaryException e)
		{
			fail("Error creation domain");
		}
		return null;
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
