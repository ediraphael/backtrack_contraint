package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import Exception.DomainBoundaryException;
import Exception.VariableValueException;

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

		public boolean reduceDomains(Variable left, Variable right) throws DomainBoundaryException
		{
			boolean asChange = false;
			boolean isPossible = false;
			boolean importantChange = true;
			HashMap<Domain, Integer> rightDomainUsed = new HashMap<Domain, Integer>();
			while (importantChange)
			{
				importantChange = false;
				for (Domain leftDomain : left.getDomains())
				{
					importantChange = false;
					isPossible = false;
					for (Domain rightDomain : right.getDomains())
					{
						importantChange = false;
						if (left.isInstantiated() && !right.isInstantiated())
						{
							if (left.getValue() < rightDomain.getUpperBoundary())
							{
								isPossible = true;
								rightDomainUsed.put(rightDomain, 1);
							}
							// Case
							// --------1------------------------
							// --------[-------]----------------
							// Become
							// --------1------------------------
							// ---------[------]----------------
							if (left.getValue() >= rightDomain.getBottomBoundary() && left.getValue() < rightDomain.getUpperBoundary())
							{
								rightDomain.setBottomBoundary(left.getValue() + 1);
								asChange = true;
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
								isPossible = false;
								asChange = true;
							}
						} else if (!left.isInstantiated() && right.isInstantiated())
						{
							if (right.getValue() > leftDomain.getBottomBoundary())
							{
								isPossible = true;
								rightDomainUsed.put(rightDomain, 1);
							}
							// Case
							// --------[-------]----------------
							// ----------------1----------------
							// Become
							// --------[------]-----------------
							// ----------------1----------------
							if (right.getValue() <= leftDomain.getUpperBoundary() && right.getValue() > leftDomain.getBottomBoundary())
							{
								leftDomain.setBottomBoundary(right.getValue() - 1);
								asChange = true;
							}
							// Case
							// --------[-------]----------------
							// --------1------------------------
							// Become
							// ---------------------------------
							// --------1------------------------
							else if (right.getValue() == leftDomain.getBottomBoundary())
							{
								left.getDomains().remove(leftDomain);
								importantChange = true;
								isPossible = false;
								asChange = true;
							}
						}
						// Mean that the bottom boundary of the left domain is under to the upper boundary of the right domain
						else if (leftDomain.getBottomBoundary() < rightDomain.getUpperBoundary())
						{
							isPossible = true;
							rightDomainUsed.put(rightDomain, 1);
							// Case
							// --------[-------]----------------
							// -----[------------]--------------
							// Become
							// --------[-------]----------------
							// ---------[--------]--------------
							if (leftDomain.getBottomBoundary() >= rightDomain.getBottomBoundary() && leftDomain.getBottomBoundary() < rightDomain.getUpperBoundary())
							{
								boolean shouldReduce = true;
								for (Domain checkLeftDomain : left.getDomains())
								{
									if (leftDomain != checkLeftDomain && checkLeftDomain.getBottomBoundary() < rightDomain.getUpperBoundary())
									{
										shouldReduce = false;
									}
								}
								if (shouldReduce)
								{
									if (rightDomain.getBottomBoundary() != leftDomain.getBottomBoundary() + 1)
									{
										rightDomain.setBottomBoundary(leftDomain.getBottomBoundary() + 1);
										asChange = true;
									}
								}
							}
							// Case
							// --------[-----------]------------
							// -----------[------]--------------
							// Become
							// --------[--------]---------------
							// -----------[------]---------------
							if (rightDomain.getUpperBoundary() <= leftDomain.getUpperBoundary() && rightDomain.getUpperBoundary() > leftDomain.getBottomBoundary())
							{
								boolean shouldReduce = true;
								for (Domain checkRightDomain : right.getDomains())
								{
									if (rightDomain != checkRightDomain && checkRightDomain.getUpperBoundary() > rightDomain.getBottomBoundary())
									{
										shouldReduce = false;
									}
								}
								if (shouldReduce)
								{
									if (leftDomain.getUpperBoundary() != rightDomain.getUpperBoundary() - 1)
									{
										leftDomain.setUpperBoundary(rightDomain.getUpperBoundary() - 1);
										asChange = true;
									}
								}
							}
						}
						if (importantChange)
						{
							break;
						}
					}
					if (!isPossible)
					{
						left.getDomains().remove(leftDomain);
						importantChange = true;
						asChange = true;
					}
					if (importantChange)
					{
						break;
					}
				}
			}
			for (Domain rightDomain : right.getDomains())
			{
				if (!rightDomainUsed.containsKey(rightDomain))
				{
					right.getDomains().remove(rightDomain);
					importantChange = true;
					asChange = true;
					break;
				}
			}
			if (importantChange)
			{
				Operator.INFERIOR.reduceDomains(left, right);
			}
			if (!left.isInstantiated() && !right.isInstantiated())
			{
				if (!left.getDomains().isEmpty())
				{
					try
					{
						left.setValue(left.getDomains().get(0).getBottomBoundary());
						left.setInstantiated(false);
					} catch (VariableValueException e)
					{
						e.printStackTrace();
					}
				}
				if (!right.getDomains().isEmpty())
				{
					try
					{
						right.setValue(right.getDomains().get(0).getBottomBoundary());
						right.setInstantiated(false);
					} catch (VariableValueException e)
					{
						e.printStackTrace();
					}
				}
			}
			return asChange;
		}
	},
	SUPERIOR(">")
	{
		public boolean execute(Variable left, Variable right)
		{
			return Operator.INFERIOR.execute(right, left);
		}

		public boolean checkIfPossible(Variable left, Variable right)
		{
			return Operator.INFERIOR.checkIfPossible(right, left);
		}

		public boolean reduceDomains(Variable left, Variable right) throws DomainBoundaryException
		{
			return Operator.INFERIOR.reduceDomains(right, left);
		}
	},
	EQUAL("==")
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

		public boolean reduceDomains(Variable left, Variable right) throws DomainBoundaryException
		{
			boolean asChange = false;
			boolean importantChange = true;
			TreeSet<Domain> newLeftDomains = new TreeSet<Domain>();
			TreeSet<Domain> newRightDomains = new TreeSet<Domain>();

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
								newRightDomains.add(new Domain(left.getValue(), left.getValue()));
								asChange = true;
							} else
							{
								right.getDomains().remove(rightDomain);
								importantChange = true;
								asChange = true;
							}
						} else if (!left.isInstantiated() && right.isInstantiated())
						{
							if (right.getValue() >= leftDomain.getBottomBoundary() && right.getValue() <= leftDomain.getUpperBoundary())
							{
								newLeftDomains.add(new Domain(right.getValue(), right.getValue()));
								asChange = true;
							} else
							{
								right.getDomains().remove(rightDomain);
								importantChange = true;
								asChange = true;
							}
						}
						// Case
						// -----------[------]--------------
						// ---------[-----------]-----------
						// Become
						// -----------[------]--------------
						// -----------[------]--------------
						else if (leftDomain.isCompatibleTo(rightDomain))
						{
							if (leftDomain.getBottomBoundary() != rightDomain.getBottomBoundary() || leftDomain.getUpperBoundary() != rightDomain.getUpperBoundary())
							{
								asChange = true;
							}
							Domain newDomain = leftDomain.getIntersectionWith(rightDomain);
							newLeftDomains.add(new Domain(newDomain.getBottomBoundary(), newDomain.getUpperBoundary()));
							newRightDomains.add(new Domain(newDomain.getBottomBoundary(), newDomain.getUpperBoundary()));
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
			if (left.isInstantiated())
			{
				newLeftDomains.add(new Domain(left.getValue(), left.getValue()));
				asChange = true;
			} else if (right.isInstantiated())
			{
				newRightDomains.add(new Domain(right.getValue(), right.getValue()));
				asChange = true;
			}
			if (left.getDomains().size() != newLeftDomains.size() || right.getDomains().size() != newRightDomains.size())
			{
				asChange = true;
			}
			left.setDomains(new ArrayList<>(newLeftDomains));
			right.setDomains(new ArrayList<>(newRightDomains));
			if (!left.isInstantiated() && !right.isInstantiated())
			{
				if (!left.getDomains().isEmpty())
				{
					try
					{
						left.setValue(left.getDomains().get(0).getBottomBoundary());
						left.setInstantiated(false);
					} catch (VariableValueException e)
					{
						e.printStackTrace();
					}
				}
				if (!right.getDomains().isEmpty())
				{
					try
					{
						right.setValue(right.getDomains().get(0).getBottomBoundary());
						right.setInstantiated(false);
					} catch (VariableValueException e)
					{
						e.printStackTrace();
					}
				}
			}
			return asChange;
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

		public boolean reduceDomains(Variable left, Variable right) throws DomainBoundaryException
		{
			boolean asChange = false;
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
								// case
								// --------1------------------------
								// --------[----------]-------------
								// Become
								// --------1------------------------
								// ---------[---------]-------------
								if (left.getValue() == rightDomain.getBottomBoundary() && left.getValue() != rightDomain.getUpperBoundary())
								{
									rightDomain.setBottomBoundary(left.getValue() + 1);
									asChange = true;
								}
								// case
								// -------------------1-------------
								// --------[----------]-------------
								// Become
								// -------------------1-------------
								// --------[---------]--------------
								else if (left.getValue() != rightDomain.getBottomBoundary() && left.getValue() == rightDomain.getUpperBoundary())
								{
									rightDomain.setUpperBoundary(left.getValue() - 1);
									asChange = true;
								}
								// case
								// --------------1------------------
								// --------[----------]-------------
								// Become
								// --------------1------------------
								// --------[----]-[--]--------------
								else if (left.getValue() != rightDomain.getBottomBoundary() && left.getValue() != rightDomain.getUpperBoundary())
								{
									right.getDomains().add(new Domain(left.getValue() + 1, rightDomain.getUpperBoundary()));
									rightDomain.setUpperBoundary(left.getValue() - 1);
									importantChange = true;
									asChange = true;
								}
							}
						} else if (!left.isInstantiated() && right.isInstantiated())
						{
							if (right.getValue() >= leftDomain.getBottomBoundary() && right.getValue() <= leftDomain.getUpperBoundary())
							{
								// case
								// --------1------------------------
								// --------[----------]-------------
								// Become
								// --------1------------------------
								// ---------[---------]-------------
								if (right.getValue() == leftDomain.getBottomBoundary() && right.getValue() != leftDomain.getUpperBoundary())
								{
									leftDomain.setBottomBoundary(right.getValue() + 1);
									asChange = true;
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
									asChange = true;
								}
								// case
								// --------------1------------------
								// --------[----------]-------------
								// Become
								// --------------1------------------
								// --------[----]-[--]--------------
								else if (right.getValue() != leftDomain.getBottomBoundary() && right.getValue() != leftDomain.getUpperBoundary())
								{
									left.getDomains().add(new Domain(right.getValue() + 1, leftDomain.getUpperBoundary()));
									leftDomain.setUpperBoundary(right.getValue() - 1);
									importantChange = true;
									asChange = true;
								}
							}
						}
						// Case :
						// ----[]-----------------------
						// ----[]-----------------------
						else if (leftDomain.getBottomBoundary() == rightDomain.getBottomBoundary() && leftDomain.getUpperBoundary() == rightDomain.getUpperBoundary() && leftDomain.getAmplitude() == 0)
						{
							left.getDomains().remove(leftDomain);
							right.getDomains().remove(rightDomain);
							importantChange = true;
							asChange = true;
						} else
						{
							if (leftDomain.getAmplitude() == 0 && leftDomain.getIntersectionWith(rightDomain) != null)
							{
								if (leftDomain.getBottomBoundary() == rightDomain.getBottomBoundary())
								{
									rightDomain.setBottomBoundary(leftDomain.getBottomBoundary() + 1);
									asChange = true;
								} else if (leftDomain.getBottomBoundary() == rightDomain.getUpperBoundary())
								{
									rightDomain.setUpperBoundary(leftDomain.getBottomBoundary() - 1);
									asChange = true;
								} else
								{
									right.getDomains().add(new Domain(leftDomain.getBottomBoundary() + 1, rightDomain.getUpperBoundary()));
									rightDomain.setUpperBoundary(leftDomain.getBottomBoundary() - 1);
									importantChange = true;
									asChange = true;
								}
							}
							if (rightDomain.getAmplitude() == 0 && leftDomain.getIntersectionWith(rightDomain) != null)
							{
								if (rightDomain.getBottomBoundary() == leftDomain.getBottomBoundary())
								{
									leftDomain.setBottomBoundary(leftDomain.getBottomBoundary() + 1);
									asChange = true;
								} else if (rightDomain.getBottomBoundary() == leftDomain.getUpperBoundary())
								{
									leftDomain.setBottomBoundary(leftDomain.getBottomBoundary() - 1);
									asChange = true;
								} else
								{
									left.getDomains().add(new Domain(rightDomain.getBottomBoundary() + 1, leftDomain.getUpperBoundary()));
									leftDomain.setUpperBoundary(rightDomain.getBottomBoundary() - 1);
									importantChange = true;
									asChange = true;
								}
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
			if (!left.isInstantiated() && !right.isInstantiated())
			{
				if (!left.getDomains().isEmpty())
				{
					try
					{
						left.setValue(left.getDomains().get(0).getBottomBoundary());
						left.setInstantiated(false);
					} catch (VariableValueException e)
					{
						e.printStackTrace();
					}
				}
				if (!right.getDomains().isEmpty())
				{
					try
					{
						right.setValue(right.getDomains().get(0).getBottomBoundary());
						right.setInstantiated(false);
					} catch (VariableValueException e)
					{
						e.printStackTrace();
					}
				}
			}
			return asChange;
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

		public boolean reduceDomains(Variable left, Variable right) throws DomainBoundaryException
		{
			boolean asChange = false;
			boolean isPossible = false;
			boolean importantChange = true;
			HashMap<Domain, Integer> rightDomainUsed = new HashMap<Domain, Integer>();
			while (importantChange)
			{
				importantChange = false;
				for (Domain leftDomain : left.getDomains())
				{
					importantChange = false;
					isPossible = false;
					for (Domain rightDomain : right.getDomains())
					{
						importantChange = false;
						if (left.isInstantiated() && !right.isInstantiated())
						{
							if (left.getValue() <= rightDomain.getUpperBoundary())
							{
								isPossible = true;
								rightDomainUsed.put(rightDomain, 1);
							}
							// Case
							// --------1------------------------
							// --------[-------]----------------
							// Become
							// --------1------------------------
							// ---------[------]----------------
							if (left.getValue() >= rightDomain.getBottomBoundary() && left.getValue() <= rightDomain.getUpperBoundary())
							{
								rightDomain.setBottomBoundary(left.getValue());
								asChange = true;
							}
						} else if (!left.isInstantiated() && right.isInstantiated())
						{
							if (right.getValue() >= leftDomain.getBottomBoundary())
							{
								isPossible = true;
								rightDomainUsed.put(rightDomain, 1);
							}
							// Case
							// --------[-------]----------------
							// ----------------1----------------
							// Become
							// --------[------]-----------------
							// ----------------1----------------
							if (right.getValue() <= leftDomain.getUpperBoundary() && right.getValue() >= leftDomain.getBottomBoundary())
							{
								leftDomain.setBottomBoundary(right.getValue());
								asChange = true;
							}
						}
						// Mean that the bottom boundary of the left domain is under to the upper boundary of the right domain
						else if (leftDomain.getBottomBoundary() <= rightDomain.getUpperBoundary())
						{
							isPossible = true;
							rightDomainUsed.put(rightDomain, 1);
							// Case
							// --------[-------]----------------
							// -----[------------]--------------
							// Become
							// --------[-------]----------------
							// ---------[--------]--------------
							if (leftDomain.getBottomBoundary() >= rightDomain.getBottomBoundary() && leftDomain.getBottomBoundary() <= rightDomain.getUpperBoundary())
							{
								boolean shouldReduce = true;
								for (Domain checkLeftDomain : left.getDomains())
								{
									if (leftDomain != checkLeftDomain && checkLeftDomain.getBottomBoundary() <= rightDomain.getUpperBoundary())
									{
										shouldReduce = false;
									}
								}
								if (shouldReduce)
								{
									if (rightDomain.getBottomBoundary() != leftDomain.getBottomBoundary())
									{
										rightDomain.setBottomBoundary(leftDomain.getBottomBoundary());
										asChange = true;
									}
								}
							}
							// Case
							// --------[-----------]------------
							// -----------[------]--------------
							// Become
							// --------[--------]---------------
							// -----------[------]---------------
							if (rightDomain.getUpperBoundary() <= leftDomain.getUpperBoundary() && rightDomain.getUpperBoundary() >= leftDomain.getBottomBoundary())
							{
								boolean shouldReduce = true;
								for (Domain checkRightDomain : right.getDomains())
								{
									if (rightDomain != checkRightDomain && checkRightDomain.getUpperBoundary() >= rightDomain.getBottomBoundary())
									{
										shouldReduce = false;
									}
								}
								if (shouldReduce)
								{
									if (leftDomain.getUpperBoundary() != rightDomain.getUpperBoundary())
									{
										leftDomain.setUpperBoundary(rightDomain.getUpperBoundary());
										asChange = true;
									}
								}
							}
						}
						if (importantChange)
						{
							break;
						}
					}
					if (!isPossible)
					{
						left.getDomains().remove(leftDomain);
						asChange = true;
						importantChange = true;
					}
					if (importantChange)
					{
						break;
					}
				}
			}
			for (Domain rightDomain : right.getDomains())
			{
				if (!rightDomainUsed.containsKey(rightDomain))
				{
					right.getDomains().remove(rightDomain);
					asChange = true;
					importantChange = true;
					break;
				}
			}
			if (importantChange)
			{
				Operator.INFERIOR.reduceDomains(left, right);
			}
			if (!left.isInstantiated() && !right.isInstantiated())
			{
				if (!left.getDomains().isEmpty())
				{
					try
					{
						left.setValue(left.getDomains().get(0).getBottomBoundary());
						left.setInstantiated(false);
					} catch (VariableValueException e)
					{
						e.printStackTrace();
					}
				}
				if (!right.getDomains().isEmpty())
				{
					try
					{
						right.setValue(right.getDomains().get(0).getBottomBoundary());
						right.setInstantiated(false);
					} catch (VariableValueException e)
					{
						e.printStackTrace();
					}
				}
			}
			return asChange;
		}
	},
	SUPERIOREQUAL(">=")
	{
		public boolean execute(Variable left, Variable right)
		{
			return Operator.INFERIOREQUAL.execute(right, left);
		}

		public boolean checkIfPossible(Variable left, Variable right)
		{
			return Operator.INFERIOREQUAL.checkIfPossible(right, left);
		}

		public boolean reduceDomains(Variable left, Variable right) throws DomainBoundaryException
		{
			return Operator.INFERIOREQUAL.reduceDomains(right, left);
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

	public boolean reduceDomains(Variable left, Variable right) throws DomainBoundaryException
	{
		return false;
	}

	public static Operator getOperatorByString(String operatorRepresentation)
	{
		for (Operator operator : Operator.values())
		{
			if (operator.getRepresentation().equals(operatorRepresentation))
			{
				return operator;
			}
		}
		return null;
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
