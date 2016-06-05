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
 * Cette classe a été créée pour définir les fonctions qui permettent de gérer l'emploi du temps
 * en récupérant les données de la classe réservation. On peut ainsi créer les emplois du temps
 * qui seront stockés dans la classe TimeTableDB.
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
	 * La fonction getIDroom permet de retourner le numéro de la salle qui a été réservée
	 * par rapport au numéro de réservation.
	 * Si jamais il n'y a pas de réservation à ce numéro, il retourne -1, car on ne peut pas retourner null pour un int.
	 * 
	 * @param IDreser 
	 * @return le numéro de la salle
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
	 * La fonction getIDteacher permet de retourner le login du professeur qui a la réservation
	 * par rapport au numéro de la réservation
	 * Si jamais la réservation n'existe pas, on return null
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
	 * La fonction getDateFrom permet de retourner la date et l'heure de début de la réservation
	 * par rapport au numéro de la réservation
	 * Si jamais la réservation n'existe pas, on return null
	 * 
	 * @param IDreser
	 * @return Date et heure de début de la réservation
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
	 * La fonction getDateTo permet de retourner la date et l'heure de fin de la réservation
	 * par rapport au numéro de la réservation
	 * Si jamais la réservation n'existe pas, on return null
	 * 
	 * @param IDreser
	 * @return Date et heure de fin de la réservation
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
	 * Fonction permettant de récupérer l'identifiant de la réservation
	 * 
	 * @return le numéro de la réservation
	 */
	public Set<Integer> getIDreser() {
		return this.reservations.keySet();
	}
	
	/**
	 * fonction permettant de récupérer l'identifiant de l'emploi du temps
	 * 
	 * @return l'identifiant de l'emploi du temps
	 */
	public int getIDTimeTable() {
		return this.ID;
	}
	
	/**
	 * fonction permettant de définir la valeur de l'identifiant de l'emploi du temps
	 * 
	 * @param IDTimeTable
	 */
	public void setTimeTableId (int IDTimeTable) {
		this.ID = IDTimeTable;
	}
	
	/**
	 * Fonction permettant de récupérer un numéro de réservation qui n'est pas utilisé
	 *  
	 * @return le numero de la derniere reservation disponible
	 */
	public int GetIDLastReser(){
		int LastReser=-1; // on initialise la variable qui va stocker le numero de la dernière reservation.
		// on ne la met pas à 0 car le numero de la première réservation est 0 et donc on ne pourrait pas boucler, du coup on le met à une valeur négative pour éviter tout conflit.
		Set<Integer> IDreser=this.reservations.keySet();
		for( Integer IDreser0 : IDreser){
			if (IDreser0>LastReser){
				LastReser=IDreser0;
			}
		}
		return LastReser;
	}
	
	/**
	 * fonction permettant de récupérer les heures de début et de fin de chaque réservations
	 * déjà existantes dans la table.
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
	 * Fonction permettant d'ajouter une réservation à l'emploi du temps
	 * return true pour dire que tout a bien été ajouté
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
	 * Fonction permettant de supprimer une réservation sachant le numéro de la réservation
	 * rerturn true pour dire que tout a bien été supprimé
	 * @param IDreser
	 * @return true
	 */
	public boolean DeleteRes(int IDreser) {
		this.reservations.remove(IDreser);
		return true;
	}
}
