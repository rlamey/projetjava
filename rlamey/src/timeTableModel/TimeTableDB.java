package timeTableModel;
import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.output.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.util.Set;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * Cette classe gère la base de données d'emplois du temps. Elle doit permettre de sauvegarder et charger les emplois du temps ainsi que les salles à  partir d'un fichier XML. 
 * La structure du fichier XML devra être la même que celle du fichier TimeTableDB.xml.
 * @see <a href="../../TimeTableDB.xml">TimeTableDB.xml</a> 
 * 
 * @author rlamey
 * @version 06/2016
 * 
 */



public class TimeTableDB {
	private Hashtable<Integer, DailyTimeTable> Schedule;
	private Hashtable<Integer, Room> Rooms;
	private String file;
	
	/**
	 * Constructeur de TimeTableDB. 
	 * définit les paramètres de la classe
	 * 
	 * @param file
	 */
public TimeTableDB(String file){
	this.setFile(file);
	this.Schedule= new Hashtable<Integer,DailyTimeTable>();
	this.Rooms= new Hashtable<Integer,Room>();
}

/**
 * cette fonctione permet de récupérer le fichier de l'emploi du temps
 *
 * @return file
 */
public String getFile() {
	return file;
}

/**
 * cette fonction permet de selectionner le fichier de l'emploi du temps
 * 
 * @param file
 */
public void setFile(String file) {
	this.file = file;
}

/**
 * Cette fonction permet d'ajouter des salles avec pour paramètres leur capacité et leur numéro
 * 
 * @param IDroom
 * @param capacity
 * @return un booléen pour confirmer la sauvegarde dans la base
 */
public boolean AddRoom(int IDroom, int capacity){
	this.Rooms.put(IDroom, new Room(IDroom, capacity));
	return this.SaveDataBase();
}

/**
 * Cette fonction permet de supprimer une salle grâce au numéro de la salle
 * 
 * @param IDroom
 * @return un booléen pour confirmer la sauvegarde de la suppression dans la base
 */
public boolean DelRoom(int IDroom){
	this.Rooms.remove(IDroom);
	return this.SaveDataBase();
}

/**
 * Cette fonction permet de récupérer le login du professeur à partir du 
 * numéro de la table et du numéro de la réservation
 * @param IDTimeTable
 * @param IDreser
 * @return le login du professeur ou null si jamais il ne le trouve pas
 */
public String getIDTeacher(int IDTimeTable, int IDreser){
	DailyTimeTable schedule= this.Schedule.get(IDTimeTable);
	if (schedule!=null){
		return schedule.getIDteacher(IDreser);
	}
	else{
		return null;
	}
}

/**
 * Cette fonction permet de récupérer le numéro de la salle à partir du
 * numéro de la table et du numéro de la réservation
 * @param IDTimeTable
 * @param IDreser
 * @return le numéro de la salle ou -1 si jamais il ne la trouve pas
 * On a été obligé de le faire retourner -1 au lieu de null car la fonction est un int.
 */
public int getIDroom(int IDTimeTable, int IDreser){
	DailyTimeTable schedule= this.Schedule.get(IDTimeTable);
	if (schedule!=null){
		return schedule.getIDroom(IDreser);
	}
	else{
		return -1;
	}
}

/**
 * Cette fonction permet d'ajouter un emploi du temps à la base
 * @param IDTimeTable
 * @return un booléen pour confirmer sa sauvegarde
 */
public boolean AddSchedule(int IDTimeTable){
	this.Schedule.put(IDTimeTable,new DailyTimeTable(IDTimeTable, new Hashtable<Integer, Reservation>()));
	return this.SaveDataBase();
}
	
/**
 * Cette fonction permet de supprimer un emploi du temps à la base
 * @param IDTimeTable
 * @return un booléen pour confirmer la sauvegarde de la suppression
 */
public boolean DelSchedule(int IDTimeTable){
	this.Schedule.remove(IDTimeTable);
	return this.SaveDataBase();
}

/**
 * Cette fonction permet d'ajouter une réservation à l'emploi du temps en fonction des différentes données rentrées.
 * @param IDreser
 * @param IDTimeTable
 * @param From
 * @param To
 * @param IDTeacher
 * @param IDRoom
 * @return un booléen pour confirmer la sauvegarde
 */
public boolean AddReser(int IDreser, int IDTimeTable, Date From, Date To, String IDTeacher, int IDRoom){
	boolean added= true;
	DailyTimeTable schedule = this.Schedule.get(IDTimeTable);
	if (schedule!= null){
		added= schedule.AddRes(IDreser, IDTimeTable, From, To, IDTeacher, IDRoom);
		}
	else {
		added= false;
	}
	return added && this.SaveDataBase();
}

/**
 * permet de supprimer une réservation à partir du numéro de l'emploi du temps et du numéro de la réservation
 * 
 * @param IDTimeTable
 * @param IDreser
 * @return un booléen pour confirmer la sauvegarde de la suppression
 */
public boolean DelReser(int IDTimeTable, int IDreser){
	boolean deleted = true;
	DailyTimeTable schedule = this.Schedule.get(IDTimeTable);
	if(schedule!=null){
		deleted= schedule.DeleteRes(IDreser);
	}
		else {
			deleted=false;
	}
	return deleted && this.SaveDataBase();
}

/**
 * Cette fonctio permet de récupérer le numéro de la derniere réservation disponible
 * 
 * @param IDTimeTable
 * @return le numéro de la derniere reservation disponible ou -1
 * on renvoit -1 dans le cas où il ne l'aurait pas trouvé à la place de null car un int ne peut pas renvoyer null
 */
public int getIDLastReser(int IDTimeTable){
	DailyTimeTable schedule = this.Schedule.get(IDTimeTable);
	if (schedule!=null){
		return schedule.GetIDLastReser();
	}
	else {
		return -1;
	}
}

/**
 * Cette fonction permet de récupérer le créneau horraire de la dernière réservation
 * 
 * @param IDTimeTable
 * @param From
 * @param To
 */
public void getReserDate(int IDTimeTable, Hashtable<Integer, Date> From, Hashtable<Integer, Date> To){
	DailyTimeTable schedule= this.Schedule.get(IDTimeTable);
	if (schedule!=null){
		schedule.getDateReser(From, To);
	}
}

/**
 * Cette fonction transforme la valeur en int de la salle en une chaine de caractères string
 * 
 * @return le numéro de la salle en string
 */
public String[] StringIDRoom() {
	int i=0;
	String IDRoomString[]= new String[999];
	Set<Integer> IDroom = this.Rooms.keySet();
	
	for (Integer ID : IDroom){
		IDRoomString[i]= ID.toString();
		i++;
	}
	return IDRoomString;
}

/**
 * Cette fonction permet de récupérer toutes les informations de la salle 
 * sous forme d'une chaine de caractères string
 * 
 * @return les informations de la salle en string
 */
public String[] StringRoom() {
	int i=0;
	String RoomString[]= new String[999];
	Set<Integer> room = this.Rooms.keySet();
	
	for (Integer ID : room){
		RoomString[i]=ID.toString() + "    " + String.valueOf(Rooms.get(ID).getSpace());
		i++;
	}
	return RoomString;
}

/**
 * Cette fonction permet de transformer un emploi du temps en chaine de caractères string
 * 
 * @return l'emploi du temps en string
 */
public String[] StringSchedule(){
	int i=0;
	String ScheduleString[]= new String[999];
	Set<Integer> IDschedule = this.Schedule.keySet();
	
	for (Integer ID : IDschedule){
		ScheduleString[i]=ID.toString();
		i++;
	}
	return ScheduleString;
}

/**
 * Cette fonction permet de transformer une réservation en chaine de caractères string
 * 
 * @param IDTimeTable
 * @return la réservation en type string
 */
public String[] StringReser(int IDTimeTable){
	int i=0;
	DailyTimeTable schedule = this.Schedule.get(IDTimeTable);
	String ReserString[] = new String[999];
	Set<Integer> IDreser= schedule.getIDreser();
	
	for (Integer ID : IDreser){
		ReserString[i]=ID.toString();
		i++;
	}
	return ReserString;
}

/**
 * Cette fonction permet de transformer la date en chaine de caractères string
 * @param date
 * @return une date sous la forme d'un string
 */
public static String StringDate(Date date){
	SimpleDateFormat StringDate = new SimpleDateFormat("DD/MM/YYYY hh:mm:ss");
	return StringDate.format(date);
}

/**
 * Cette fonction permet de tranformer la chaine de caractère en date
 * @param date
 * @return la date
 */
public static Date DateString(String date){
	Date Date;
	SimpleDateFormat DateString = new SimpleDateFormat("DD/MM/YYYY hh:mm:ss");
	try{
		Date = DateString.parse(date);
	} 
	catch(Exception e){
		Date=null;
	}
	return Date;
}

/**
 * Cette fonction permet de sauvegarder une réservation
 * 
 * @param schedule
 * @return un Element contenant les éléments que l'on veut sauvegarder
 */
private Element SaveReser(DailyTimeTable schedule){
	Element Resers, Reser, IDReser, IDTeacher, From, To, IDroom;
	Set<Integer> IDResers= schedule.getIDreser();
	Resers = new Element("resers");
	
	for(Integer ID : IDResers){
		Reser = new Element("reser");
		IDReser = new Element("idreser");
		IDTeacher = new Element("idteacher");
		From = new Element("from");
		To = new Element("to");
		IDroom = new Element("idroom");
		Reser.addContent(IDReser);
		Reser.addContent(IDTeacher);
		Reser.addContent(From);
		Reser.addContent(To);
		Reser.addContent(IDroom);
		Resers.addContent(Reser);
		IDReser.addContent(ID.toString());
		IDTeacher.addContent(schedule.getIDteacher(ID));
		From.addContent(StringDate(schedule.getDateFrom(ID)));
		To.addContent(StringDate(schedule.getDateTo(ID)));
		IDroom.addContent(String.valueOf(schedule.getIDroom(ID)));
	}
	return Resers;
}

/**
 * Cette fonction permet de sauvegarder l'emploi du temps
 * 
 * @return Un élément contenant les élément que l'on veut sauvegarder
 */
private Element SaveSchedule(){
	Element SavedSchedule = new Element("Schedules");
	Set<Integer> IDSchedule= this.Schedule.keySet();
	Element Schedule, IDschedule;
	
	for (Integer ID : IDSchedule){
		Schedule= new Element("schedule");
		IDschedule= new Element("idschedule");
		IDschedule.addContent(ID.toString());
		Schedule.addContent(Schedule);
		Schedule.addContent(this.SaveReser(this.Schedule.get(ID)));
		SavedSchedule.addContent(Schedule);
	}
	return SavedSchedule;
}
		
/**
 * Cette fonction permet de sauvegarder une nouvelle salle
 * 
 * @return un élément contenant les éléments que l'on veut sauvegarder
 */
private Element SaveRoom(){
	Element SavedRoom = new Element("Rooms");
	Set<Integer> IDRoom = this.Rooms.keySet();
	Element Room, IDroom, Capacity;
	
	for (Integer ID : IDRoom) {
		Room= new Element("room");
		IDroom= new Element("idroom");
		Capacity= new Element("capacity");
		IDroom.addContent(ID.toString());
		Capacity.addContent(String.valueOf(this.Rooms.get(ID).getSpace()));
		SavedRoom.addContent(Room);
		Room.addContent(IDroom);
		Room.addContent(Capacity);
	}
	return SavedRoom;		
}

/**
 * Cette fonction permet de sauvegarder toute la base de donnée
 * 
 * @return un document contenant la base de donnée
 */
public boolean SaveDataBase() {
	Element element = new Element ("TimeTableDB");
	org.jdom2.Document document = new Document(element);
	element.addContent(this.SaveRoom());
	element.addContent(this.SaveSchedule());
	try{
		XMLOutputter output= new XMLOutputter(Format.getPrettyFormat());
		output.output(document, new FileOutputStream(this.getFile()));
		return true;
	}catch (java.io.IOException e) {
		return false;
	}
}

/**
 *  Cette fonction permet de charger les salles de la base
 *  
 * @param Room
 * @return un booléen pour confirmer sa réussite ou non
 */
private boolean LoadRoom(Element Room){
	boolean loaded=true;
	int Capacity;
	int IDroom;
	java.util.List<Element> room;
	if (Room!=null){
		room=Room.getChildren("Room");
		
		for (Element ID : room){
			IDroom=Integer.parseInt(ID.getChildText("IDroom"));
			Capacity=Integer.parseInt(ID.getChildText("Capacity"));
			this.Rooms.put(IDroom, new Room(IDroom, Capacity));
		}
	}
	else { 
		loaded=false;
	}
	return loaded;
}

/**
 * Cette fonction permet de charger les emplois du temps
 * 
 * @param schedule
 * @return un booléen pour confirmer sa réussite ou non
 */
private boolean LoadSchedule(Element schedule){
	boolean loaded=true;
	int IDschedule;
	Hashtable<Integer, Reservation> resers;
	java.util.List<Element> schedules;
	
	if(schedule!=null){
		schedules=schedule.getChildren("schedule");
	for (Element ID : schedules) {
		IDschedule= Integer.parseInt(ID.getChildText("Group"));
		resers= new Hashtable<Integer, Reservation>();
		loaded=LoadReser(ID.getChild("Resers"), resers, IDschedule);
		this.Schedule.put(IDschedule, new DailyTimeTable(IDschedule,resers));
		}
	}
	else {
		loaded=false;
	}
	return loaded;
}

/**
 * Cette fonction permet de charger les réservations
 * 
 * @param Reser
 * @param resers
 * @param IDschedule
 * @return un booléen pour confirmer sa réussite
 */
private boolean LoadReser(Element Reser, Hashtable<Integer, Reservation>resers, int IDschedule){
	Date From;
	Date To;
	int IDreser;
	int IDroom;
	String reserID;
	boolean loaded=true;
	java.util.List<Element> Resers;
	if (Reser!=null){
		Resers=Reser.getChildren("reser");
		for (Element ID : Resers){
			IDreser= Integer.parseInt(ID.getChildText("idreser"));
			reserID = ID.getChildText("reserID");
			From=DateString(ID.getChildText("from"));
			To=DateString(ID.getChildText("to"));
			IDroom=Integer.parseInt(ID.getChildText("idroom"));
			resers.put(IDreser, new Reservation(IDreser, From, To, IDroom, IDschedule, reserID ));
		}
	}
	else{
		loaded=false;
	}
	return loaded;	
}

/**
 * Cette fonction permet de charger toute la base de donnée
 * 
 * @return un booléen pour confirmer sa réussite
 */
public boolean LoadDatabase(){
	boolean loaded = true;
	org.jdom2.Document doc = null;
	Element element;
	SAXBuilder builder = new SAXBuilder();
	try{
		doc=builder.build(new File(this.getFile()));
	}
	catch(Exception e){
		loaded = false;
	}
	if (loaded=true && doc != null){
		element= doc.getRootElement();
		if (element.getName()=="schedules"){
			loaded= LoadRoom(element.getChild("room")) && LoadSchedule(element.getChild("schedule"));
		}
		else loaded = false;
	}
	return loaded;
}

}
