package devoir2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

public class Ftc {

	private String path;
	private static ArrayList<Data> database=new ArrayList<Data>();
	public ArrayList<Data> getDatabase() {
		return database;
	}

	public void setDatabase(ArrayList<Data> database) {
		Ftc.database = database;
	}


	private double minsup;

	private int coutDoc;
	public int getCoutDoc() {
		return coutDoc;
	}

	public void setCoutDoc(int coutDoc) {
		this.coutDoc = coutDoc;
	}
	private ArrayList<String> filelist;

	public Ftc(String path, double minsup){
		filelist=new ArrayList<String>();

		this.setPath(path);
		folder = new File(path);
		this.setCoutDoc(filelist.size());
		this.setMinsup(minsup);

	}
	final File folder;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public double getMinsup() {
		return minsup;
	}
	public void setMinsup(double minsup) {
		this.minsup = minsup;
	}
	public  ArrayList<Data> listFilesForFolder() {
		return listFilesForFolder(folder);
	}


	public  ArrayList<Data> listFilesForFolder(final File folder) {
		Data data=null;
		int countDoc=0;
		for (final File fileEntry : folder.listFiles()) {
			String text=readDoc(fileEntry);
			String[] terms = text.split("[\\W]");
			ArrayList<String> list = new ArrayList<String>();
			for(String termToLowCase :terms){
				list.add(termToLowCase.toLowerCase());
			}
			ArrayList<String> newterms=new ArrayList<String>();
			int i=0,j;
			int size = list.size()-1;
			while(i<size){
				j=0;
				String string = list.get(i);
				while(i<size && !string.equals("body")){
					i++;
					string = list.get(i);
				}
				i++;if(i>size)break;
				string = list.get(i);
				while (i<size && !string.equals("body"))
				{
					if( !(string.length()==0)){
						j++;
						newterms.add(list.get(i));}
					i++;
					string = list.get(i);}
				newterms.trimToSize();
				int k=2;
				while(k>0){
					int size2 = newterms.size();
					newterms.remove(size2-k);
					k--;}
				i++;
				newterms.trimToSize();
				countDoc++;
				data=new Data(countDoc,newterms);
				database.add(data);
				newterms = new ArrayList<String>();}
		}
		setDatabase(database);
		System.out.println(database.size());
		return database;
	}

	public Set<Integer> listDoc(){
		Set<Integer> numb=new HashSet<Integer>();
		for (int i=1;i<=database.size();i++){
			numb.add(i);
		}
		return numb;
	}


