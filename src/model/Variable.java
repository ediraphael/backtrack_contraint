package model;

import java.util.ArrayList;

import exception.VariableValueException;

public class Variable
{
	private String name;
	private ArrayList<Domain> domains;
	private int value;
	private boolean isInstantiated;

	public Variable(String name, Domain domain)
	{
		this.name = name;
		this.domains = new ArrayList<Domain>();
		this.domains.add(domain);
		this.value = domain.getBottomBoundary();
		this.isInstantiated = false;
	}

	public Variable(String name, ArrayList<Domain> domains)
	{
		this.name = name;
		this.domains = domains;
		if (!domains.isEmpty())
		{
			this.value = domains.get(0).getBottomBoundary();
		} else
		{
			this.value = 0;
		}
		this.isInstantiated = false;
	}

	public Variable(String name, ArrayList<Domain> domain, int value, boolean isInstantiated) throws VariableValueException
	{
		this.name = name;
		this.domains = domain;
		this.isInstantiated = isInstantiated;
		if (this.isInstantiated)
		{
			setValue(value);
		} else
		{
			this.value = value;
		}

	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int calculateDomainsPossility()
	{
		int nbPossibility = 0;
		for (Domain domain : this.domains)
		{
			nbPossibility += domain.getAmplitude() + 1;
		}
		return nbPossibility;
	}

	public ArrayList<Domain> getDomains()
	{
		return domains;
	}

	public void setDomains(ArrayList<Domain> domain)
	{
		this.domains = domain;
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value) throws VariableValueException
	{
		boolean isPossible = false;
		for (Domain domain : this.domains)
		{
			if (value >= domain.getBottomBoundary() && value <= domain.getUpperBoundary())
			{
				isPossible = true;
			}
		}
		if (isPossible)
		{
			this.value = value;
			this.isInstantiated = true;
		} else if (this.domains.isEmpty())
		{
			this.value = value;
		} else
		{
			throw new VariableValueException("Error during setting new value to " + name + ", " + value + " is out of possible Domains " + domains);
		}
	}

	public boolean isInstantiated()
	{
		return isInstantiated;
	}

	public void setInstantiated(boolean isInstantiated)
	{
		this.isInstantiated = isInstantiated;
	}

	@Override
	public String toString()
	{
		return this.name + ", " + this.domains + ", value=" + this.value + ", active=" + this.isInstantiated;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		} else if (!(obj instanceof Variable))
		{
			return false;
		} else if (!((Variable) obj).getName().equals(this.getName()))
		{
			return false;
		}
		return true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{
		ArrayList<Domain> newDomains = new ArrayList<Domain>();
		for (Domain domain : domains)
		{
			newDomains.add((Domain) domain.clone());
		}
		try
		{
			return new Variable(new String(name), newDomains, value, isInstantiated);
		} catch (VariableValueException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
