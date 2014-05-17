package jUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import Exception.DomainBoundaryException;

import modele.Domain;

public class DomainTest
{
	private Domain domain;

	@Test
	public void normalTest()
	{
		try
		{
			domain = new Domain(0, 5);
			assertTrue(domain != null);
		} catch (DomainBoundaryException e)
		{
			fail("Fail during domain creation!");
		}
	}

	@Test
	public void tinyTest()
	{
		try
		{
			domain = new Domain(5, 5);
			assertTrue(domain != null);
		} catch (DomainBoundaryException e)
		{
			fail("Fail during domain creation!");
		}
	}

	@Test
	public void reverseTest()
	{
		try
		{
			domain = new Domain(10, 5);
			assertTrue(domain == null);
		} catch (DomainBoundaryException e)
		{
			assertTrue(domain == null);
		}
	}
}
