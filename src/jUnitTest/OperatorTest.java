package jUnitTest;

import static org.junit.Assert.*;

import modele.Domain;
import modele.DomainBoundaryException;
import modele.Operator;
import modele.Variable;

import org.junit.Test;

public class OperatorTest
{
	private Operator operator;
	private Variable leftVariable;
	private Variable rightVariable;

	@Test
	public void inferiorTest()
	{
		setOperator(Operator.INFERIOR);
		Domain leftDomain;
		Domain rightDomain;
		// Case
		// ---[---]-------------------
		// ---------[----]------------
		initLeftVariable("left", 0, 10);
		initRightVariable("right", 15, 20);
		leftDomain = initDomain(0, 10);
		rightDomain = initDomain(15, 20);

		reduceDomains();
		assertEquals(leftDomain, leftVariable.getDomains().get(0));
		assertEquals(rightDomain, rightVariable.getDomains().get(0));

		// Case
		// ---[---]-------------------
		// -----[----]----------------
		initLeftVariable("left", 0, 10);
		initRightVariable("right", 5, 20);
		leftDomain = initDomain(0, 10);
		rightDomain = initDomain(5, 20);
		reduceDomains();
		assertEquals(leftDomain, leftVariable.getDomains().get(0));
		assertEquals(rightDomain, rightVariable.getDomains().get(0));

		// Case
		// --------[---]--------------
		// -----[---------]-----------
		initLeftVariable("left", 5, 15);
		initRightVariable("right", 0, 20);
		leftDomain = initDomain(5, 15);
		rightDomain = initDomain(6, 20);
		reduceDomains();
		assertEquals(leftDomain, leftVariable.getDomains().get(0));
		assertEquals(rightDomain, rightVariable.getDomains().get(0));

		// Case
		// -----[---------]-----------
		// --------[---]--------------
		initLeftVariable("left", 0, 20);
		initRightVariable("right", 5, 15);
		leftDomain = initDomain(0, 14);
		rightDomain = initDomain(5, 15);
		reduceDomains();
		assertEquals(leftDomain, leftVariable.getDomains().get(0));
		assertEquals(rightDomain, rightVariable.getDomains().get(0));

		// Case
		// -----[---------]-----------
		// --[---------]--------------
		initLeftVariable("left", 0, 20);
		initRightVariable("right", -5, 15);
		leftDomain = initDomain(0, 14);
		rightDomain = initDomain(1, 15);
		reduceDomains();
		assertEquals(leftDomain, leftVariable.getDomains().get(0));
		assertEquals(rightDomain, rightVariable.getDomains().get(0));

		// Case
		// -----[---------]-----------
		// -----[---------]-----------
		initLeftVariable("left", 0, 10);
		initRightVariable("right", 0, 10);
		leftDomain = initDomain(0, 9);
		rightDomain = initDomain(1, 10);
		reduceDomains();
		assertEquals(leftDomain, leftVariable.getDomains().get(0));
		assertEquals(rightDomain, rightVariable.getDomains().get(0));

		// Case
		// ----------[----]-----------
		// --[---]--------------------
		initLeftVariable("left", 10, 15);
		initRightVariable("right", 0, 5);

		reduceDomains();
		assertTrue(leftVariable.getDomains().isEmpty());
		assertTrue(rightVariable.getDomains().isEmpty());
	}

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
