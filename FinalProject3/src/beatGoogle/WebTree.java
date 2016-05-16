package beatGoogle;

import java.io.IOException;
import java.util.ArrayList;

public class WebTree {
	public WebNode Root;
	public double scores[] = new double[30];
	public ArrayList<WebTree> arrangedWebforest = new ArrayList<WebTree>();
	public ArrayList<WebTree> Webforest = new ArrayList<WebTree>();
	int size = 0;

	public WebTree(WebNode root) {
		super();
		Root = root;
	}

	public void postOrderNodeScore(String Keywords)throws IOException {
		Root.calcNodeScore(Keywords);
	}

	public void eulerTourPrint() {
		Root.printwebpage(0);
	}

	public void WebForest(WebTree tree) {
		Webforest.add(tree);		
		size = size +1;
		System.out.println("tree added");
		System.out.println(Webforest.size());
		System.out.println(size);
	}

	public ArrayList<WebTree> ArrangeTrees(ArrayList<WebTree> Webforest) {
		for (int j = 0; j < Webforest.size(); j++) {
			System.out.println("tree before");
			WebTree trees = Webforest.get(j);
			System.out.println("tree got");
			double treescore = trees.Root.nodeScore;
			System.out.println("tree scored");
			scores[j] = treescore;
		}
		Bobblesort(scores);
		for (int i = 0; i < scores.length; i++) {
			for (int j = 0; j < Webforest.size(); j++) {
				WebTree tree2 = Webforest.get(j);
				double tree2score = tree2.Root.nodeScore;
				if (tree2score == scores[i]) {
					arrangedWebforest.add(tree2);
					Webforest.remove(j);
					break;
				}
			}
		}
		return arrangedWebforest;
	}

	public void Bobblesort(double[] treescore) {
		double[] a = treescore;
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < a.length - i - 1; j++) {
				if (a[j + 1] > a[j]) {
					double temp = a[j + 1];
					a[j + 1] = a[j];
					a[j] = temp;
				}
			}
		}
	}

}
