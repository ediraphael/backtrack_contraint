package jUnitTest;

import static org.junit.Assert.*;
import modele.Domain;
import modele.Operator;

import org.junit.Before;
import org.junit.Test;

public class OperatorEqualTest extends AbstractOperatorTest
{
	Domain leftDomainTest;
	Domain rightDomainTest;

	@Before
	public void initOperator()
	{
		setOperator(Operator.INFERIOREQUAL);
	}

	@Test
	public void leftEqualRightTest()
	{
		// Case
		// ---------[----]------------
		// ---------[----]------------
		initLeftVariable("left", 0, 5);
		initRightVariable("right", 0, 5);
		leftDomainTest = initDomain(0, 5);
		rightDomainTest = initDomain(0, 5);

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}
}
