package modele;

public class VariableValueException extends Exception
{
	private static final long serialVersionUID = 1L;

	public VariableValueException()
	{
		super();
	}

	public VariableValueException(String message)
	{
		super(message);
	}

	public VariableValueException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public VariableValueException(Throwable cause)
	{
		super(cause);
	}
}
