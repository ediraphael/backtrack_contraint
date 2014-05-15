package modele;

public enum Operator
{
	INFERIOR("<")
	{
		public boolean execute(Variable left, Variable right)
		{
			return left.getValue() < right.getValue();
		}

		public boolean checkIfPossible(Variable left, Variable right)
		{
			return left.getDomain().getBottomBoundary() < right.getDomain().getUpperBoundary();
		}

		public void reduceDomains(Variable left, Variable right)
		{
			if (left.isInstantiated() && !right.isInstantiated())
			{
				if (left.getValue() >= right.getDomain().getBottomBoundary() && left.getValue() < right.getDomain().getUpperBoundary())
				{
					right.getDomain().setBottomBoundary(left.getValue() + 1);
				}
			} else if (!left.isInstantiated() && right.isInstantiated())
			{
				if (right.getValue() <= left.getDomain().getUpperBoundary() && right.getValue() > left.getDomain().getBottomBoundary())
				{
					left.getDomain().setBottomBoundary(right.getValue() - 1);
				}
			} else if (checkIfPossible(left, right))
			{
				if (left.getDomain().getBottomBoundary() >= right.getDomain().getBottomBoundary())
				{
					right.getDomain().setBottomBoundary(left.getDomain().getBottomBoundary() + 1);
				}
				if (right.getDomain().getUpperBoundary() <= left.getDomain().getUpperBoundary())
				{
					left.getDomain().setUpperBoundary(right.getDomain().getUpperBoundary() - 1);
				}
			}
		}
	},
	SUPERIOR(">")
	{
		public boolean execute(Variable left, Variable right)
		{
			return left.getValue() > right.getValue();
		}

		public boolean checkIfPossible(Variable left, Variable right)
		{
			return right.getDomain().getBottomBoundary() < left.getDomain().getUpperBoundary();
		}

		public void reduceDomains(Variable left, Variable right)
		{
			if (left.isInstantiated() && !right.isInstantiated())
			{
				if (left.getValue() <= right.getDomain().getUpperBoundary() && left.getValue() > right.getDomain().getBottomBoundary())
				{
					right.getDomain().setUpperBoundary(left.getValue() - 1);
				}
			} else if (!left.isInstantiated() && right.isInstantiated())
			{
				if (right.getValue() >= left.getDomain().getBottomBoundary() && right.getValue() < left.getDomain().getUpperBoundary())
				{
					left.getDomain().setBottomBoundary(right.getValue() + 1);
				}
			} else if (checkIfPossible(left, right))
			{
				if (left.getDomain().getUpperBoundary() <= right.getDomain().getUpperBoundary())
				{
					right.getDomain().setUpperBoundary(left.getDomain().getUpperBoundary() - 1);
				}
				if (right.getDomain().getBottomBoundary() >= left.getDomain().getBottomBoundary())
				{
					left.getDomain().setBottomBoundary(right.getDomain().getBottomBoundary() + 1);
				}
			}
		}
	},
	EQUAL("=")
	{
		public boolean execute(Variable left, Variable right)
		{
			return left.getValue() == right.getValue();
		}

		public boolean checkIfPossible(Variable left, Variable right)
		{
			return left.getDomain().isCompatibleTo(right.getDomain());
		}

		public void reduceDomains(Variable left, Variable right)
		{
			Domain newDomain = left.getDomain().getIntersectionWith(right.getDomain());
			left.setDomain(new Domain(newDomain.getBottomBoundary(), newDomain.getUpperBoundary()));
			right.setDomain(new Domain(newDomain.getBottomBoundary(), newDomain.getUpperBoundary()));
		}
	},
	NOTEQUAL("!=")
	{
		public boolean execute(Variable left, Variable right)
		{
			return left.getValue() != right.getValue();
		}

		public boolean checkIfPossible(Variable left, Variable right)
		{
			return !(left.getDomain().getAmplitude() == 0 && right.getDomain().getAmplitude() == 0 && left.getDomain().getBottomBoundary() == right.getDomain().getBottomBoundary() && left.getDomain().getUpperBoundary() == right.getDomain().getUpperBoundary());
		}
	},
	INFERIOREQUAL("<=")
	{
		public boolean execute(Variable left, Variable right)
		{
			return left.getValue() <= right.getValue();
		}

		public boolean checkIfPossible(Variable left, Variable right)
		{
			return left.getDomain().getBottomBoundary() <= right.getDomain().getUpperBoundary();
		}

		public void reduceDomains(Variable left, Variable right)
		{
			if (left.isInstantiated() && !right.isInstantiated())
			{
				if (left.getValue() >= right.getDomain().getBottomBoundary() && left.getValue() <= right.getDomain().getUpperBoundary())
				{
					right.getDomain().setBottomBoundary(left.getValue());
				}
			} else if (!left.isInstantiated() && right.isInstantiated())
			{
				if (right.getValue() <= left.getDomain().getUpperBoundary() && right.getValue() >= left.getDomain().getBottomBoundary())
				{
					left.getDomain().setBottomBoundary(right.getValue());
				}
			} else if (checkIfPossible(left, right))
			{
				if (left.getDomain().getBottomBoundary() > right.getDomain().getBottomBoundary())
				{
					right.getDomain().setBottomBoundary(left.getDomain().getBottomBoundary());
				}
				if (right.getDomain().getUpperBoundary() < left.getDomain().getUpperBoundary())
				{
					left.getDomain().setUpperBoundary(right.getDomain().getUpperBoundary());
				}
			}
		}
	},
	SUPERIOREQUAL(">=")
	{
		public boolean execute(Variable left, Variable right)
		{
			return left.getValue() >= right.getValue();
		}

		public boolean checkIfPossible(Variable left, Variable right)
		{
			return right.getDomain().getBottomBoundary() <= left.getDomain().getUpperBoundary();
		}

		public void reduceDomains(Variable left, Variable right)
		{
			if (left.isInstantiated() && !right.isInstantiated())
			{
				if (left.getValue() <= right.getDomain().getUpperBoundary() && left.getValue() >= right.getDomain().getBottomBoundary())
				{
					right.getDomain().setUpperBoundary(left.getValue());
				}
			} else if (!left.isInstantiated() && right.isInstantiated())
			{
				if (right.getValue() >= left.getDomain().getBottomBoundary() && right.getValue() <= left.getDomain().getUpperBoundary())
				{
					left.getDomain().setBottomBoundary(right.getValue());
				}
			} else if (checkIfPossible(left, right))
			{
				if (left.getDomain().getUpperBoundary() < right.getDomain().getUpperBoundary())
				{
					right.getDomain().setUpperBoundary(left.getDomain().getUpperBoundary());
				}
				if (right.getDomain().getBottomBoundary() > left.getDomain().getBottomBoundary())
				{
					left.getDomain().setBottomBoundary(right.getDomain().getBottomBoundary());
				}
			}
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

	public void reduceDomains(Variable left, Variable right)
	{

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
