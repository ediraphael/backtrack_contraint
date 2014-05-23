package model;

import java.util.ArrayList;
import java.util.Random;

import exception.DomainBoundaryException;

public class Generator
{
	private int nbVariable;
	private int nbConstraint;
	private int minValue;
	private int maxValue;
	private ArrayList<Variable> variableList;
	private ArrayList<Constraint> constraintList;

	public Generator(int nbVariable, int nbConstraint, int minValue, int maxValue)
	{
		this.nbVariable = nbVariable > 2 ? nbVariable : 2;
		this.nbConstraint = nbConstraint;
		this.minValue = minValue;
		this.maxValue = maxValue >= minValue ? maxValue : 2 * minValue - maxValue;
		this.variableList = new ArrayList<Variable>();
		this.constraintList = new ArrayList<Constraint>();
	}

	public String generate()
	{
		String content = "";
		boolean retry = false;
		do
		{
			this.variableList = new ArrayList<Variable>();
			this.constraintList = new ArrayList<Constraint>();
			content = "";
			Random random = new Random();
			for (int i = 0; i < nbVariable; i++)
			{
				int randomNumber = random.nextInt(maxValue - minValue + 1) + minValue;
				int bottomBoundary = (randomNumber < (maxValue - minValue) / 2) ? randomNumber : randomNumber - ((maxValue - minValue) / 2);
				int upperBoundary = (randomNumber > (maxValue - minValue) / 2) ? randomNumber : randomNumber + ((maxValue - minValue) / 2);
				content += "var " + bottomBoundary + ".." + upperBoundary + ": var" + i + ";\n";
				try
				{
					variableList.add(new Variable("var" + i, new Domain(bottomBoundary, upperBoundary)));
				} catch (DomainBoundaryException e)
				{
					System.err.println("Error generation variable : " + e.getMessage());
				}
			}

			for (int i = 0; i < nbConstraint && !retry; i++)
			{
				int leftVariable;
				int rightVariable;
				int operator;
				boolean noNullDomain = true;
				int cptContraintRetry = -1;
				do
				{
					cptContraintRetry++;
					boolean incoherence = false;
					do
					{
						leftVariable = random.nextInt(variableList.size());
						rightVariable = random.nextInt(variableList.size());
						while (rightVariable == leftVariable)
						{
							rightVariable = random.nextInt(variableList.size());
						}
						operator = random.nextInt(Operator.values().length);
						if (Operator.SUPERIOR == Operator.values()[operator])
						{
							incoherence = incoherence || constraintList.contains(new Constraint(variableList.get(leftVariable), variableList.get(rightVariable), Operator.INFERIOR));
							incoherence = incoherence || constraintList.contains(new Constraint(variableList.get(rightVariable), variableList.get(leftVariable), Operator.values()[operator]));
						} else if (Operator.EQUAL == Operator.values()[operator])
						{
							incoherence = incoherence || constraintList.contains(new Constraint(variableList.get(leftVariable), variableList.get(rightVariable), Operator.NOTEQUAL));
						} else if (Operator.NOTEQUAL == Operator.values()[operator])
						{
							incoherence = incoherence || constraintList.contains(new Constraint(variableList.get(leftVariable), variableList.get(rightVariable), Operator.EQUAL));
						}
					} while (incoherence);

					try
					{
						ArrayList<Variable> newVariableList = new ArrayList<Variable>();
						for (Variable variable : this.getVariableList())
						{
							newVariableList.add((Variable) variable.clone());
						}
						ArrayList<Constraint> newConstraintList = new ArrayList<Constraint>();
						for (Constraint constraint : this.getConstraintList())
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
						newConstraintList.add(new Constraint(newVariableList.get(leftVariable), newVariableList.get(rightVariable), Operator.values()[operator]));

						boolean oneTrue = true;
						while (oneTrue)
						{
							oneTrue = false;
							for (Constraint constraint : newConstraintList)
							{
								oneTrue = constraint.reduceDomainVariables() || oneTrue;
							}
						}
						for (Variable variable : newVariableList)
						{
							noNullDomain = noNullDomain && !variable.getDomains().isEmpty();
						}
					} catch (Exception e)
					{
						// TODO: handle exception
					}
					if (cptContraintRetry > 100)
					{
						retry = true;
					}
				} while (!noNullDomain && !retry);

				content += "constraint " + variableList.get(leftVariable).getName() + " " + Operator.values()[operator] + " " + variableList.get(rightVariable).getName() + ";\n";
				constraintList.add(new Constraint(variableList.get(leftVariable), variableList.get(rightVariable), Operator.values()[operator]));
			}
			if (!retry)
			{
				content += "output [";
				for (Variable variable : variableList)
				{
					content += "\n\"\\n" + variable.getName() + "=\",show(" + variable.getName() + "),";
				}
				content += "];";
			}
		} while (retry);
		return content;
	}

	public static void main(String[] args)
	{
		Generator generator = new Generator(20, 10, 0, 40);
		System.out.println(generator.generate());
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

	public int getNbVariable()
	{
		return nbVariable;
	}

	public void setNbVariable(int nbVariable)
	{
		this.nbVariable = nbVariable;
	}

	public int getNbConstraint()
	{
		return nbConstraint;
	}

	public void setNbConstraint(int nbConstraint)
	{
		this.nbConstraint = nbConstraint;
	}

	public int getMinValue()
	{
		return minValue;
	}

	public void setMinValue(int minValue)
	{
		this.minValue = minValue;
	}

	public int getMaxValue()
	{
		return maxValue;
	}

	public void setMaxValue(int maxValue)
	{
		this.maxValue = maxValue;
	}

}
