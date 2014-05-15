package modele;

public enum Operator
{
	INFERIOR("<"),
	SUPERIOR(">"),
	EQUAL("="),
	NOTEQUAL("!="),
	INFERIOREQUAL("<="),
	SUPERIOREQUAL(">=");

	private String representation;

	private Operator(String representation)
	{
		this.representation = representation;
	}

	public String getRepresentation()
	{
		return representation;
	}

	public void setRepresentation(String representation)
	{
		this.representation = representation;
	}

}
