package jUnitTest;

import modele.Operator;
import static org.junit.Assert.*;

import org.junit.Test;

public class OperatorRecognitionTest extends AbstractOperatorTest
{
	@Test
	public void equalTest()
	{
		assertEquals(Operator.EQUAL, Operator.getOperatorByString("=="));
	}

	@Test
	public void notEqualTest()
	{
		assertEquals(Operator.NOTEQUAL, Operator.getOperatorByString("!="));
	}

	@Test
	public void inferiorTest()
	{
		assertEquals(Operator.INFERIOR, Operator.getOperatorByString("<"));
	}

	@Test
	public void superiorTest()
	{
		assertEquals(Operator.SUPERIOR, Operator.getOperatorByString(">"));
	}

	@Test
	public void inferiorEqualTest()
	{
		assertEquals(Operator.INFERIOREQUAL, Operator.getOperatorByString("<="));
	}

	@Test
	public void superiorEqualTest()
	{
		assertEquals(Operator.SUPERIOREQUAL, Operator.getOperatorByString(">="));
	}
}
