package org.sw.marketing.transformation;

import java.io.InputStream;
import java.net.URL;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

public class XsltResolver implements URIResolver
{
	private String baseUrl;
	
	public String getBaseUrl()
	{
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl)
	{
		this.baseUrl = baseUrl;
	}

	@Override
	public Source resolve(String href, String base) throws TransformerException
	{
		try
		{
			URL url = new URL("http://localhost:8080/xsl/toolbox/forms/" + href);
			InputStream inputStream = url.openStream();
			
			return new StreamSource(inputStream);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
}
