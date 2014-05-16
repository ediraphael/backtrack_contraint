package modele;

import java.util.ArrayList;

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
		this.value = 0;
		this.isInstantiated = false;
	}

	public Variable(String name, ArrayList<Domain> domain)
	{
		this.name = name;
		this.domains = domain;
		this.value = 0;
		this.isInstantiated = false;
	}

	public Variable(String name, ArrayList<Domain> domain, int value, boolean isInstantiated)
	{
		this.name = name;
		this.domains = domain;
		this.value = value;
		this.isInstantiated = isInstantiated;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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

	public void setValue(int value)
	{
		this.value = value;
		this.isInstantiated = true;
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
		return this.name + " " + this.domains + " : " + this.value + " active:" + this.isInstantiated;
	}
}
