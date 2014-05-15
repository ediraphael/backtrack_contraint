package modele;

public class Variable
{
	private String nom;
	private Domain domain;
	private int value;
	private boolean isInstantiated;

	public Variable(String nom, Domain domain)
	{
		this.nom = nom;
		this.domain = domain;
		this.value = 0;
		this.isInstantiated = false;
	}

	public Variable(String nom, Domain domain, int valeur, boolean isInstantiated)
	{
		this.nom = nom;
		this.domain = domain;
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

	public Domain getDomain()
	{
		return domain;
	}

	public void setDomain(Domain domain)
	{
		this.domain = domain;
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
		return this.nom + " " + this.domain + " : " + this.value + " active:" + this.isInstantiated;
	}
}
