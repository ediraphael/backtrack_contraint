package algo;

import java.util.ArrayList;

import model.Constraint;
import model.Domain;
import model.Variable;

import exception.DomainBoundaryException;
import exception.VariableValueException;


public abstract class AbstractSolver
{
	private ArrayList<Variable> variableList;
	private ArrayList<Constraint> constraintList;
	private ArrayList<Variable> solutionList;
	private String finalOutput;

	public static enum Heuristic
	{
		DEFAULT,
		MINDOMAIN
		{
			public ArrayList<Variable> apply(ArrayList<Variable> variableList)
			{
				ArrayList<Variable> newVariableList = new ArrayList<Variable>();
				while (!variableList.isEmpty())
				{
					Variable minVariable = null;
					int minVariableDomains = Integer.MAX_VALUE;
					for (Variable variable : variableList)
					{
						if (variable.calculateDomainsPossility() < minVariableDomains)
						{
							minVariable = variable;
							minVariableDomains = variable.calculateDomainsPossility();
						}
					}
					variableList.remove(minVariable);
					newVariableList.add(minVariable);
				}
				return newVariableList;
			}
		};
		public ArrayList<Variable> apply(ArrayList<Variable> variableList)
		{
			return variableList;
		}
	}

	public AbstractSolver(ArrayList<Variable> variableList, ArrayList<Constraint> constraintList, String finalOutput)
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

	public abstract boolean problemCheck();

	public ArrayList<Variable> launch() throws VariableValueException
	{
		return launch(Heuristic.DEFAULT);
	}

	public ArrayList<Variable> launch(Heuristic heuristic) throws VariableValueException
	{
		variableList = heuristic.apply(variableList);
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
									variable.setValue(i);
									try
									{
										AbstractSolver newSolver = (AbstractSolver) this.clone();
										if (newSolver.problemCheck())
										{
											solutionList = newSolver.launch(heuristic);
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
														System.out.println("Solution");
														ArrayList<Variable> solution = new ArrayList<Variable>();
														for (Variable variable2 : variableList)
														{
															solution.add((Variable) variable2.clone());
														}
														solutionList = solution;
														return solutionList;
													}
												}
											} else
											{
												return solutionList;
											}
										}
									} catch (CloneNotSupportedException e)
									{
										System.err.println("Error during copy : " + e.getMessage());
									}
								}
							}
						}
					}
				}
				break;
			}
		}
		return solutionList;
	}

	public String generateFinalOutput()
	{
		String outputRepresentation = "";
		String elements[] = finalOutput.split("\\+");
		String textPattern = "[\\n|\\t|\\s]*\"[\\n|\\t|\\s]*.*[\\n|\\t|\\s]*\"[\\n|\\t|\\s]*";
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
}
