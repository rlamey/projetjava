/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package timeTableModel;
import java.util.Date;
import java.util.Hashtable;
import java.util.Set;

/**
 * Description de la classe DailyTimeTable.
 * 
 * Cette classe a �t� cr��e pour d�finir les fonctions qui permettent de g�rer l'emploi du temps
 * en r�cup�rant les donn�es de la classe r�servation. On peut ainsi cr�er les emplois du temps
 * qui seront stock�s dans la classe TimeTableDB.
 * 
 *@author rlamey
 */
public class DailyTimeTable {

	private Hashtable<Integer, Reservation> reservations = new Hashtable<Integer, Reservation>();
	private int ID = 0;
	
	/**
	 *Le constructeur de DailyTimeTable
	 *
	 *@param ID
	 *@param reservations
	 */	
	public DailyTimeTable(int ID, Hashtable<Integer, Reservation> reservations) {
		this.ID=ID;
		this.reservations=reservations;
	}
	
	/**
	 * La fonction getIDroom permet de retourner le num�ro de la salle qui a �t� r�serv�e
	 * par rapport au num�ro de r�servation.
	 * Si jamais il n'y a pas de r�servation � ce num�ro, il retourne -1, car on ne peut pas retourner null pour un int.
	 * 
	 * @param IDreser 
	 * @return le num�ro de la salle
	 */
	public int getIDroom (int IDreser){
	Reservation reser = this.reservations.get(IDreser);
	if (reser!=null){
		return reser.getRoom();
		}
	else{
		return -1; // null ne fonctionne pas puisque l'on return un int, on return donc une valeur impossible qui est -1
		}
	}
	
	/**
	 * La fonction getIDteacher permet de retourner le login du professeur qui a la r�servation
	 * par rapport au num�ro de la r�servation
	 * Si jamais la r�servation n'existe pas, on return null
	 * 
	 * @param IDreser
	 * @return le login du professeur
	 */
	public String getIDteacher (int IDreser){
	Reservation reser = this.reservations.get(IDreser);
	if (reser!=null){
		return reser.getTeacher();
		}
	else {
		return null;
		}
	}
	
	/**
	 * La fonction getDateFrom permet de retourner la date et l'heure de d�but de la r�servation
	 * par rapport au num�ro de la r�servation
	 * Si jamais la r�servation n'existe pas, on return null
	 * 
	 * @param IDreser
	 * @return Date et heure de d�but de la r�servation
	 */	
	public Date getDateFrom(int IDreser){
	Reservation reser = this.reservations.get(IDreser);
	if (reser!=null){
		return reser.getFrom();
		}
	else {
		return null;
		}
	}

	/**
	 * La fonction getDateTo permet de retourner la date et l'heure de fin de la r�servation
	 * par rapport au num�ro de la r�servation
	 * Si jamais la r�servation n'existe pas, on return null
	 * 
	 * @param IDreser
	 * @return Date et heure de fin de la r�servation
	 */
	public Date getDateTo(int IDreser){
	Reservation reser = this.reservations.get(IDreser);
	if (reser!=null){
		return reser.getTo();
		}
	else {
		return null;
		}
	}
	
	/**
	 * Fonction permettant de r�cup�rer l'identifiant de la r�servation
	 * 
	 * @return le num�ro de la r�servation
	 */
	public Set<Integer> getIDreser() {
		return this.reservations.keySet();
	}
	
	/**
	 * fonction permettant de r�cup�rer l'identifiant de l'emploi du temps
	 * 
	 * @return l'identifiant de l'emploi du temps
	 */
	public int getIDTimeTable() {
		return this.ID;
	}
	
	/**
	 * fonction permettant de d�finir la valeur de l'identifiant de l'emploi du temps
	 * 
	 * @param IDTimeTable
	 */
	public void setTimeTableId (int IDTimeTable) {
		this.ID = IDTimeTable;
	}
	
	/**
	 * Fonction permettant de r�cup�rer un num�ro de r�servation qui n'est pas utilis�
	 *  
	 * @return le numero de la derniere reservation disponible
	 */
	public int GetIDLastReser(){
		int LastReser=-1; // on initialise la variable qui va stocker le numero de la derni�re reservation.
		// on ne la met pas � 0 car le numero de la premi�re r�servation est 0 et donc on ne pourrait pas boucler, du coup on le met � une valeur n�gative pour �viter tout conflit.
		Set<Integer> IDreser=this.reservations.keySet();
		for( Integer IDreser0 : IDreser){
			if (IDreser0>LastReser){
				LastReser=IDreser0;
			}
		}
		return LastReser;
	}
	
	/**
	 * fonction permettant de r�cup�rer les heures de d�but et de fin de chaque r�servations
	 * d�j� existantes dans la table.
	 * @param FromDate
	 * @param ToDate
	 */
	public void getDateReser(Hashtable<Integer, Date> FromDate, Hashtable<Integer, Date> ToDate){
		Set<Integer> IDreser= this.getIDreser();
	
		for (int reservation : IDreser){
			FromDate.put(reservation, this.reservations.get(reservations).getFrom());
			ToDate.put(reservation, this.reservations.get(reservations).getTo());
		}	
	}
	
	/**
	 * Fonction permettant d'ajouter une r�servation � l'emploi du temps
	 * return true pour dire que tout a bien �t� ajout�
	 * 
	 * @param IDreser
	 * @param IDTimeTable
	 * @param From
	 * @param To
	 * @param IDTeacher
	 * @param IDRoom
	 * @return true 
	 */
	public boolean AddRes(int IDreser, int IDTimeTable, Date From, Date To, String IDTeacher, int IDRoom) {
		this.reservations.put(IDreser,new Reservation(IDreser, From, To, IDRoom, IDTimeTable, IDTeacher));		
		return true;
	}
	
	/**
	 * Fonction permettant de supprimer une r�servation sachant le num�ro de la r�servation
	 * rerturn true pour dire que tout a bien �t� supprim�
	 * @param IDreser
	 * @return true
	 */
	public boolean DeleteRes(int IDreser) {
		this.reservations.remove(IDreser);
		return true;
	}
}
