package devoir2;

import java.util.HashSet;
import java.util.Set;

public class FrequentItem {
	public FrequentItem(Set<String> frequentTermSet) {
		super();
		this.frequentTermSet = frequentTermSet;
	}
	public FrequentItem(String frequentTermSet) {
		super();
		Set<String> listOntTerm=new HashSet<String>();
		listOntTerm.add(frequentTermSet);
		this.frequentTermSet = listOntTerm;
	}
	Set<String> frequentTermSet=new HashSet<String>();
	Set<Integer> documents=new  HashSet<Integer>();
	
	public boolean addDocument(Integer arg0) {
		return documents.add(arg0);
	}
	boolean addFrequent(String set){
		 return frequentTermSet.add(set);
	 }
	public Set<String> getFrequentTermSet() {
		return frequentTermSet;
	}
	public void setFrequentTermSet(Set<String> frequentTermSet) {
		this.frequentTermSet = frequentTermSet;
	}
	public Set<Integer> getDocuments() {
		return documents;
	}
	public void setDocuments(Set<Integer> documents) {
		this.documents = documents;
	}
	 
}
