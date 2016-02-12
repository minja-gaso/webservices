package org.sw.marketing.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.sw.marketing.data.form.Data.Form;

public class SkinReader
{
	public static String getSkinByUrl(Form form)
	{		
		InputStream urlInputStream = null;
		Document document = null;
		try
		{
			urlInputStream = new URL(form.getSkinUrl()).openStream();
			document = Jsoup.parse(urlInputStream, "CP1252", form.getSkinUrl());
			String url = document.baseUri();
			boolean isHttp = false;
			boolean isHttps = false;
			if (url.substring(0, 5).equals("http:"))
			{
				isHttp = true;
			}
			else if (url.substring(0, 6).equals("https:"))
			{
				isHttps = true;
			}
			int position = -1;
			if (url.indexOf("/") > -1)
			{
				position = url.indexOf("/");
			}
			String domain = url.substring(7);
			if (domain.indexOf("/") > -1)
			{
				position = domain.indexOf("/");
				domain = domain.substring(0, position);
			}

			if (isHttp)
			{
				domain = "http://" + domain + "/";
			}
			else if (isHttps)
			{
				domain = "https://" + domain + "/";
			}

			for(Element hrefElement : document.select("a, link"))
			{
				hrefElement.attr("href", hrefElement.absUrl("href"));
			}
			for(Element srcElement : document.select("button, img, input, script"))
			{
				srcElement.attr("src", srcElement.absUrl("src"));
			}
			for(Element style : document.select("style"))
			{
				String inlineText = style.html().trim();
				inlineText = inlineText.replace("(/resources", "(" + domain + "/resources");
				style.html(inlineText);
			}
			
			if(document.select("title").size() > 0)
			{
				document.select("title").first().html("{TITLE}");
			}
			
			document.select(form.getSkinSelector()).html("{CONTENT}");
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return document.html();
	}
	
	public static String getSkinByUrl(String urlStr, String selector)
	{		
		InputStream urlInputStream = null;
		Document document = null;
		try
		{
			urlInputStream = new URL(urlStr).openStream();
			document = Jsoup.parse(urlInputStream, "CP1252", urlStr);
			String url = document.baseUri();
			boolean isHttp = false;
			boolean isHttps = false;
			if (url.substring(0, 5).equals("http:"))
			{
				isHttp = true;
			}
			else if (url.substring(0, 6).equals("https:"))
			{
				isHttps = true;
			}
			int position = -1;
			if (url.indexOf("/") > -1)
			{
				position = url.indexOf("/");
			}
			String domain = url.substring(7);
			if (domain.indexOf("/") > -1)
			{
				position = domain.indexOf("/");
				domain = domain.substring(0, position);
			}

			if (isHttp)
			{
				domain = "http://" + domain + "/";
			}
			else if (isHttps)
			{
				domain = "https://" + domain + "/";
			}

			for(Element hrefElement : document.select("a, link"))
			{
				hrefElement.attr("href", hrefElement.absUrl("href"));
			}
			for(Element srcElement : document.select("button, img, input, script"))
			{
				srcElement.attr("src", srcElement.absUrl("src"));
			}
			for(Element style : document.select("style"))
			{
				String inlineText = style.html().trim();
				inlineText = inlineText.replace("(/resources", "(" + domain + "/resources");
				style.html(inlineText);
			}
			
			if(document.select("title").size() > 0)
			{
				document.select("title").first().html("{TITLE}");
			}
			
			document.select(selector).html("{CONTENT}");
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return document.html();
	}
}
