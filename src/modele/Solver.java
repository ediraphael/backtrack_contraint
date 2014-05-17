package modele;

import java.util.ArrayList;

public class Solver
{
	private ArrayList<Variable> variableList;
	private ArrayList<Constraint> constraintList;
	private String finalOutput;

	public Solver(ArrayList<Variable> variableList, ArrayList<Constraint> constraintList, String finalOutput)
	{
		this.variableList = variableList;
		this.constraintList = constraintList;
		this.finalOutput = finalOutput;
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
