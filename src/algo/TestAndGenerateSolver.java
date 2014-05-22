package algo;

import java.util.ArrayList;

import model.Constraint;
import model.Variable;

public class TestAndGenerateSolver extends AbstractSolver
{
	public TestAndGenerateSolver(ArrayList<Variable> variableList, ArrayList<Constraint> constraintList, String finalOutput)
	{
		super(variableList, constraintList, finalOutput);
	}

	public boolean problemCheck()
	{
		boolean noProbleme = true;
		for (Constraint constraint : this.getConstraintList())
		{
			if (constraint.getLeftVariable().isInstantiated() && constraint.getRightVariable().isInstantiated())
			{
				noProbleme = constraint.checkInstance() && noProbleme;
			}
			if (!noProbleme)
			{
				break;
			}
		}
		return noProbleme;
	}

	@Override
	public Object clone() throws CloneNotSupportedException
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
			if (left == null)
			{
				left = (Variable) constraint.getLeftVariable().clone();
			}
			if (right == null)
			{
				right = (Variable) constraint.getRightVariable().clone();
			}
			newConstraintList.add(new Constraint(left, right, constraint.getOperator()));
		}
		return new TestAndGenerateSolver(newVariableList, newConstraintList, new String(this.getFinalOutput()));
	}
}
