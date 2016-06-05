package timeTableController;

import java.util.Date;
import java.util.Hashtable;

import timeTableModel.TimeTableDB;
/**
 * Cette classe est le contrôleur d'emplois du temps que vous devez implémenter. 
 * Elle contient un attribut correspondant à la base de données d'emplois du temps que vous allez créer.
 * Elle contient toutes les fonctions de l'interface ITimeTableController que vous devez implémenter.
 * 
 * @author Jose Mennesson (Mettre à jour)
 * @version 04/2016 (Mettre à jour)
 * 
 */

public class TimeTableController implements ITimeTableController{

	/**
	 * Contient une instance de base de données d'emplois du temps
	 * 
	 */
	TimeTableDB tTDB;
	/**
	 * Constructeur de controleur d'emplois du temps créant la base de données d'emplois du temps
	 * 
	 * @param tTfile
	 * 		Fichier XML contenant la base de données d'emplois du temps
	 */
	public TimeTableController(String tTfile) {
		TimeTableDB tTDB=new TimeTableDB(tTfile);
		this.tTDB=tTDB;
	}

	@Override
	public String getTeacherLogin(int timeTableId, int bookId) {
		String TeacherLogin;
		TeacherLogin= this.tTDB.getIDTeacher(timeTableId, bookId);
		return TeacherLogin;
	}

	@Override
	public String[] roomsIdToString() {
		String roomsIdToString[];
		roomsIdToString= this.tTDB.StringIDRoom();
		return roomsIdToString;
	}

	@Override
	public String[] roomsToString() {
		String roomsToString[];
		roomsToString= this.tTDB.StringRoom();
		return roomsToString;
	}

	@Override
	public String[] timeTablesIDToString() {
		String timeTablesIDToString[];
		timeTablesIDToString=this.tTDB.StringSchedule();
		return timeTablesIDToString;
	}

	@Override
	public String[] booksIdToString(int timeTableId) {
		String booksIdToString[];
		booksIdToString=this.tTDB.StringReser(timeTableId);
		return booksIdToString;
	}

	@Override
	public boolean addRoom(int roomId, int capacity) {
		boolean addRoom;
		addRoom=this.tTDB.AddRoom(roomId, capacity);
		return addRoom;
	}

	@Override
	public boolean removeRoom(int roomId) {
		boolean removeRoom;
		removeRoom=this.tTDB.DelRoom(roomId);
		return removeRoom;
	}

	@Override
	public int getRoom(int timeTableId, int bookId) {
	int getRoom;
	getRoom=this.tTDB.getIDroom(timeTableId, bookId);
	return getRoom;
	}

	@Override
	public boolean addTimeTable(int timeTableId) {
	boolean addTimeTable;
	addTimeTable=this.tTDB.AddSchedule(timeTableId);
	return addTimeTable;
	}

	@Override
	public boolean removeTimeTable(int timeTableId) {
	boolean removeTimeTable;
	removeTimeTable=this.tTDB.DelSchedule(timeTableId);
	return removeTimeTable;
	}

	@Override
	public boolean addBooking(int timeTableId, int bookingId, String login, Date dateBegin, Date dateEnd, int roomId) {
	boolean addBooking;
	addBooking=this.tTDB.AddReser(bookingId, timeTableId, dateBegin, dateEnd, login, roomId);
	return addBooking;
	}

	@Override
	public void getBookingsDate(int timeTableId, Hashtable<Integer, Date> dateBegin, Hashtable<Integer, Date> dateEnd) {
			this.tTDB.getReserDate(timeTableId, dateBegin, dateEnd);
	}

	@Override
	public boolean removeBook(int timeTableId, int bookId) {
	boolean removeBook;
	removeBook=this.tTDB.DelReser(timeTableId, bookId);
	return removeBook;
	}

	@Override
	public int getBookingsMaxId(int timeTableId) {
	int getBookingMaxId;
	getBookingMaxId=this.tTDB.getIDLastReser(timeTableId);
	return getBookingMaxId;
	}

	@Override
	public boolean saveDB() {
	boolean saveDB;
	saveDB=this.tTDB.SaveDataBase();
	return saveDB;
	}

	@Override
	public boolean loadDB() {
	boolean loadDB;
	loadDB=this.tTDB.LoadDatabase();
	return loadDB;
	}
	
	

}
