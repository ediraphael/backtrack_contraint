package jUnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import modele.Domain;
import modele.Operator;

import org.junit.Before;
import org.junit.Test;

public class OperatorInferiorEqualTest extends AbstractOperatorTest
{
	Domain leftDomainTest;
	Domain rightDomainTest;

	@Before
	public void initOperator()
	{
		setOperator(Operator.INFERIOREQUAL);
	}
	
	@Test
	public void leftInferiorRightTest()
	{
		// Case
		// ---[---]-------------------
		// ---------[----]------------
		initLeftVariable("left", 0, 10);
		initRightVariable("right", 15, 20);
		leftDomainTest = initDomain(0, 10);
		rightDomainTest = initDomain(15, 20);

		reduceDomains();
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}

	@Test
	public void leftInferiorEqualTest()
	{
		// Case
		// ---[---]-------------------
		// -----[----]----------------
		initLeftVariable("left", 0, 10);
		initRightVariable("right", 5, 20);
		leftDomainTest = initDomain(0, 10);
		rightDomainTest = initDomain(5, 20);
		reduceDomains();
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}

	@Test
	public void leftIncludeInRightTest()
	{
		// Case
		// --------[---]--------------
		// -----[---------]-----------
		initLeftVariable("left", 5, 15);
		initRightVariable("right", 0, 20);
		leftDomainTest = initDomain(5, 15);
		rightDomainTest = initDomain(5, 20);
		reduceDomains();
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}

	@Test
	public void rightIncludeInLeftTest()
	{
		// Case
		// -----[---------]-----------
		// --------[---]--------------
		initLeftVariable("left", 0, 20);
		initRightVariable("right", 5, 15);
		leftDomainTest = initDomain(0, 15);
		rightDomainTest = initDomain(5, 15);
		reduceDomains();
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}

	@Test
	public void leftSuperiorEqualRightTest()
	{
		// Case
		// -----[---------]-----------
		// --[---------]--------------
		initLeftVariable("left", 0, 20);
		initRightVariable("right", -5, 15);
		leftDomainTest = initDomain(0, 15);
		rightDomainTest = initDomain(0, 15);
		reduceDomains();
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}

	@Test
	public void leftEqualRightTest()
	{
		// Case
		// -----[---------]-----------
		// -----[---------]-----------
		initLeftVariable("left", 0, 10);
		initRightVariable("right", 0, 10);
		leftDomainTest = initDomain(0, 10);
		rightDomainTest = initDomain(0, 10);
		reduceDomains();
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}

	@Test
	public void leftSuperiorRight()
	{
		// Case
		// ----------[----]-----------
		// --[---]--------------------
		initLeftVariable("left", 10, 15);
		initRightVariable("right", 0, 5);

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().isEmpty());
		assertTrue(getRightVariable().getDomains().isEmpty());
	}

	@Test
	public void doubleLeftSurroundingOneRight()
	{
		// Case
		// -[--]------[----]-----------
		// -----[---]------------------
		initLeftVariable("left", 15, 20);
		initRightVariable("right", 8, 13);
		getLeftVariable().getDomains().add(initDomain(0, 5));
		leftDomainTest = initDomain(0, 5);
		rightDomainTest = initDomain(8, 13);
		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}

	@Test
	public void oneLeftSurroundByTwoRight()
	{
		// Case
		// -----[---]------------------
		// -[--]------[----]-----------
		initLeftVariable("left", 8, 13);
		initRightVariable("right", 15, 20);
		getRightVariable().getDomains().add(initDomain(0, 5));
		leftDomainTest = initDomain(8, 13);
		rightDomainTest = initDomain(15, 20);
		reduceDomains();
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}

	@Test
	public void twoLeftTwoRightInCrenel()
	{
		// Case
		// -[--]------[----]-----------
		// -----[---]--------[---]-----
		initLeftVariable("left", 15, 20);
		initRightVariable("right", 8, 13);
		getLeftVariable().getDomains().add(initDomain(0, 5));
		getRightVariable().getDomains().add(initDomain(25, 30));
		leftDomainTest = initDomain(15, 20);
		rightDomainTest = initDomain(8, 13);
		reduceDomains();
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
		assertEquals(initDomain(0, 5), getLeftVariable().getDomains().get(1));
		assertEquals(initDomain(25, 30), getRightVariable().getDomains().get(1));
	}

	@Test
	public void twoRightTwoLeftInCrenel()
	{
		// Case
		// -----[---]--------[---]-----
		// -[--]------[----]-----------
		initLeftVariable("left", 8, 13);
		initRightVariable("right", 15, 20);
		getLeftVariable().getDomains().add(initDomain(25, 30));
		getRightVariable().getDomains().add(initDomain(0, 5));
		leftDomainTest = initDomain(8, 13);
		rightDomainTest = initDomain(15, 20);
		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}

	@Test
	public void twoLeftSurroundByTwoRight()
	{
		// Case
		// -----[---]--[---]-----------
		// -[--]--------------[----]---
		initLeftVariable("left", 5, 10);
		initRightVariable("right", 25, 30);
		getLeftVariable().getDomains().add(initDomain(15, 20));
		getRightVariable().getDomains().add(initDomain(0, 4));
		leftDomainTest = initDomain(5, 10);
		rightDomainTest = initDomain(25, 30);
		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 2);
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(initDomain(15, 20), getLeftVariable().getDomains().get(1));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}
}
