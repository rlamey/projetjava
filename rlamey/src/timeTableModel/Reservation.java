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
 * Cette classe a �t� cr��e pour d�finir les r�servations et toutes les op�rations
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
	 * Il regroupe tous les param�tres qui seront utilis�s dans cette classe
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
	 * La fonction getFrom qui permet de r�cup�rer l'heure de d�but
	 * de la r�servation
	 * 
	 * @return From
	 */
	public Date getFrom() {
		return this.From;
	}
	
	/**
	 * La fonction getTo qui permet de r�cup�rer l'heure de fin
	 * de la r�servation
	 * 
	 * @return To
	 */
	public Date getTo() {
		return this.To;
	}

	/**
	 * La fonction getIDreser qui permet de r�cup�rer l'identifiant
	 * de la r�servation
	 * 
	 * @return IDreser
	 */
	public int getIDreser() {
		return this.IDreser;
	}
	
	/**
	 * La Fonction getRoom qui permet de r�cup�rer l'identifiant de la salle
	 * qui a �t� reserv�e 
	 * 
	 * @return Room
	 */
	public int getRoom() {
		return this.Room;
	}
	
	/**
	 * 
	 * La fonction getTeacher qui permet de r�cup�rer le login de la personne
	 * qui a r�serv� la salle
	 * 
	 * @return Teacher
	 */
	public String getTeacher() {
		return this.Teacher;
	}

	/**
	 * La fonction getIDTimeTable qui permet de r�cup�rer le num�ro de la TimeTable
	 * 
	 * @return IDTimeTable
	 */
	public int getIDTimeTable() {
		return this.IDTimeTable;
	}
}