	public static String readDoc(File fileEntry) {
		String text = "";
		int read, N = 1024 * 1024;
		char[] buffer = new char[N];

		try {
			FileReader fr = new FileReader(fileEntry);
			BufferedReader br = new BufferedReader(fr);

			while(true) {
				read = br.read(buffer, 0, N);
				text += new String(buffer, 0, read);

				if(read < N) {
					break;
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		return text;
	}
	//	static <T> ArrayList<T> arrayToList(final T[] array) {
	//		final ArrayList<T> l = new ArrayList<T>(array.length);
	//
	//		for (final T s : array) {
	//			l.add(s);
	//		}
	//		return (l);
	//	}

	public  Set<Integer> covDocuments(Set<String> list){
		Set<Integer> documents=new HashSet<Integer>();
		int i=0;
		if((list.size()==0)){
			return documents;
		}
		for(Data data:database){
			for(String term:list){
				{
					if(data.getTerms().contains(term)){
						i++;
					}
				}
			}
			if (i>=list.size()){
				documents.add(data.getDocNumb());

			}
			i=0;
		}
		return documents;}



	public  Set<Integer> covDocuments(String list){
		Set<Integer> documents=new HashSet<Integer>();
		for(Data data:database){
			if(data.getTerms().contains(list)){
				documents.add(data.getDocNumb());
			}
		}
		return documents;

	}

	public HashSet<FrequentItem> findFrequentItem(){
		HashSet<FrequentItem> frequentItemSet=new HashSet<FrequentItem>();

		for (Data data:getDatabase()){
			for(String term:data.getTerms()){
				if(term.equals("the") ||term.equals("be")||term.equals("to")
						||term.equals("said")||term.equals("and")||term.equals("of")
						||term.equals("for")||term.equals("a")||term.equals("in")
						||term.equals("will")||term.equals("it")||term.equals("its")
						||term.equals("s")||term.equals("is")||term.equals("an")||term.equals("at")||term.equals("was")
						||term.equals("as")||term.equals("2")||term.equals("3")||term.equals("4")||term.equals("5")
						||term.equals("by")||term.equals("with")||term.equals("by")||term.equals("from")||term.equals("has")
						||term.equals("also")||term.equals("1")||term.equals("have")||term.equals("after")||term.equals("not")
						||term.equals("about")||term.equals("that")||term.equals("up")||term.equals("per")||term.equals("which")
						||term.equals("i")||term.equals("may")||term.equals("one")||term.equals("co")||term.equals("any")
						||term.equals("dlrs")||term.equals("on")||term.equals("inc")||term.equals("pct")||term.equals("or")
						||term.equals("title")||term.equals("orgs")||term.equals("newid")||term.equals("oldid")||term.equals("would")
						||term.equals("lt")||term.equals("this")||term.equals("cts")||term.equals("net")||term.equals("would")
						||term.equals("22")||term.equals("reuters")||term.equals("31")||term.equals("net")||term.equals("would")
						)

				{continue;}
				boolean found=false;
				Set<String> newSet=new HashSet<String>();
				newSet.add(term);
				double d = minsup*database.size();
				int size = covDocuments(newSet).size();
				if (size>=d){

					for(FrequentItem element:frequentItemSet)
					{
						if(element.getFrequentTermSet().contains(term)){
							found=true;break;}
					}
					if(!found){
						FrequentItem frequent=new FrequentItem(term);
						frequent.setDocuments(covDocuments(newSet));
						frequentItemSet.add(frequent);
					}
					found=false;
				}
			}
		}
		return frequentItemSet;
	}
	public ArrayList<Integer> overlapStandardDoc(Set<FrequentItem> frequentTerms){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=1;i<=database.size();i++){
			list.add(0);
		}
		for(FrequentItem element:frequentTerms){
			for(Integer listeDoc:element.getDocuments()){
				list.set(listeDoc-1, list.get(listeDoc-1)+1);  
			}
		}
		return list;
	}

	public static void main(String[] args) {
		Fenetre fenetre=new Fenetre();
		String path = fenetre.chooser.getSelectedFile().getAbsolutePath();

		try {
			Thread.sleep(9000);
		} catch(InterruptedException e) {
		}


		//fenetre.b.addActionListener(boutonListener);
		JFormattedTextField jtf = fenetre.jtf;
		fenetre.jtf = new JFormattedTextField(NumberFormat.getIntegerInstance());
		System.out.println(jtf.getText());
		String text = jtf.getText();
		Boolean choiceOverlap=fenetre.overlap;
		//String text = "0.4";
		double value = Double.parseDouble(text);
		//String path = "C:\\Users\\Houssem\\Downloads\\mini_newsgroups.tar";
		Ftc ftc=new Ftc(path,value/100);



		ftc.listFilesForFolder();
		Set<Set<String>> findFrequentItem = new HashSet<Set<String>>();
		HashSet<FrequentItem> findFrequentItem2 = ftc.findFrequentItem();

		for(FrequentItem element :findFrequentItem2)
		{
			findFrequentItem.add(element.getFrequentTermSet());
		}
		System.out.println(findFrequentItem);
		Set<Set<String>> w=new HashSet<Set<String>>();
		Set<Set<String>> data=new HashSet<Set<String>>();
		Set<String> u = new HashSet<String>();
		double d = ftc.getMinsup()*ftc.getDatabase().size();
		for(Set<String> lista :findFrequentItem){
			for(String element:lista){
				u.add(element);
			}}
		if(!fenetre.ftcbool){
			//findFrequentItem2=new0 HashSet<FrequentItem>();
			for(int i=1;i<=findFrequentItem.size();i++){
				try {
					w=new HashSet<Set<String>>();
					combinaison<String> csgen=new combinaison<String>(u, i);
					w.addAll(csgen.getCombinaisons());
					//csgen.lister();
					HashSet<Set<String>> w1=new HashSet<Set<String>>();
					w1.addAll(w);
					for(Set<String> membre:w){
						int size = ftc.covDocuments(membre).size();
						if (!(size>=d)){
							w1.remove(membre);
						}
					}
					data.addAll(w1);
					Set<Integer> listDoc=ftc.listDoc();
					for(Set<String> element :w1){
						for(String term: element){
							for(FrequentItem frequent: findFrequentItem2){
								if(frequent.getFrequentTermSet().contains(term)){
									listDoc.removeAll(frequent.getDocuments());
								}
							}
						}
						FrequentItem newfrequent=new FrequentItem(element);
						newfrequent.setDocuments(listDoc);
						findFrequentItem2.add(newfrequent);
					}
				} catch (Exception e) {
				}
			}
			//		for(Set<String> element:data){
			//			System.out.println(element);
			//		}
			HashSet<FrequentItem> findFrequentItem3=new HashSet<FrequentItem>();
			findFrequentItem3.addAll(findFrequentItem2);
			for(FrequentItem element:findFrequentItem2){
				if(element.getDocuments().size()<d){
					findFrequentItem3.remove(element);
				}
			}
			//Object[] array = findFrequentItem3.toArray();
			Set<FrequentItem> selectedTermSets= new HashSet<FrequentItem>();
			int n=database.size();
			HashSet<FrequentItem> remainingTermSets=new HashSet<FrequentItem>();
			remainingTermSets.addAll(findFrequentItem3);
			ArrayList<Integer> overlapStandardDoc = ftc.overlapStandardDoc(findFrequentItem3);
			ArrayList<Overlap> listOverlap=new ArrayList<Overlap>();
			double minOverlap=Integer.MAX_VALUE;
			int size = 0;
			Overlap bestCondidate=null;
			int oldsize=-1;
			while(size!= n-2&&oldsize!=size){
				for(FrequentItem set:remainingTermSets){
					Overlap overlap=new Overlap(set, overlapStandardDoc);
					listOverlap.add(overlap);
				}
				if(!choiceOverlap){

					for(Overlap element:listOverlap){

						if(element.overlapEntropie<minOverlap){

							minOverlap=element.overlapEntropie;
							bestCondidate=element;}
						minOverlap=Integer.MAX_VALUE;}
					System.out.println("entropie");}
				else {for(Overlap element:listOverlap){
					if(element.overlap<minOverlap){
						minOverlap=element.overlap;

						bestCondidate=element;}
					minOverlap=Integer.MAX_VALUE;}
				System.out.println("standard");}

				selectedTermSets.add(bestCondidate.cluster);
				remainingTermSets.remove(bestCondidate.cluster);

				//System.out.println(bestCondidate.cluster.frequentTermSet);
				//System.out.println(bestCondidate.cluster.documents);
				for(Integer docToRemove:bestCondidate.cluster.documents){
					database.remove(docToRemove);
					for(FrequentItem set:remainingTermSets){
						set.documents.remove(docToRemove);
					}
				}
				oldsize=size;
				size = overlapsize(ftc, selectedTermSets);
				//System.out.println(size);
			}
			int numbDoc=0;
			String res=new String();

			ArrayList<JLabel> listafficher=new ArrayList<JLabel>();
			for(FrequentItem element:selectedTermSets){
				System.out.println(element.frequentTermSet);
				System.out.println(element.documents);
				numbDoc=numbDoc+element.documents.size();
				res=element.frequentTermSet+" est un ensemble de termes fréquent qui se trouve dans les documents "+element.documents+"\n";
				JLabel resultat=new JLabel(res);
				listafficher.add(resultat);
			}
			fenetre.add(listafficher);
			System.out.println(numbDoc);
		}
		else{
			for(int i=1;i<=findFrequentItem.size();i++){
				try {
					w=new HashSet<Set<String>>();
					combinaison<String> csgen=new combinaison<String>(u, i);
					w.addAll(csgen.getCombinaisons());
					//csgen.lister();
					HashSet<Set<String>> w1=new HashSet<Set<String>>();
					w1.addAll(w);
					for(Set<String> membre:w){
						int size = ftc.covDocuments(membre).size();
						if (!(size>=d)){
							w1.remove(membre);
						}
					}
					data.addAll(w1);
					Set<Integer> listDoc=ftc.listDoc();
					for(Set<String> element :w1){
						for(String term: element){
							for(FrequentItem frequent: findFrequentItem2){
								if(frequent.getFrequentTermSet().contains(term)){
									listDoc.removeAll(frequent.getDocuments());
								}
							}
						}
						FrequentItem newfrequent=new FrequentItem(element);
						newfrequent.setDocuments(listDoc);
						findFrequentItem2.add(newfrequent);
					}
				} catch (Exception e) {
				}
				Set<FrequentItem> selectedTermSets= new HashSet<FrequentItem>();
				int n=database.size();
				HashSet<FrequentItem> remainingTermSets=new HashSet<FrequentItem>();
				remainingTermSets.addAll(findFrequentItem2);
				ArrayList<Integer> overlapStandardDoc = ftc.overlapStandardDoc(findFrequentItem2);
				ArrayList<Overlap> listOverlap=new ArrayList<Overlap>();
				double minOverlap=Integer.MAX_VALUE;
				int size = 0;
				Overlap bestCondidate=null;
				int oldsize=-1;
				while(size!= n-2&&oldsize!=size){
					for(FrequentItem set:remainingTermSets){
						Overlap overlap=new Overlap(set, overlapStandardDoc);
						listOverlap.add(overlap);
					}
					if(!choiceOverlap){

						for(Overlap element:listOverlap){

							if(element.overlapEntropie<minOverlap){

								minOverlap=element.overlapEntropie;
								bestCondidate=element;}
							minOverlap=Integer.MAX_VALUE;}
						System.out.println("entropie");}
					else {for(Overlap element:listOverlap){
						if(element.overlap<minOverlap){
							minOverlap=element.overlap;

							bestCondidate=element;}
						minOverlap=Integer.MAX_VALUE;}
					System.out.println("standard");}

					selectedTermSets.add(bestCondidate.cluster);
					remainingTermSets.remove(bestCondidate.cluster);

					//System.out.println(bestCondidate.cluster.frequentTermSet);
					//System.out.println(bestCondidate.cluster.documents);
					for(Integer docToRemove:bestCondidate.cluster.documents){
						database.remove(docToRemove);
						for(FrequentItem set:remainingTermSets){
							set.documents.remove(docToRemove);
						}
					}
					oldsize=size;
					size = overlapsize(ftc, selectedTermSets);
					//System.out.println(size);
				}
				HashSet<FrequentItem> findFrequentItem3=new HashSet<FrequentItem>();
				findFrequentItem3.addAll(findFrequentItem2);
				for(FrequentItem element:findFrequentItem2){
					if(element.getDocuments().size()<d){
						findFrequentItem3.remove(element);
					}
				}
				//Object[] array = findFrequentItem3.toArray();

				int numbDoc=0;
				String res=new String();

				ArrayList<JLabel> listafficher=new ArrayList<JLabel>();
				for(FrequentItem element:selectedTermSets){
					System.out.println(element.frequentTermSet);
					System.out.println(element.documents);
					numbDoc=numbDoc+element.documents.size();
					res=element.frequentTermSet+" est un ensemble de termes fréquent qui se trouve dans les documents "+element.documents+"\n";
					JLabel resultat=new JLabel(res);
					listafficher.add(resultat);
				}
				fenetre.add(listafficher);
				System.out.println(numbDoc);
			}
		}
		//		for(Set<String> element:data){
		//			System.out.println(element);
		//		}

	}
	private static int overlapsize(Ftc ftc, Set<FrequentItem> selectedTermSets) {
		Set<Integer> covtotal=new HashSet<Integer>();
		for(FrequentItem frequent:selectedTermSets)
		{
			Set<Integer> covDocuments = ftc.covDocuments(frequent.frequentTermSet);
			for(int doc:covDocuments){
				if(!(covtotal.contains(doc))){
					covtotal.add(doc);
				}
			}
		}
		int size=covtotal.size();
		return size;
	}
}
