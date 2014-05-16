package modele;

import java.util.ArrayList;

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
			boolean possible = false;
			for (Domain leftDomain : left.getDomains())
			{
				for (Domain rightDomain : right.getDomains())
				{
					if (leftDomain.getBottomBoundary() < rightDomain.getUpperBoundary())
					{
						possible = true;
					}
				}
			}
			return possible;
		}

		public void reduceDomains(Variable left, Variable right)
		{
			for (Domain leftDomain : left.getDomains())
			{
				for (Domain rightDomain : right.getDomains())
				{

					if (left.isInstantiated() && !right.isInstantiated())
					{
						if (left.getValue() >= rightDomain.getBottomBoundary() && left.getValue() < rightDomain.getUpperBoundary())
						{
							rightDomain.setBottomBoundary(left.getValue() + 1);
						}
					} else if (!left.isInstantiated() && right.isInstantiated())
					{
						if (right.getValue() <= leftDomain.getUpperBoundary() && right.getValue() > leftDomain.getBottomBoundary())
						{
							leftDomain.setBottomBoundary(right.getValue() - 1);
						}
					} else if (checkIfPossible(left, right))
					{
						if (leftDomain.getBottomBoundary() >= rightDomain.getBottomBoundary())
						{
							rightDomain.setBottomBoundary(leftDomain.getBottomBoundary() + 1);
						}
						if (rightDomain.getUpperBoundary() <= leftDomain.getUpperBoundary())
						{
							leftDomain.setUpperBoundary(rightDomain.getUpperBoundary() - 1);
						}
					}
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
			boolean possible = false;
			for (Domain leftDomain : left.getDomains())
			{
				for (Domain rightDomain : right.getDomains())
				{
					if (rightDomain.getBottomBoundary() < leftDomain.getUpperBoundary())
					{
						possible = true;
					}
				}
			}
			return possible;
		}

		public void reduceDomains(Variable left, Variable right)
		{
			for (Domain leftDomain : left.getDomains())
			{
				for (Domain rightDomain : right.getDomains())
				{

					if (left.isInstantiated() && !right.isInstantiated())
					{
						if (left.getValue() <= rightDomain.getUpperBoundary() && left.getValue() > rightDomain.getBottomBoundary())
						{
							rightDomain.setUpperBoundary(left.getValue() - 1);
						}
					} else if (!left.isInstantiated() && right.isInstantiated())
					{
						if (right.getValue() >= leftDomain.getBottomBoundary() && right.getValue() < leftDomain.getUpperBoundary())
						{
							leftDomain.setBottomBoundary(right.getValue() + 1);
						}
					} else if (checkIfPossible(left, right))
					{
						if (leftDomain.getUpperBoundary() <= rightDomain.getUpperBoundary())
						{
							rightDomain.setUpperBoundary(leftDomain.getUpperBoundary() - 1);
						}
						if (rightDomain.getBottomBoundary() >= leftDomain.getBottomBoundary())
						{
							leftDomain.setBottomBoundary(rightDomain.getBottomBoundary() + 1);
						}
					}
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
			boolean possible = false;
			for (Domain leftDomain : left.getDomains())
			{
				for (Domain rightDomain : right.getDomains())
				{
					if (leftDomain.isCompatibleTo(rightDomain))
					{
						possible = true;
					}
				}
			}
			return possible;
		}

		public void reduceDomains(Variable left, Variable right)
		{
			ArrayList<Domain> newLeftDomains = new ArrayList<Domain>();
			ArrayList<Domain> newRightDomains = new ArrayList<Domain>();
			for (Domain leftDomain : left.getDomains())
			{
				for (Domain rightDomain : right.getDomains())
				{
					Domain newDomain = leftDomain.getIntersectionWith(rightDomain);
					newLeftDomains.add(new Domain(newDomain.getBottomBoundary(), newDomain.getUpperBoundary()));
					newRightDomains.add(new Domain(newDomain.getBottomBoundary(), newDomain.getUpperBoundary()));
				}
			}
			left.setDomains(newLeftDomains);
			right.setDomains(newRightDomains);
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
			boolean possible = false;
			for (Domain leftDomain : left.getDomains())
			{
				for (Domain rightDomain : right.getDomains())
				{
					if (!(leftDomain.getAmplitude() == 0 && rightDomain.getAmplitude() == 0 && leftDomain.getBottomBoundary() == rightDomain.getBottomBoundary() && leftDomain.getUpperBoundary() == rightDomain.getUpperBoundary()))
					{
						possible = true;
					}
				}
			}
			return possible;
		}

		public void reduceDomains(Variable left, Variable right)
		{
			boolean importantChange = true;
			while (importantChange)
			{
				importantChange = false;
				for (Domain leftDomain : left.getDomains())
				{
					for (Domain rightDomain : right.getDomains())
					{
						if (left.isInstantiated() && !right.isInstantiated())
						{
							if (left.getValue() >= rightDomain.getBottomBoundary() && left.getValue() <= rightDomain.getUpperBoundary())
							{
								// cas
								// --------1------------------------
								// --------[----------]-------------
								// Devient
								// --------1------------------------
								// ---------[---------]-------------
								if (left.getValue() == rightDomain.getBottomBoundary() && left.getValue() != rightDomain.getUpperBoundary())
								{
									rightDomain.setBottomBoundary(left.getValue() + 1);
								}
								// cas
								// -------------------1-------------
								// --------[----------]-------------
								// Devient
								// -------------------1-------------
								// --------[---------]--------------
								else if (left.getValue() != rightDomain.getBottomBoundary() && left.getValue() == rightDomain.getUpperBoundary())
								{
									rightDomain.setUpperBoundary(left.getValue() - 1);
								}
								// cas
								// --------------1------------------
								// --------[----------]-------------
								// Devient
								// --------------1------------------
								// --------[----]-[--]--------------
								else if (left.getValue() != rightDomain.getBottomBoundary() && left.getValue() != rightDomain.getUpperBoundary())
								{
									rightDomain.setUpperBoundary(left.getValue() - 1);
									right.getDomains().add(new Domain(left.getValue() + 1, rightDomain.getUpperBoundary()));
									importantChange = true;
								}
							}
						} else if (!left.isInstantiated() && right.isInstantiated())
						{
							this.reduceDomains(right, left);
						}
						// Cas :
						// ----[]-----------------------
						// ----[]-----------------------
						else if (leftDomain.getBottomBoundary() == rightDomain.getBottomBoundary() && leftDomain.getUpperBoundary() == rightDomain.getUpperBoundary() && leftDomain.getAmplitude() == 0)
						{
							left.getDomains().remove(leftDomain);
							right.getDomains().remove(rightDomain);
							importantChange = true;
						}
						if (importantChange)
						{
							break;
						}
					}
					if (importantChange)
					{
						break;
					}
				}
			}
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
			boolean possible = false;
			for (Domain leftDomain : left.getDomains())
			{
				for (Domain rightDomain : right.getDomains())
				{
					if (leftDomain.getBottomBoundary() <= rightDomain.getUpperBoundary())
					{
						possible = true;
					}
				}
			}
			return possible;
		}

		public void reduceDomains(Variable left, Variable right)
		{
			for (Domain leftDomain : left.getDomains())
			{
				for (Domain rightDomain : right.getDomains())
				{

					if (left.isInstantiated() && !right.isInstantiated())
					{
						if (left.getValue() >= rightDomain.getBottomBoundary() && left.getValue() <= rightDomain.getUpperBoundary())
						{
							rightDomain.setBottomBoundary(left.getValue());
						}
					} else if (!left.isInstantiated() && right.isInstantiated())
					{
						if (right.getValue() <= leftDomain.getUpperBoundary() && right.getValue() >= leftDomain.getBottomBoundary())
						{
							leftDomain.setBottomBoundary(right.getValue());
						}
					} else if (checkIfPossible(left, right))
					{
						if (leftDomain.getBottomBoundary() > rightDomain.getBottomBoundary())
						{
							rightDomain.setBottomBoundary(leftDomain.getBottomBoundary());
						}
						if (rightDomain.getUpperBoundary() < leftDomain.getUpperBoundary())
						{
							leftDomain.setUpperBoundary(rightDomain.getUpperBoundary());
						}
					}
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
			boolean possible = false;
			for (Domain leftDomain : left.getDomains())
			{
				for (Domain rightDomain : right.getDomains())
				{
					if (rightDomain.getBottomBoundary() <= leftDomain.getUpperBoundary())
					{
						possible = true;
					}
				}
			}
			return possible;
		}

		public void reduceDomains(Variable left, Variable right)
		{
			for (Domain leftDomain : left.getDomains())
			{
				for (Domain rightDomain : right.getDomains())
				{

					if (left.isInstantiated() && !right.isInstantiated())
					{
						if (left.getValue() <= rightDomain.getUpperBoundary() && left.getValue() >= rightDomain.getBottomBoundary())
						{
							rightDomain.setUpperBoundary(left.getValue());
						}
					} else if (!left.isInstantiated() && right.isInstantiated())
					{
						if (right.getValue() >= leftDomain.getBottomBoundary() && right.getValue() <= leftDomain.getUpperBoundary())
						{
							leftDomain.setBottomBoundary(right.getValue());
						}
					} else if (checkIfPossible(left, right))
					{
						if (leftDomain.getUpperBoundary() < rightDomain.getUpperBoundary())
						{
							rightDomain.setUpperBoundary(leftDomain.getUpperBoundary());
						}
						if (rightDomain.getBottomBoundary() > leftDomain.getBottomBoundary())
						{
							leftDomain.setBottomBoundary(rightDomain.getBottomBoundary());
						}
					}
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

	@Override
	public String toString()
	{
		return this.representation;
	}
}
