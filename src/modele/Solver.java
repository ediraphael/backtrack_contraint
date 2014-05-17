package modele;

import java.util.ArrayList;

public class Solver
{
	private ArrayList<Variable> variableList;
	private ArrayList<Constraint> constraintList;

	public Solver(ArrayList<Variable> variableList, ArrayList<Constraint> constraintList)
	{
		this.variableList = variableList;
		this.constraintList = constraintList;
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

	@Override
	public String toString()
	{
		String representation = "Variable(s) :\n";
		for (Variable variable : variableList)
		{
			representation += "\t" + variable + "\n";
		}
		representation+="Constraint(s) :\n";
		for (Constraint constraint : constraintList)
		{
			representation += "\t" + constraint + "\n";
		}
		return representation;
	}
}
