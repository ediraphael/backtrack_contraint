package jUnitTest;

import static org.junit.Assert.*;
import modele.Domain;
import modele.Operator;

import org.junit.Before;
import org.junit.Test;

import Exception.VariableValueException;

public class OperatorEqualTest extends AbstractOperatorTest
{
	Domain leftDomainTest;
	Domain rightDomainTest;

	@Before
	public void initOperator()
	{
		setOperator(Operator.EQUAL);
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

	@Test
	public void leftInferiorRightTest()
	{
		// Case
		// ----[----]------------------
		// ------------[----]----------
		initLeftVariable("left", 0, 5);
		initRightVariable("right", 10, 15);

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 0);
		assertTrue(getRightVariable().getDomains().size() == 0);
	}

	@Test
	public void rightInferiorLeftTest()
	{
		// Case
		// ------------[----]---------
		// ----[----]-----------------
		initLeftVariable("left", 10, 15);
		initRightVariable("right", 0, 5);

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 0);
		assertTrue(getRightVariable().getDomains().size() == 0);
	}

	@Test
	public void leftIncludeInRightTest()
	{
		// Case
		// -------[----]-------------
		// ----[---------]-----------
		initLeftVariable("left", 10, 15);
		initRightVariable("right", 0, 20);
		leftDomainTest = initDomain(10, 15);
		rightDomainTest = initDomain(10, 15);

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}

	@Test
	public void rightIncludeInLeftTest()
	{
		// Case
		// ----[---------]-----------
		// -------[----]-------------
		initLeftVariable("left", 0, 20);
		initRightVariable("right", 10, 15);
		leftDomainTest = initDomain(10, 15);
		rightDomainTest = initDomain(10, 15);

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}

	@Test
	public void leftInferiorEqualRightTest()
	{
		// Case
		// ----[-----]---------------
		// -------[----]-------------
		initLeftVariable("left", 0, 10);
		initRightVariable("right", 5, 15);
		leftDomainTest = initDomain(5, 10);
		rightDomainTest = initDomain(5, 10);

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}

	@Test
	public void rightInferiorEqualLeftTest()
	{
		// Case
		// -------[----]-------------
		// ----[-----]---------------
		initLeftVariable("left", 5, 15);
		initRightVariable("right", 0, 10);
		leftDomainTest = initDomain(5, 10);
		rightDomainTest = initDomain(5, 10);

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}

	@Test
	public void twoLeftSurroundingOneRightTest()
	{
		// Case
		// --[---]--------[----]-----
		// -------[-----]------------
		initLeftVariable("left", 15, 20);
		initRightVariable("right", 7, 13);
		getLeftVariable().getDomains().add(initDomain(0, 5));

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 0);
		assertTrue(getRightVariable().getDomains().size() == 0);
	}

	@Test
	public void twoRightSurroundingOneLeftTest()
	{
		// Case
		// -------[-----]------------
		// --[---]--------[----]-----
		initLeftVariable("left", 7, 13);
		initRightVariable("right", 15, 20);
		getRightVariable().getDomains().add(initDomain(0, 5));

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 0);
		assertTrue(getRightVariable().getDomains().size() == 0);
	}

	@Test
	public void twoLeftSurroundingOneRightWithFirstLeftEqualTest()
	{
		// Case
		// ---[---]-------[----]-----
		// ------[-----]-------------
		initLeftVariable("left", 15, 20);
		initRightVariable("right", 5, 10);
		getLeftVariable().getDomains().add(initDomain(0, 7));
		leftDomainTest = initDomain(5, 7);
		rightDomainTest = initDomain(5, 7);

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}

	@Test
	public void twoLeftSurroundingOneRightWithSecondLeftEqualTest()
	{
		// Case
		// ---[---]-------[----]-----
		// ----------[-----]---------
		initLeftVariable("left", 15, 20);
		initRightVariable("right", 10, 17);
		getLeftVariable().getDomains().add(initDomain(0, 5));
		leftDomainTest = initDomain(15, 17);
		rightDomainTest = initDomain(15, 17);

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}

	@Test
	public void twoLeftSurroundingEqualOneRightTest()
	{
		// Case
		// ---[---]-------[----]-----
		// ------[---------]---------
		initLeftVariable("left", 0, 7);
		initRightVariable("right", 5, 15);
		getLeftVariable().getDomains().add(initDomain(13, 20));
		leftDomainTest = initDomain(5, 7);
		rightDomainTest = initDomain(5, 7);

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 2);
		assertTrue(getRightVariable().getDomains().size() == 2);
		assertTrue(getLeftVariable().getDomains().contains(leftDomainTest));
		assertTrue(getRightVariable().getDomains().contains(rightDomainTest));
		assertTrue(getLeftVariable().getDomains().contains(initDomain(13, 15)));
		assertTrue(getRightVariable().getDomains().contains(initDomain(13, 15)));
	}

	@Test
	public void twoRightSurroundingEqualOneLeftTest()
	{
		// Case
		// ------[---------]---------
		// ---[---]-------[----]-----
		initLeftVariable("left", 5, 15);
		initRightVariable("right", 0, 7);
		getRightVariable().getDomains().add(initDomain(13, 20));
		leftDomainTest = initDomain(5, 7);
		rightDomainTest = initDomain(5, 7);

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 2);
		assertTrue(getRightVariable().getDomains().size() == 2);
		assertTrue(getLeftVariable().getDomains().contains(leftDomainTest));
		assertTrue(getRightVariable().getDomains().contains(rightDomainTest));
		assertTrue(getLeftVariable().getDomains().contains(initDomain(13, 15)));
		assertTrue(getRightVariable().getDomains().contains(initDomain(13, 15)));
	}

	@Test
	public void twoLeftCrenelEqualTwoRightTest()
	{
		// Case
		// ------[---------]--[--]---
		// ---[---]-------[----]-----
		initLeftVariable("left", 5, 15);
		initRightVariable("right", 0, 7);
		getRightVariable().getDomains().add(initDomain(13, 20));
		getLeftVariable().getDomains().add(initDomain(19, 25));
		leftDomainTest = initDomain(5, 7);
		rightDomainTest = initDomain(5, 7);

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 3);
		assertTrue(getRightVariable().getDomains().size() == 3);
		assertTrue(getLeftVariable().getDomains().contains(leftDomainTest));
		assertTrue(getRightVariable().getDomains().contains(rightDomainTest));
		assertTrue(getLeftVariable().getDomains().contains(initDomain(13, 15)));
		assertTrue(getRightVariable().getDomains().contains(initDomain(13, 15)));
		assertTrue(getLeftVariable().getDomains().contains(initDomain(19, 20)));
		assertTrue(getRightVariable().getDomains().contains(initDomain(19, 20)));
	}

	@Test
	public void twoRightCrenelEqualTwoLeftTest()
	{
		// Case
		// ------[---------]--[--]---
		// ---[---]-------[----]-----
		initLeftVariable("left", 0, 7);
		initRightVariable("right", 5, 15);
		getLeftVariable().getDomains().add(initDomain(13, 20));
		getRightVariable().getDomains().add(initDomain(19, 25));
		leftDomainTest = initDomain(5, 7);
		rightDomainTest = initDomain(5, 7);

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 3);
		assertTrue(getRightVariable().getDomains().size() == 3);
		assertTrue(getLeftVariable().getDomains().contains(leftDomainTest));
		assertTrue(getRightVariable().getDomains().contains(rightDomainTest));
		assertTrue(getLeftVariable().getDomains().contains(initDomain(13, 15)));
		assertTrue(getRightVariable().getDomains().contains(initDomain(13, 15)));
		assertTrue(getLeftVariable().getDomains().contains(initDomain(19, 20)));
		assertTrue(getRightVariable().getDomains().contains(initDomain(19, 20)));
	}

	@Test
	public void leftInstanciatedInferiorRight()
	{
		// Case
		// --1-----------------------
		// ----[-----]---------------
		initLeftVariable("left", 0, 10);
		initRightVariable("right", 5, 10);
		try
		{
			this.getLeftVariable().setValue(0);
		} catch (VariableValueException e)
		{
			fail("Fail during setting leftVariable value : " + e.getMessage());
		}
		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 0);
		assertTrue(getRightVariable().getDomains().size() == 0);
	}

	@Test
	public void leftInstanciatedSuperiorRight()
	{
		// Case
		// ------------1-------------
		// ----[-----]---------------
		initLeftVariable("left", 0, 15);
		initRightVariable("right", 5, 10);
		try
		{
			this.getLeftVariable().setValue(15);
		} catch (VariableValueException e)
		{
			fail("Fail during setting leftVariable value : " + e.getMessage());
		}
		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 0);
		assertTrue(getRightVariable().getDomains().size() == 0);
	}

	@Test
	public void leftInstanciatedInRight()
	{
		// Case
		// --------1-----------------
		// ----[-----]---------------
		initLeftVariable("left", 0, 15);
		initRightVariable("right", 5, 10);
		try
		{
			this.getLeftVariable().setValue(7);
		} catch (VariableValueException e)
		{
			fail("Fail during setting leftVariable value : " + e.getMessage());
		}
		leftDomainTest = initDomain(7, 7);
		rightDomainTest = initDomain(7, 7);
		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}
	
	
	
	
	
	@Test
	public void rightInstanciatedInferiorLeft()
	{
		// Case
		// ----[-----]---------------
		// --1-----------------------
		initLeftVariable("left", 5, 10);
		initRightVariable("right", 0, 10);
		try
		{
			this.getRightVariable().setValue(0);
		} catch (VariableValueException e)
		{
			fail("Fail during setting leftVariable value : " + e.getMessage());
		}
		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 0);
		assertTrue(getRightVariable().getDomains().size() == 0);
	}

	@Test
	public void rightInstanciatedSuperiorLeft()
	{
		// Case
		// ----[-----]---------------
		// ------------1-------------
		initLeftVariable("left", 5, 10);
		initRightVariable("right", 0, 15);
		try
		{
			this.getRightVariable().setValue(15);
		} catch (VariableValueException e)
		{
			fail("Fail during setting leftVariable value : " + e.getMessage());
		}
		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 0);
		assertTrue(getRightVariable().getDomains().size() == 0);
	}

	@Test
	public void rightInstanciatedInLeft()
	{
		// Case
		// ----[-----]---------------
		// --------1-----------------
		initLeftVariable("left", 0, 15);
		initRightVariable("right", 5, 10);
		try
		{
			this.getRightVariable().setValue(7);
		} catch (VariableValueException e)
		{
			fail("Fail during setting leftVariable value : " + e.getMessage());
		}
		leftDomainTest = initDomain(7, 7);
		rightDomainTest = initDomain(7, 7);
		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}
}
