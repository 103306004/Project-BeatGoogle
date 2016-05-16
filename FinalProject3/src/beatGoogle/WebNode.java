package beatGoogle;

import java.io.IOException;
import java.util.ArrayList;

public class WebNode {

	public WebNode parent;
	public ArrayList<WebNode> children;
	public WebPage webPage;
	public double nodeScore;

	public WebNode(WebPage webPage) {
		super();
		this.webPage = webPage;
		this.children = new ArrayList<>();
	}

	public void appendChild(WebNode childNode) {
		childNode.parent = this;
		children.add(childNode);

	}

	public double calcNodeScore(String keywords) throws IOException {

		nodeScore = 0;
		for (WebNode child : children) {
			nodeScore += child.calcNodeScore(keywords);
			//nodeScore += webPage.calcScore(keywords);
		}
		nodeScore += webPage.calcScore(keywords);
		return nodeScore;
	}

	public void printwebpage(int n) {
		for (int i = 0; i < n; i++) {
			System.out.printf("\t");
		}

		System.out.print("(" +  nodeScore + " , " + webPage.name + " , " + webPage.url  );

		if (children.isEmpty()) {
			System.out.println(")");
		} else {
			System.out.printf(")");
			System.out.println();

			for (WebNode child : children) {
				child.printwebpage(n + 1);
			}

			if (n == 0) {
				
			} else {
				for (int i = 0; i < n; i++)
				System.out.printf("\t");
			}
		}
	}

}