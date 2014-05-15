package modele;

import java.util.ArrayList;

public class Variable
{
	private String nom;
	private ArrayList<Domain> domains;
	private int value;
	private boolean isInstantiated;

	public Variable(String nom, Domain domain)
	{
		this.nom = nom;
		this.domains = new ArrayList<Domain>();
		this.domains.add(domain);
		this.value = 0;
		this.isInstantiated = false;
	}

	public Variable(String nom, ArrayList<Domain> domain)
	{
		this.nom = nom;
		this.domains = domain;
		this.value = 0;
		this.isInstantiated = false;
	}

	public Variable(String nom, ArrayList<Domain> domain, int valeur, boolean isInstantiated)
	{
		this.nom = nom;
		this.domains = domain;
		this.value = valeur;
		this.isInstantiated = isInstantiated;
	}

	public String getNom()
	{
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
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

	public void setValue(int valeur)
	{
		this.value = valeur;
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
		return this.nom + " " + this.domains + " : " + this.value + " active:" + this.isInstantiated;
	}
}
