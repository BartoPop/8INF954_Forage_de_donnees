package devoir2;

import java.util.ArrayList;

public class Overlap {
	FrequentItem cluster;
	int overlap;
	double overlapEntropie;
	public Overlap(FrequentItem frequentItem,ArrayList<Integer> overlapStandardDoc){
		int somme=0;
		double sommeEntropie=0;
		for(Integer i:frequentItem.getDocuments()){
			Integer integer = overlapStandardDoc.get(i-1);
			somme=somme+integer-1;
			sommeEntropie=(sommeEntropie+(1/(double)integer-1)*Math.log((double)integer));
		}
		
		cluster=frequentItem;
		overlap=somme/cluster.frequentTermSet.size();
		overlapEntropie=sommeEntropie;
		
	}
	
}
