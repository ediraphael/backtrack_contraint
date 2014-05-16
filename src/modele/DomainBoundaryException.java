package modele;

public class DomainBoundaryException extends Exception
{
	private static final long serialVersionUID = 1L;

	public DomainBoundaryException()
	{
		super();
	}

	public DomainBoundaryException(String message)
	{
		super(message);
	}

	public DomainBoundaryException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public DomainBoundaryException(Throwable cause)
	{
		super(cause);
	}
}
