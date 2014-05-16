package modele;

public class Domain
{
	private int bottomBoundary;
	private int upperBoundary;

	public Domain(int bottomBoundary, int upperBoundary)
	{
		super();
		this.bottomBoundary = bottomBoundary;
		this.upperBoundary = upperBoundary;
	}

	public Domain getIntersectionWith(Domain otherDomain)
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

	public void setBottomBoundary(int bottomBoundary)
	{
		this.bottomBoundary = bottomBoundary;
	}

	public int getUpperBoundary()
	{
		return upperBoundary;
	}

	public void setUpperBoundary(int upperBoundary)
	{
		this.upperBoundary = upperBoundary;
	}

	@Override
	public String toString()
	{
		return "[" + this.bottomBoundary + ".." + this.upperBoundary + "]";
	}
}
