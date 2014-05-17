package modele;

import java.util.ArrayList;

import Exception.DomainBoundaryException;

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
				for (Variable variable : this.variableList)
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
