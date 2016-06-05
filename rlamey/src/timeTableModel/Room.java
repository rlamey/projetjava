/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package timeTableModel;

/**
 * Description of Room.
 * 
 * @author rlamey
 * 
 * Cette classe a été créée pour définir les fonctions de base pour créer et administrer les salles
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
 * La fonction getSpace qui permet de récupérer la taille d'une salle
 * pour les besoins des fonctions plus avancées
 * @return Space
 */
	public int getSpace() {
		return this.Space;
	}
	/*
	 * La fonction getIDroom qui permet de récupérer le numéro identifiant une salle
	 * pour les besoins des fonctions plus avancées
	 * @return IDroom
	 */
	public int getIDroom() {
		return this.IDroom;
	}
}