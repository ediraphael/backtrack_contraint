package modele;

public enum Operator
{
	INFERIOR("<")
	{
		public boolean execute(Variable left, Variable right)
		{
			return left.getValeur() < right.getValeur();
		}

		public boolean checkIfPossible(Variable left, Variable right)
		{
			return left.getDomain().getBottomBoundary() < right.getDomain().getUpperBoundary();
		}
	},
	SUPERIOR(">")
	{
		public boolean execute(Variable left, Variable right)
		{
			return left.getValeur() > right.getValeur();
		}

		public boolean checkIfPossible(Variable left, Variable right)
		{
			return right.getDomain().getBottomBoundary() < left.getDomain().getUpperBoundary();
		}
	},
	EQUAL("=")
	{
		public boolean execute(Variable left, Variable right)
		{
			return left.getValeur() == right.getValeur();
		}

		public boolean checkIfPossible(Variable left, Variable right)
		{
			return left.getDomain().isCompatibleTo(right.getDomain());
		}
	},
	NOTEQUAL("!=")
	{
		public boolean execute(Variable left, Variable right)
		{
			return left.getValeur() != right.getValeur();
		}
		
		public boolean checkIfPossible(Variable left, Variable right)
		{
			return false;
		}
	},
	INFERIOREQUAL("<=")
	{
		public boolean execute(Variable left, Variable right)
		{
			return left.getValeur() <= right.getValeur();
		}

		public boolean checkIfPossible(Variable left, Variable right)
		{
			return left.getDomain().getBottomBoundary() <= right.getDomain().getUpperBoundary();
		}
	},
	SUPERIOREQUAL(">=")
	{
		public boolean execute(Variable left, Variable right)
		{
			return left.getValeur() >= right.getValeur();
		}

		public boolean checkIfPossible(Variable left, Variable right)
		{
			return right.getDomain().getBottomBoundary() <= left.getDomain().getUpperBoundary();
		}
	};

	private String representation;

	private Operator(String representation)
	{
		this.representation = representation;
	}

	public boolean execute(Variable left, Variable right)
	{
		return false;
	}

	public boolean checkIfPossible(Variable left, Variable right)
	{
		return false;
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
