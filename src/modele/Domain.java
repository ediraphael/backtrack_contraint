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

}
