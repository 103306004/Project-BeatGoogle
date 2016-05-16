package beatGoogle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleSearch {
	private String queryStr;
	private String content;

	public GoogleSearch(String queryStr) {
		this.queryStr = queryStr;
	}
	 
	private String fetchContent() throws IOException
	{
		URL url = new URL("https://www.google.com.tw/search?q=" + queryStr + "&num=10&oe=utf8");
		URLConnection conn = url.openConnection();
		conn.setRequestProperty("user-agent", "Chrome/7.0.517.44");
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in,"utf-8"));
		
		String reVal = "";	
		String line = null; 
		
		while((line = br.readLine()) != null){
			reVal += line + "\n";
		}
		
		return reVal;
	}
	
	public HashMap<String, String> getResults() throws IOException {
		HashMap<String, String> retVal = new HashMap<String, String>();	
		if (content == null) {
			content = fetchContent();
		}	
		Document doc = Jsoup.parse(content);	
		Elements divGs = doc.select("div.g");
		
		for (Element divG : divGs) {
			try {
				Element h3R = divG.select("h3.r").get(0);
				Element aTag = h3R.select("a").get(0);
				String title = aTag.text();
				String url = aTag.attr("href");
				retVal.put(title, url);
			} catch (IndexOutOfBoundsException ex) {
				//System.out.println("Parent Unconnectable");
			}
		}
		return retVal;
	}
}
