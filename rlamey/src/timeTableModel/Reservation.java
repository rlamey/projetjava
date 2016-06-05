/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package timeTableModel;
import java.util.Date;
/**
 * Description of Reservation.
 * 
 * @author rlamey
 * 
 * Cette classe a été créée pour définir les réservations et toutes les opérations
 * et manipulationsque l'on peut faire avec
 */
public class Reservation {
	private int IDreser = 0; // Numero de reservation
	private Date From = null; // Heure et date de debut de la reservation
	private Date To = null; // heure et date de fin de la reservation
	private int Room = 0; // Numero de la salle
	private int IDTimeTable = 0; // Identifiant de l'emploi du temps
	private String Teacher = ""; // Nom de la personne qui reserve
	
	/**
	 * Constructeur de la classe Reservation
	 * 
	 * Il regroupe tous les paramètres qui seront utilisés dans cette classe
	 * 
	 * @param IDreser
	 * @param From
	 * @param To
	 * @param Room
	 * @param IDTimeTable
	 * @param Teacher
	 */
	public Reservation(int IDreser, Date From, Date To, int Room, int IDTimeTable, String Teacher) {
		this.IDreser=IDreser;
		this.From=From;
		this.To=To;
		this.Room=Room;
		this.IDTimeTable=IDTimeTable;
		this.Teacher=Teacher;
	}

	/**
	 * La fonction getFrom qui permet de récupérer l'heure de début
	 * de la réservation
	 * 
	 * @return From
	 */
	public Date getFrom() {
		return this.From;
	}
	
	/**
	 * La fonction getTo qui permet de récupérer l'heure de fin
	 * de la réservation
	 * 
	 * @return To
	 */
	public Date getTo() {
		return this.To;
	}

	/**
	 * La fonction getIDreser qui permet de récupérer l'identifiant
	 * de la réservation
	 * 
	 * @return IDreser
	 */
	public int getIDreser() {
		return this.IDreser;
	}
	
	/**
	 * La Fonction getRoom qui permet de récupérer l'identifiant de la salle
	 * qui a été reservée 
	 * 
	 * @return Room
	 */
	public int getRoom() {
		return this.Room;
	}
	
	/**
	 * 
	 * La fonction getTeacher qui permet de récupérer le login de la personne
	 * qui a réservé la salle
	 * 
	 * @return Teacher
	 */
	public String getTeacher() {
		return this.Teacher;
	}

	/**
	 * La fonction getIDTimeTable qui permet de récupérer le numéro de la TimeTable
	 * 
	 * @return IDTimeTable
	 */
	public int getIDTimeTable() {
		return this.IDTimeTable;
	}
}
