package devoir2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;



/**
 *
 * @author Anthony BARRE
 * génére les combinaisons de P éléments dans N éléments
 */
public class combinaison<E> {

	//////////////////ATTRIBUT/////////////////////
	private LinkedList<E> univers;//liste pour pouvoir être triée
	private Set<Set<E>> combinaisons; //ensemble des combinaisons
	private int nbElements;


	//////////////////METHODE/////////////////////
	/**
	 * constructeur
	 * univ est l'univers (ensemble des éléments sur lequel on travaille)
	 * P est la taille d'une combinaison
	 */
	public combinaison(Set<E> univ, int P) throws Exception{
		super();
		//nCp existe si p<n et p>0
		if(univ!=null&P>0&P<=univ.size()){
			this.univers=new LinkedList<E>(univ);
			this.nbElements=P;
			HashSet<E> tmpCombin = new HashSet<E>();
			LinkedList<E> tmpUniv = (LinkedList<E>) univers.clone();
			combinaisons =new HashSet<Set<E>>();
			combinaisonRec(tmpCombin,tmpUniv,P);
		}else throw new Exception("Mauvais parametres...");        
	}
	public Set<Set<E>> getCombinaisons(){
		return combinaisons;
	}



	/**
	 * permet de générer les différentes combinaisons de manières récursives
	 * @param tmpCombin contient une combinaison en cours de fabrication
	 * @param tmpUniv contient les éléments de l'univers au cours de fabrication d'une combinaison
	 * @param nbreEltAAjoute nombre d'élément qu'il reste à ajouter dans la combinaison
	 */
	private void combinaisonRec(HashSet<E> combin, LinkedList<E> univ, int nbreEltAAjoute) {

		//cas de base : il n'y a plus assez d'élément dans l'univers pour créer une combinaison
		if(nbreEltAAjoute>univ.size()){
			return;//ARRET
		}
		else{
			//cas de base : on a trouvé une combinaison
			if(nbreEltAAjoute==0)
				combinaisons.add(combin);
			else{
				for(E elt: univ){
					//ensemble contenant tout les éléments susceptibles d'être ajoutés dans la combinaison courante
					LinkedList<E> univCopie = (LinkedList<E>) univ.clone();
					univCopie.removeAll(univ.subList(0,(univ.indexOf(elt)+1)));//enlève les éléments déjà explorés

					//ajout de l'élément dans la combinaison
					HashSet<E> combinCopie  = (HashSet<E>) combin.clone();
					combinCopie.add(elt);

					//récursivité
					combinaisonRec(combinCopie, univCopie , nbreEltAAjoute-1);
				}
			}
		}
	}//fin combinaisonRec

	/**
	 * @return nombre de combinaisons trouvées
	 */
	public int size(){
		return combinaisons.size();
	}

	/**
	 * liste les combinaisons
	 */
	protected void lister(){
		for(Set<E> elt:combinaisons)
			System.out.println(elt.toString());
	}
	
	
	

}