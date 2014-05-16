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
}
