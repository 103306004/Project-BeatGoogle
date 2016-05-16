package beatGoogle;

import java.io.IOException;

public class WebPage {
	
	public String url;
	public String name;
	public KeywordCounter counter;  
	public double score; 
	
	public WebPage(String name, String url) {
		super();
		this.url = url;
		this.name = name;
		this.counter = new KeywordCounter(url);
	}
                        
	public double calcScore(String keywords) throws IOException{
		score = 0;
		//for(Keyword k: keywords){ 
			score +=counter.countKeyword(keywords);
			System.out.println(score);
		//}
		return score;
	}
}
