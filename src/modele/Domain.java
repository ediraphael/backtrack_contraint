package modele;

public class Domain
{
	private int bottomBoundary;
	private int upperBoundary;

	public Domain(int bottomBoundary, int upperBoundary) throws DomainBoundaryException
	{
		if (bottomBoundary > upperBoundary)
		{
			throw new DomainBoundaryException("The bottom boundary should be inferior or equal to the upper boundary");
		}
		this.bottomBoundary = bottomBoundary;
		this.upperBoundary = upperBoundary;
	}

	public Domain getIntersectionWith(Domain otherDomain) throws DomainBoundaryException
	{
		if (this.isCompatibleTo(otherDomain))
		{
			int newBottomBoundary = Math.max(this.bottomBoundary, otherDomain.bottomBoundary);
			int newUpperBoundary = Math.min(this.upperBoundary, otherDomain.upperBoundary);
			return new Domain(newBottomBoundary, newUpperBoundary);
		}
		return null;
	}

	public boolean isCompatibleTo(Domain otherDomain)
	{
		return (this.bottomBoundary <= otherDomain.upperBoundary && otherDomain.bottomBoundary <= this.upperBoundary) || (otherDomain.bottomBoundary <= this.upperBoundary && this.bottomBoundary <= otherDomain.upperBoundary);
	}

	public int getAmplitude()
	{
		return this.upperBoundary - this.bottomBoundary;
	}

	public int getBottomBoundary()
	{
		return bottomBoundary;
	}

	public void setBottomBoundary(int bottomBoundary) throws DomainBoundaryException
	{
		if (bottomBoundary > this.upperBoundary)
		{
			throw new DomainBoundaryException("The bottom boundary should be inferior or equal to the upper boundary");
		}
		this.bottomBoundary = bottomBoundary;
	}

	public int getUpperBoundary()
	{
		return upperBoundary;
	}

	public void setUpperBoundary(int upperBoundary) throws DomainBoundaryException
	{
		if (upperBoundary < this.bottomBoundary)
		{
			throw new DomainBoundaryException("The bottom boundary should be inferior or equal to the upper boundary");
		}
		this.upperBoundary = upperBoundary;
	}

	@Override
	public String toString()
	{
		return "[" + this.bottomBoundary + ".." + this.upperBoundary + "]";
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj.getClass() != this.getClass())
		{
			return false;
		} else if (this.bottomBoundary != ((Domain) obj).getBottomBoundary())
		{
			return false;
		} else if (this.upperBoundary != ((Domain) obj).getUpperBoundary())
		{
			return false;
		}
		return true;
	}
}
