package jUnitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modele.Domain;
import modele.Operator;

public class OperatorNotEqualTest extends AbstractOperatorTest
{
	Domain leftDomainTest;
	Domain rightDomainTest;

	@Before
	public void initOperator()
	{
		setOperator(Operator.NOTEQUAL);
	}

	@Test
	public void leftEqualRightAndAmplitudeZeroTest()
	{
		// Case
		// ---------[]----------------
		// ---------[]----------------
		initLeftVariable("left", 5, 5);
		initRightVariable("right", 5, 5);

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 0);
		assertTrue(getRightVariable().getDomains().size() == 0);
	}

	@Test
	public void leftAmplitudeZeroEqualRightTest()
	{
		// Case
		// ---------[]----------------
		// -------[---]---------------
		initLeftVariable("left", 5, 5);
		initRightVariable("right", 0, 10);
		leftDomainTest = initDomain(5, 5);
		rightDomainTest = initDomain(0, 4);

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertTrue(getRightVariable().getDomains().size() == 2);
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertTrue(getRightVariable().getDomains().contains(rightDomainTest));
		assertTrue(getRightVariable().getDomains().contains(initDomain(6, 10)));
	}
	
	@Test
	public void leftAmplitudeZeroLeftEqualRightTest()
	{
		// Case
		// ------[]-------------------
		// -------[---]---------------
		initLeftVariable("left", 0, 0);
		initRightVariable("right", 0, 10);
		leftDomainTest = initDomain(0, 0);
		rightDomainTest = initDomain(1, 10);

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}
	
	@Test
	public void leftAmplitudeZeroRightEqualRightTest()
	{
		// Case
		// -----------[]--------------
		// -------[---]---------------
		initLeftVariable("left", 10, 10);
		initRightVariable("right", 0, 10);
		leftDomainTest = initDomain(10, 10);
		rightDomainTest = initDomain(0, 9);

		reduceDomains();
		assertTrue(getLeftVariable().getDomains().size() == 1);
		assertTrue(getRightVariable().getDomains().size() == 1);
		assertEquals(leftDomainTest, getLeftVariable().getDomains().get(0));
		assertEquals(rightDomainTest, getRightVariable().getDomains().get(0));
	}
}
