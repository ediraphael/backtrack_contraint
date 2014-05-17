package modele;

import java.util.ArrayList;

import Exception.DomainBoundaryException;
import Exception.VariableValueException;

public class Solver
{
	private ArrayList<Variable> variableList;
	private ArrayList<Constraint> constraintList;
	private ArrayList<Variable> solutionList;
	private String finalOutput;

	public Solver(ArrayList<Variable> variableList, ArrayList<Constraint> constraintList, String finalOutput)
	{
		this.variableList = variableList;
		this.constraintList = constraintList;
		this.solutionList = new ArrayList<Variable>();
		this.finalOutput = finalOutput;
	}

	public void doArcConsistency() throws DomainBoundaryException
	{
		boolean oneTrue = true;
		while (oneTrue)
		{
			oneTrue = false;
			for (Constraint constraint : constraintList)
			{
				oneTrue = constraint.reduceDomainVariables() || oneTrue;
			}
		}
	}

	public ArrayList<Variable> doTestAndGenerate()
	{
		for (Variable variable : variableList)
		{
			if (!variable.isInstantiated())
			{
				if (solutionList.size() == 0)
				{
					for (Domain domain : variable.getDomains())
					{
						if (solutionList.size() == 0)
						{
							for (int i = domain.getBottomBoundary(); i <= domain.getUpperBoundary(); i++)
							{
								if (solutionList.size() == 0)
								{
									try
									{
										variable.setValue(i);
									} catch (VariableValueException e1)
									{
										e1.printStackTrace();
									}
									boolean noProbleme = true;
									for (Constraint constraint : constraintList)
									{
										if (constraint.getLeftVariable().isInstantiated() && constraint.getRightVariable().isInstantiated())
										{
											noProbleme = constraint.checkInstance() && noProbleme;
										}
									}
									if (noProbleme)
									{
										try
										{
											Solver newSolver = (Solver) this.clone();
											solutionList = newSolver.doTestAndGenerate();
											if (solutionList.size() == 0)
											{
												boolean allInstantiated = true;
												for (Variable finalVariable : variableList)
												{
													allInstantiated = allInstantiated && finalVariable.isInstantiated();
												}
												if (allInstantiated)
												{
													boolean allConstraintGood = true;
													for (Constraint finalConstraint : constraintList)
													{
														allConstraintGood = allConstraintGood && finalConstraint.checkInstance();
													}
													if (allConstraintGood)
													{
														ArrayList<Variable> solution = new ArrayList<Variable>();
														for (Variable variable2 : variableList)
														{
															solution.add((Variable) variable2.clone());
														}
														solutionList = solution;
														return solutionList;
													}
												}
											}
											else
											{
												return solutionList;
											}
										} catch (CloneNotSupportedException e)
										{
											e.printStackTrace();
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return solutionList;
	}

	public String generateFinalOutput()
	{
		String outputRepresentation = "";
		String elements[] = finalOutput.split("\\+");
		String textPattern = "\\s*\".*\"\\s*";
		for (String element : elements)
		{
			if (element.matches(textPattern))
			{
				element = element.replaceAll("\"", "");
				outputRepresentation += element;
			}
			// Should be a variable
			else
			{
				for (Variable variable : this.solutionList)
				{
					if (element.trim().equals(variable.getName()))
					{
						outputRepresentation += variable.getValue();
					}
				}
			}
		}
		return outputRepresentation;
	}

	public ArrayList<Variable> getVariableList()
	{
		return variableList;
	}

	public void setVariableList(ArrayList<Variable> variableList)
	{
		this.variableList = variableList;
	}

	public ArrayList<Constraint> getConstraintList()
	{
		return constraintList;
	}

	public void setConstraintList(ArrayList<Constraint> constraintList)
	{
		this.constraintList = constraintList;
	}

	public String getFinalOutput()
	{
		return finalOutput;
	}

	public void setFinalOutput(String finalOutput)
	{
		this.finalOutput = finalOutput;
	}

	public ArrayList<Variable> getSolutionList()
	{
		return solutionList;
	}

	public void setSolutionList(ArrayList<Variable> solutionList)
	{
		this.solutionList = solutionList;
	}

	@Override
	public String toString()
	{
		String representation = "Variable(s) :\n";
		for (Variable variable : variableList)
		{
			representation += "\t" + variable + "\n";
		}
		representation += "Constraint(s) :\n";
		for (Constraint constraint : constraintList)
		{
			representation += "\t" + constraint + "\n";
		}
		return representation;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		ArrayList<Variable> newVariableList = new ArrayList<Variable>();
		for (Variable variable : variableList)
		{
			newVariableList.add((Variable) variable.clone());
		}
		ArrayList<Constraint> newConstraintList = new ArrayList<Constraint>();
		for (Constraint constraint : constraintList)
		{
			Variable left = null;
			Variable right = null;
			for (Variable variable : newVariableList)
			{
				if (constraint.getLeftVariable().equals(variable))
				{
					left = variable;
				} else if (constraint.getRightVariable().equals(variable))
				{
					right = variable;
				}
			}
			newConstraintList.add(new Constraint(left, right, constraint.getOperator()));
		}
		return new Solver(newVariableList, newConstraintList, new String(finalOutput));
	}
}
