/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package timeTableModel;

/**
 * Description of Room.
 * 
 * @author rlamey
 * 
 * Cette classe a �t� cr��e pour d�finir les fonctions de base pour cr�er et administrer les salles
 */
public class Room {
	private int Space = 0;
	private int IDroom = 0;
	/**
	 * Constructeur de Room
	 * 
	 * @param Space
	 * @param IDroom
	 */
	public Room(int Space, int IDroom) {
		this.IDroom=IDroom;
		this.Space=Space;
		}

/*
 * La fonction getSpace qui permet de r�cup�rer la taille d'une salle
 * pour les besoins des fonctions plus avanc�es
 * @return Space
 */
	public int getSpace() {
		return this.Space;
	}
	/*
	 * La fonction getIDroom qui permet de r�cup�rer le num�ro identifiant une salle
	 * pour les besoins des fonctions plus avanc�es
	 * @return IDroom
	 */
	public int getIDroom() {
		return this.IDroom;
	}
}