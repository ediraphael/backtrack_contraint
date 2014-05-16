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
							// Case
							// --------1------------------------
							// --------[-------]----------------
							// Become
							// --------1------------------------
							// ---------[------]----------------
							if (left.getValue() >= rightDomain.getBottomBoundary() && left.getValue() < rightDomain.getUpperBoundary())
							{
								rightDomain.setBottomBoundary(left.getValue() + 1);
							}
							// Case
							// ----------------1-----------------
							// --------[-------]----------------
							// Become
							// ----------------1----------------
							// ---------------------------------
							else if (left.getValue() == rightDomain.getUpperBoundary())
							{
								right.getDomains().remove(rightDomain);
								importantChange = true;
							}
						} else if (!left.isInstantiated() && right.isInstantiated())
						{
							// Case
							// --------[-------]----------------
							// ----------------1----------------
							// Become
							// --------[------]-----------------
							// ----------------1----------------
							if (right.getValue() <= leftDomain.getUpperBoundary() && right.getValue() > leftDomain.getBottomBoundary())
							{
								leftDomain.setBottomBoundary(right.getValue() - 1);
							}
							// Case
							// --------[-------]----------------
							// --------1------------------------
							// Become
							// ---------------------------------
							// --------1------------------------
							else if (right.getValue() == leftDomain.getUpperBoundary())
							{
								left.getDomains().remove(leftDomain);
								importantChange = true;
							}
						}
						// Mean that the bottom boundary of the left domain is under to the upper boundary of the right domain
						else if (checkIfPossible(left, right))
						{
							// Case
							// --------[-------]----------------
							// -----[------------]--------------
							// Become
							// --------[-------]----------------
							// ---------[--------]--------------
							if (leftDomain.getBottomBoundary() >= rightDomain.getBottomBoundary())
							{
								rightDomain.setBottomBoundary(leftDomain.getBottomBoundary() + 1);
							}
							// Case
							// --------[-----------]------------
							// -----------[------]--------------
							// Become
							// --------[--------]---------------
							// -----------[------]---------------
							if (rightDomain.getUpperBoundary() <= leftDomain.getUpperBoundary())
							{
								leftDomain.setUpperBoundary(rightDomain.getUpperBoundary() - 1);
							}
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
							// Case
							// ------------------1--------------
							// -----[------------]--------------
							// Become
							// ------------------1--------------
							// -----[-----------]---------------
							if (left.getValue() <= rightDomain.getUpperBoundary() && left.getValue() > rightDomain.getBottomBoundary())
							{
								rightDomain.setUpperBoundary(left.getValue() - 1);
							}
							// Case
							// -----1---------------------------
							// -----[------------]--------------
							// Become
							// -----1--------------------------
							// ---------------------------------
							else if (left.getValue() == rightDomain.getBottomBoundary())
							{
								right.getDomains().remove(rightDomain);
							}
						} else if (!left.isInstantiated() && right.isInstantiated())
						{
							// Case
							// -----[------------]--------------
							// -----1---------------------------
							// Become
							// ------[-----------]--------------
							// -----1---------------------------
							if (right.getValue() >= leftDomain.getBottomBoundary() && right.getValue() < leftDomain.getUpperBoundary())
							{
								leftDomain.setBottomBoundary(right.getValue() + 1);
							}
							// Case
							// -----[------------]--------------
							// ------------------1--------------
							// Become
							// ---------------------------------
							// ------------------1--------------
							else if (right.getValue() == leftDomain.getUpperBoundary())
							{
								left.getDomains().remove(leftDomain);
								importantChange = true;
							}
						}
						// Mean that the bottom boundary of the right domain is under to the upper boundary of the left domain
						else if (checkIfPossible(left, right))
						{
							// Case
							// -----------[------]--------------
							// ---------[--------]--------------
							// Become
							// -----------[------]--------------
							// ---------[-------]---------------
							if (leftDomain.getUpperBoundary() <= rightDomain.getUpperBoundary())
							{
								rightDomain.setUpperBoundary(leftDomain.getUpperBoundary() - 1);
							}
							// Case
							// ---------[----------]------------
							// ---------[--------]--------------
							// Become
							// ----------[---------]------------
							// ---------[--------]--------------
							if (rightDomain.getBottomBoundary() >= leftDomain.getBottomBoundary())
							{
								leftDomain.setBottomBoundary(rightDomain.getBottomBoundary() + 1);
							}
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
					System.out.println(leftDomain);
					System.out.println(rightDomain);
					Domain newDomain = leftDomain.getIntersectionWith(rightDomain);
					// Case
					// -----------[------]--------------
					// ---------[-----------]-----------
					// Become
					// -----------[------]--------------
					// -----------[------]--------------
					System.out.println(newDomain);
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
									right.getDomains().add(new Domain(left.getValue() + 1, rightDomain.getUpperBoundary()));
									rightDomain.setUpperBoundary(left.getValue() - 1);
									importantChange = true;
								}
							}
						} else if (!left.isInstantiated() && right.isInstantiated())
						{
							if (right.getValue() >= leftDomain.getBottomBoundary() && right.getValue() <= leftDomain.getUpperBoundary())
							{
								// cas
								// --------1------------------------
								// --------[----------]-------------
								// Devient
								// --------1------------------------
								// ---------[---------]-------------
								if (right.getValue() == leftDomain.getBottomBoundary() && right.getValue() != leftDomain.getUpperBoundary())
								{
									leftDomain.setBottomBoundary(right.getValue() + 1);
								}
								// cas
								// -------------------1-------------
								// --------[----------]-------------
								// Devient
								// -------------------1-------------
								// --------[---------]--------------
								else if (right.getValue() != leftDomain.getBottomBoundary() && right.getValue() == leftDomain.getUpperBoundary())
								{
									leftDomain.setUpperBoundary(right.getValue() - 1);
								}
								// cas
								// --------------1------------------
								// --------[----------]-------------
								// Devient
								// --------------1------------------
								// --------[----]-[--]--------------
								else if (right.getValue() != leftDomain.getBottomBoundary() && right.getValue() != leftDomain.getUpperBoundary())
								{
									left.getDomains().add(new Domain(right.getValue() + 1, leftDomain.getUpperBoundary()));
									leftDomain.setUpperBoundary(right.getValue() - 1);
									importantChange = true;
								}
							}
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

	public static void main(String[] args)
	{
		Variable var1 = new Variable("var1", new Domain(5, 5));
		Variable var2 = new Variable("var2", new Domain(5, 6));
		Operator.EQUAL.reduceDomains(var1, var2);
		System.out.println(var1);
		System.out.println(var2);
		var1.setValue(5);
		Operator.EQUAL.reduceDomains(var1, var2);
		System.out.println(var1);
		System.out.println(var2);
	}
}
