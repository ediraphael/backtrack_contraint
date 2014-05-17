package modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Parser
{
	public void loadFile(String path)
	{
		try
		{
			byte[] encoded = Files.readAllBytes(Paths.get(path));
			String contentFile = new String(encoded);
			System.out.println(contentFile);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void load(String fileContent)
	{

	}

	public static void main(String[] args)
	{
		Parser parser = new Parser();
		parser.loadFile("basic.mzn");
	}
}
