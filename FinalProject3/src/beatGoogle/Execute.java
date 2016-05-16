package beatGoogle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Execute {
	ArrayList<WebTree> arrtree = new ArrayList<WebTree>();
	
	public void  Do (String keyword) throws IOException {
		WebTree trees = new WebTree(null);
		String userkeyword = keyword;
		//ArrayList<Keyword> Keywords = new ArrayList<Keyword>(null);
		//String queryStr = userkeyword;
		//Keywords.add(new Keyword(queryStr, 1.0));

		GoogleSearch gSearch = new GoogleSearch(userkeyword);
		HashMap<String, String> results = gSearch.getResults();
		for (Entry<String, String> entry : results.entrySet()) {
			String title = entry.getKey();
			String url = entry.getValue();
			KeywordCounter counter = new KeywordCounter(url);
			String realurl = counter.ChangeUrl(url);
			WebNode rootNode = new WebNode(new WebPage(title, realurl));
			HashMap<String, String> children = counter.ChildSearch();
			if (children != null) {
				int numberofchild = 1;
				for (Entry<String, String> entrychild : children.entrySet()) {
					String childtitle = entrychild.getKey();
					String childurl = entrychild.getValue();
					if (childurl != null) {
						KeywordCounter kc = new KeywordCounter(childurl);
						String realchildurl = kc.ChangeUrl(childurl);
						if (numberofchild <= 3) {
							if (childtitle != null && realchildurl != null) {
								rootNode.appendChild(new WebNode(new WebPage(childtitle, realchildurl)));
								numberofchild = numberofchild + 1;
							}
						} else {
							break;
						}
					}

				}
			}
			WebTree tree = new WebTree(rootNode);
			tree.postOrderNodeScore(userkeyword);
			tree.eulerTourPrint();
			trees.WebForest(tree);
		}

		arrtree = trees.ArrangeTrees(trees.Webforest);
		for (int j = 0; j < arrtree.size(); j++) {
			WebTree treeb = arrtree.get(j);
			treeb.eulerTourPrint();
		}
	}
}
