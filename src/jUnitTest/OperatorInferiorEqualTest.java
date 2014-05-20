package jUnitTest;

import static org.junit.Assert.*;
import model.Domain;
import model.Operator;

import org.junit.Before;
import org.junit.Test;

import exception.VariableValueException;


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
		assertTrue(getLeftVariable().getDomains().contains(leftDomainTest));
		assertTrue(getRightVariable().getDomains().contains(rightDomainTest));
		assertTrue(getLeftVariable().getDomains().contains(initDomain(0, 5)));
		assertTrue(getRightVariable().getDomains().contains(initDomain(25, 30)));
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
		assertTrue(getLeftVariable().getDomains().contains(leftDomainTest));
		assertTrue(getLeftVariable().getDomains().contains(initDomain(15, 20)));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}

	@Test
	public void leftInitiatedInRightTest()
	{
		// Case
		// -----1---------------------
		// --[---------]--------------
		initLeftVariable("left", 0, 20);
		initRightVariable("right", 0, 15);
		leftDomainTest = initDomain(0, 20);
		rightDomainTest = initDomain(5, 15);
		try
		{
			getLeftVariable().setValue(5);
		} catch (VariableValueException e)
		{
			fail("Error setting value : " + e.getMessage());
		}

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}

	@Test
	public void leftInitiatedInferiorRightTest()
	{
		// Case
		// ---1-----------------------
		// ------[-----]--------------
		initLeftVariable("left", 0, 15);
		initRightVariable("right", 5, 20);
		leftDomainTest = initDomain(0, 15);
		rightDomainTest = initDomain(5, 20);
		try
		{
			getLeftVariable().setValue(0);
		} catch (VariableValueException e)
		{
			fail("Error setting value : " + e.getMessage());
		}

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}

	@Test
	public void leftInitiatedSuperiorRightTest()
	{
		// Case
		// --------------1------------
		// --[---------]--------------
		initLeftVariable("left", 0, 15);
		initRightVariable("right", 5, 10);
		leftDomainTest = initDomain(0, 15);
		try
		{
			getLeftVariable().setValue(15);
		} catch (VariableValueException e)
		{
			fail("Error setting value : " + e.getMessage());
		}

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertTrue(getRightVariable().getDomains().size() == 0);
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
	}

	@Test
	public void rightInitiatedInLeftTest()
	{
		// Case
		// --[---------]--------------
		// -----1---------------------
		initLeftVariable("left", 0, 20);
		initRightVariable("right", 0, 15);
		leftDomainTest = initDomain(0, 5);
		rightDomainTest = initDomain(0, 15);
		try
		{
			getRightVariable().setValue(5);
		} catch (VariableValueException e)
		{
			fail("Error setting value : " + e.getMessage());
		}

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}

	@Test
	public void rightInitiatedInferiorLeftTest()
	{
		// Case
		// ------[-----]--------------
		// ---1-----------------------
		initLeftVariable("left", 5, 15);
		initRightVariable("right", 0, 20);
		rightDomainTest = initDomain(0, 20);
		try
		{
			getRightVariable().setValue(0);
		} catch (VariableValueException e)
		{
			fail("Error setting value : " + e.getMessage());
		}

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 0);
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}

	@Test
	public void rightInitiatedSuperiorLeftTest()
	{
		// Case
		// --[---------]--------------
		// --------------1------------
		initLeftVariable("left", 0, 15);
		initRightVariable("right", 5, 20);
		leftDomainTest = initDomain(0, 15);
		rightDomainTest = initDomain(5, 20);
		try
		{
			getRightVariable().setValue(16);
		} catch (VariableValueException e)
		{
			fail("Error setting value : " + e.getMessage());
		}

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}

	@Test
	public void rightInitiatedBetweenTwoLeftTest()
	{
		// Case
		// --[---------]---[-----]----
		// --------------1------------
		initLeftVariable("left", 0, 15);
		initRightVariable("right", 5, 20);
		getLeftVariable().getDomains().add(initDomain(20, 25));
		leftDomainTest = initDomain(0, 15);
		rightDomainTest = initDomain(5, 20);
		try
		{
			getRightVariable().setValue(18);
		} catch (VariableValueException e)
		{
			fail("Error setting value : " + e.getMessage());
		}

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertTrue(getLeftVariable().getDomains().contains(leftDomainTest));
		assertTrue(getRightVariable().getDomains().contains(rightDomainTest));
	}
	
	@Test
	public void rightInitiatedInTwoLeftTest()
	{
		// Case
		// --[---------]---[-----]----
		// --------1------------------
		initLeftVariable("left", 0, 15);
		initRightVariable("right", 5, 10);
		getLeftVariable().getDomains().add(initDomain(20, 25));
		leftDomainTest = initDomain(0, 8);
		rightDomainTest = initDomain(5, 10);
		try
		{
			getRightVariable().setValue(8);
		} catch (VariableValueException e)
		{
			fail("Error setting value : " + e.getMessage());
		}

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertTrue(getLeftVariable().getDomains().contains(leftDomainTest));
		assertTrue(getRightVariable().getDomains().contains(rightDomainTest));
	}
	
	@Test
	public void rightInitiatedInTwoLeftTestTwo()
	{
		// Case
		// --[---------]---[-----]----
		// -------------------1-------
		initLeftVariable("left", 5, 10);
		initRightVariable("right", 10, 20);
		getLeftVariable().getDomains().add(initDomain(15, 25));
		leftDomainTest = initDomain(5, 10);
		rightDomainTest = initDomain(10, 20);
		try
		{
			getRightVariable().setValue(20);
		} catch (VariableValueException e)
		{
			fail("Error setting value : " + e.getMessage());
		}

		reduceDomains();

		assertTrue(getLeftVariable().getDomains().size() == 2);
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertTrue(getLeftVariable().getDomains().contains(leftDomainTest));
		assertTrue(getLeftVariable().getDomains().contains(initDomain(15, 20)));
		assertTrue(getRightVariable().getDomains().contains(rightDomainTest));
	}
	
	@Test
	public void leftInitiatedBetweenTwoRightTest()
	{
		// Case
		// --------------1------------
		// --[---------]---[-----]----
		initLeftVariable("left", 0, 15);
		initRightVariable("right", 5, 10);
		getRightVariable().getDomains().add(initDomain(20, 25));
		leftDomainTest = initDomain(0, 15);
		rightDomainTest = initDomain(20, 25);
		try
		{
			getLeftVariable().setValue(15);
		} catch (VariableValueException e)
		{
			fail("Error setting value : " + e.getMessage());
		}

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertTrue(getLeftVariable().getDomains().contains(leftDomainTest));
		assertTrue(getRightVariable().getDomains().contains(rightDomainTest));
	}
	
	@Test
	public void leftInitiatedInTwoRightTest()
	{
		// Case
		// --------1------------------
		// --[---------]---[-----]----
		initLeftVariable("left", 0, 15);
		initRightVariable("right", 5, 10);
		getRightVariable().getDomains().add(initDomain(20, 25));
		leftDomainTest = initDomain(0, 15);
		rightDomainTest = initDomain(20, 25);
		try
		{
			getLeftVariable().setValue(8);
		} catch (VariableValueException e)
		{
			fail("Error setting value : " + e.getMessage());
		}

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertTrue(getRightVariable().getDomains().size() == 2);
		assertTrue(getLeftVariable().getDomains().contains(leftDomainTest));
		assertTrue(getRightVariable().getDomains().contains(rightDomainTest));
		assertTrue(getRightVariable().getDomains().contains(initDomain(8, 10)));
	}
	
	@Test
	public void leftInitiatedInTwoRightTestTwo()
	{
		// Case
		// -------------------1-------
		// --[---------]---[-----]----
		initLeftVariable("left", 5, 30);
		initRightVariable("right", 5, 10);
		getRightVariable().getDomains().add(initDomain(20, 25));
		leftDomainTest = initDomain(5, 30);
		rightDomainTest = initDomain(22, 25);
		try
		{
			getLeftVariable().setValue(22);
		} catch (VariableValueException e)
		{
			fail("Error setting value : " + e.getMessage());
		}

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertTrue(getLeftVariable().getDomains().contains(leftDomainTest));
		assertTrue(getRightVariable().getDomains().contains(rightDomainTest));
	}
}
