package org.sw.marketing.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile
{
	public static String getSkin(String filePath)
	{
		String skinStr = "";
		
		try
		{
			File file = new File(filePath);
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			String line;
			while((line = reader.readLine()) != null)
			{
				skinStr += line + "\n";
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return skinStr;
	}
}
