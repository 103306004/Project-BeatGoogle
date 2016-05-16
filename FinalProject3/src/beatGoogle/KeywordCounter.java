package beatGoogle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class KeywordCounter {
	public String urlStr;
	private String content;

	public KeywordCounter(String url) {
		this.urlStr = url;
	}

	private String fetchContent() throws IOException {
		try {
			URL url = new URL(ChangeUrl(urlStr));
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("user-agent", "Chrome/7.0.517.44");
			InputStream in = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader((in),"UTF-8"));
			String retval = "";
			String line = null;
			while ((line = br.readLine()) != null) {
				retval = retval + line + "\n";
			}
			return retval;
		} catch (IOException e) {
			return "Not Found";
		}
	}

	public int countKeyword(String keyword) throws IOException {
		if (content == null) {
			content = fetchContent();
		}
		String key = keyword;
		String img ="<img ";
		String online = "½u¤W¬Ý";
		int retval = 0;
		int fromidx = 0;
		int found = -1;

		while ((found = content.indexOf(key, fromidx)) != -1 || (found = content.indexOf(img, fromidx)) != -1|| (found = content.indexOf(key, fromidx))!=-1) {
			while ((found = content.indexOf(key, fromidx)) != -1) {
				retval++;
				fromidx = found + keyword.length();	
			}
			while ((found = content.indexOf(img, fromidx)) != -1) {
				retval++;
				fromidx = found + img.length();
			}
			while ((found = content.indexOf(online, fromidx)) != -1) {
				retval++;
				fromidx = found + online.length();
			}
		}
		return retval;
	}

	public HashMap<String, String> ChildSearch() throws IOException {
		HashMap<String, String> retVal1 = new HashMap<String, String>();
		try {
			if (content == null) {
				content = fetchContent();
			}
			Document doc = Jsoup.parse(content);
			Elements divGs = doc.select("*");

			for (Element divG : divGs) {
				try {
					//Element h3R = divG.select("*");
					Element aTag = divG.select("a").get(0);
					String title = aTag.text();
					String url = aTag.attr("href");
					retVal1.put(title, url);
				} catch (IndexOutOfBoundsException ex) {
					//System.out.println("False");
					String title =null;
					String url =null;
					retVal1.put(title, url);
				}
			}
			return retVal1;
		} catch (IOException e) {
			return retVal1 = null;
		}
	}

	public String ChangeUrl(String url) {
		if (url !=null) {
			String realurl = url;
			int http = realurl.indexOf("http");
			if (http != -1) {
				realurl = realurl.substring(http, url.length());
			}
			int sa = realurl.indexOf("&sa");
			if (sa != -1) {
				realurl = realurl.substring(0, sa);

			}
			int pa = realurl.indexOf("%");
			if (pa != -1) {
				realurl = realurl.substring(0, pa);
			}
			try {
				URL tryurl = new URL(realurl) ;
			} catch (MalformedURLException k) {
				//System.out.println("Unconnectable");
				realurl = null;
			}
			return realurl;
		}
		else {
			return url;
		}
	}

}
