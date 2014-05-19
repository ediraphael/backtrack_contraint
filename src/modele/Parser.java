package modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Exception.DomainBoundaryException;

public class Parser
{
	public Solver loadFile(String path)
	{
		try
		{
			byte[] encoded = Files.readAllBytes(Paths.get(path));
			String fileContent = new String(encoded);
			return load(fileContent);
		} catch (IOException e)
		{
			System.err.println("Error reading file : " + e.getMessage() + "  -> " + e.getCause());
		}
		return null;
	}

	public Solver load(String fileContent)
	{
		ArrayList<Variable> variableList = new ArrayList<Variable>();
		ArrayList<Constraint> constraintList = new ArrayList<Constraint>();
		String finalOutput = "";
		BufferedReader inputF = new BufferedReader(new StringReader(fileContent));
		String line = null;
		boolean isForOutput = false;
		try
		{
			while ((line = inputF.readLine()) != null)
			{
				String variablePaternString = "var\\s+([0-9]+)\\.\\.([0-9]+)(,([0-9]+)\\.\\.([0-9]+))*:\\s+([a-zA-Z]+);";
				Pattern variablePattern = Pattern.compile(variablePaternString);
				String constraintPaternString = "constraint\\s+([a-zA-Z]+|[0-9]+)\\s+(<|>|!=|==|<=|>=)\\s+([a-zA-Z]+|[0-9]+);";
				Pattern constraintPattern = Pattern.compile(constraintPaternString);
				String spacePatternString = "\\s*";
				String outputStartPatternString = "output\\s+\\[.*";
				String outputMiddlePatternString = ".*";
				String outputEndPatternString = ".*\\];";
				if (line.matches(variablePaternString))
				{
					Matcher matcher = variablePattern.matcher(line);
					matcher.find();
					try
					{
						Variable newVariable = new Variable(matcher.group(matcher.groupCount()), new Domain(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))));

						String variablePaternOtherDomainString = ",([0-9]+)\\.\\.([0-9]+)";
						Pattern variablePaternOtherDomain = Pattern.compile(variablePaternOtherDomainString);
						Matcher matcherOtherDomain = variablePaternOtherDomain.matcher(line);
						while (matcherOtherDomain.find())
						{
							newVariable.getDomains().add(new Domain(Integer.parseInt(matcherOtherDomain.group(1)), Integer.parseInt(matcherOtherDomain.group(2))));
						}
						variableList.add(newVariable);
					} catch (NumberFormatException | DomainBoundaryException e)
					{
						System.err.println("Erreur creation variable : " + e.getMessage());
					}
				} else if (line.matches(constraintPaternString))
				{
					Matcher matcher = constraintPattern.matcher(line);
					matcher.find();
					Operator operator = Operator.getOperatorByString(matcher.group(2));
					Variable leftVariable = null;
					Variable rightVariable = null;
					for (Variable variable : variableList)
					{
						if (matcher.group(1).equals(variable.getName()))
						{
							leftVariable = variable;
						} else if (matcher.group(3).equals(variable.getName()))
						{
							rightVariable = variable;
						}
					}
					if (operator != null && leftVariable != null && rightVariable != null)
					{
						constraintList.add(new Constraint(leftVariable, rightVariable, operator));
					} else
					{
						System.err.println("Error creation constraint : " + line);
					}
				} else if (line.matches(outputStartPatternString))
				{
					isForOutput = true;
					finalOutput += line;
				} else if (line.matches(outputEndPatternString))
				{
					isForOutput = false;
					finalOutput += line;
				} else if (line.matches(outputMiddlePatternString) && isForOutput)
				{
					finalOutput += line;
				} else if (!line.matches(spacePatternString))
				{
					System.err.println("Not regocnize : " + line);
				}
			}

			// Fixing output String
			String showOutputPatternString = "show\\(([a-zA-Z]+)\\)";
			finalOutput = finalOutput.replaceAll(showOutputPatternString, "$1");
			finalOutput = finalOutput.replaceAll(",(\\s+|\t+)", ",");
			finalOutput = finalOutput.replaceAll(",", "+");
			finalOutput = finalOutput.replaceAll("output|\\[|\\]|;", "");
			finalOutput = finalOutput.replaceAll("\\\\t", "\t");
			finalOutput = finalOutput.replaceAll("\\\\n", "\n");

			return new Solver(variableList, constraintList, finalOutput);
		} catch (IOException e)
		{
			System.err.println("Error parsing : " + e.getMessage());
		}
		return null;
	}

	public static void main(String[] args) throws CloneNotSupportedException
	{
		Parser parser = new Parser();
		Solver solver = parser.loadFile("basic.mzn");
		System.out.println(solver);
		try
		{
			solver.doArcConsistency();
		} catch (DomainBoundaryException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		solver.doTestAndGenerate();
		System.out.println(solver.generateFinalOutput());
	}
}
