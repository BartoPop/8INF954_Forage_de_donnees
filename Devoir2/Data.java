package devoir2;

import java.util.ArrayList;

public class Data {
	private int docNumb;
	 


	private ArrayList<String> terms;
	public int getDocNumb() {
		return docNumb;
	}
	public void setDocNumb(int docNumb) {
		this.docNumb = docNumb;
	}
	public ArrayList<String> getTerms() {
		return terms;
	}
	public void setTerms(ArrayList<String> terms) {
		this.terms = terms;
	}


	public Data(int docNumb, ArrayList<String> terms) {
		super();
		this.docNumb = docNumb;
		this.setTerms(terms);
	}

	
}

