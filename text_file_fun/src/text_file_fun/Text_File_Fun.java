package text_file_fun;
import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.UIManager;


class Text_File_Formats{
	private File file_extensions = null;
	private ArrayList<String> extns = null;
	
	public Text_File_Formats() {
		file_extensions = new File("C:/Users/Jeremy/git/text_file_fun/text_file_fun/src/text_file_fun/text_file_listing.txt");
		extns = new ArrayList<String>();
	}
	
	protected ArrayList<String> list_of_extns() throws FileNotFoundException{
		Scanner sc = new Scanner(file_extensions);
		while(sc.hasNextLine()){
			String ext = sc.nextLine();
			String delim = "[ ]+";
			String[] tokens = ext.split(delim);
			extns.add(tokens[0].toLowerCase());
		}
		sc.close();
		return extns;
	}
	
	protected void print_all_extns() throws FileNotFoundException{
		Scanner sc = new Scanner(file_extensions);
		while(sc.hasNextLine()){
		 System.out.println(sc.nextLine());
		}
		sc.close();
	}
	
	protected void all_extns_to_file() throws FileNotFoundException{
		Scanner sc = new Scanner(file_extensions);
		PrintWriter file = new PrintWriter(System.getProperty("user.home") + "/Desktop/viable_file_extensions.txt");
		while(sc.hasNextLine()){
			file.println(sc.nextLine());
		}
		sc.close();
		file.close();
	}
}

public class Text_File_Fun {

	public static final int GET_FILE = 1;
	public static final int EXTN = 2;
	public static final int EXTN_LIST = 3;
	public static final int WORDS_AND_FREQ = 4;
	public static final int LETTERS_AND_FREQ = 5;
	public static final int SEARCH = 6;
	public static final int QUIT = 0 ;
	
	/*MAIN  METHOD ***********************************************************************************************************/
	public static void main(String[] args) throws FileNotFoundException {
		
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
		    System.out.println("Unable to set look at feel to local settings. " +
		    		"Continuing with default Java look and feel.");
		}
		

		File file = null;
	    int choice;
		Scanner keyboard = new Scanner(System.in);
		boolean valid_extn = true;
	    
		do {
			showMenu();
			choice = getChoice(keyboard);
			if( choice == GET_FILE){
				System.out.println("Opening GUI to choose file...");
				file = getFile();
				valid_extn = check_valid_extn(file);
			}else if(choice == EXTN){
				file_extn_return(file, keyboard);
			}else if(choice == EXTN_LIST){
				file_extn_list(keyboard);
			}else if(choice == WORDS_AND_FREQ){
				words__and_frequency(keyboard, file);
			}else if(choice ==LETTERS_AND_FREQ){
				letters_and_frequency(keyboard, file);
			}else if(choice == SEARCH){
				search(keyboard);
			}
			else{
				//System.out.println();
				System.out.println("\nGoodbye.");
			}
			if(valid_extn == false){
				choice = QUIT;
				System.out.println("\nNot a valid file extension, Goodbye.");
			}
		} while( choice != QUIT);
	    
	    keyboard.close();
		    
		
	}
	
	private static String search(Scanner s){
		return null;
	}
	
	private static void letters_and_frequency(Scanner s, File file) throws FileNotFoundException{
		Boolean _file = file_or_no(s);
		Map theMap = get_Letters_Map(file);
		if(_file){
			PrintWriter f = new PrintWriter(System.getProperty("user.home") + "/Desktop/letters_and_frequency.txt");

		}
	}
	
	private static void words__and_frequency(Scanner s, File file) throws FileNotFoundException{
		Boolean _file = file_or_no(s);
		Map<String, Integer> theMap = get_Words_Map(file);
		if(_file){
			PrintWriter f = new PrintWriter(System.getProperty("user.home") + "/Desktop/words_and_frequency.txt");
			f.println("Words | Frequency");
			Iterator<String> it = theMap.keySet().iterator();
			while(it.hasNext()){
				String key = (String) it.next();
				int value = (int) theMap.get(key);
				f.println(key + " | " + value);
			}
			f.close();
		}else{
			System.out.println("Words | Frequency");
			Iterator it = theMap.keySet().iterator();
			while(it.hasNext()){
				String key = (String) it.next();
				int value = (int) theMap.get(key);
				System.out.println(key + " | " + value);
			}
			
		}
	}
	
	
	private static Map<String, Integer> get_Words_Map(File file) throws FileNotFoundException{
		Map<String, Integer> theMap = new HashMap<String, Integer>();
		Scanner sc = new Scanner(file);
		
		while( sc.hasNextLine() ){
			String line = sc.nextLine();
			String[]tokens = line.split(" |\\.|\\,");
			for(int i = 0; i < tokens.length; i++){
				String tmp = tokens[i];
				if(theMap.containsKey(tmp)){
					theMap.put(tmp, theMap.get(tmp) + 1);
				}else{
					theMap.put(tmp, 1);
				}
			}
		}
		sc.close();
		
		return theMap;
	}
	
	private static Map get_Letters_Map(File file) throws FileNotFoundException{
		Map<String, Integer> theMap = new HashMap<String, Integer>();
		Scanner sc = new Scanner(file);
		
		while( sc.hasNextLine() ){
			String line = sc.nextLine();
			String[] tokens = line.split("(?<!^)");
			for(int i = 0; i < tokens.length; i++){
				String tmp = tokens[i];
				if(theMap.containsKey(tmp)){
					theMap.put(tmp, theMap.get(tmp) + 1);
				}else{
					theMap.put(tmp, 1);
				}
			}
		}
		sc.close();
		return theMap;
	}
	
	private static void file_extn_list(Scanner s) throws FileNotFoundException{
		Boolean _file = file_or_no(s);
		Text_File_Formats alpha = new Text_File_Formats();
		if(_file){
			alpha.all_extns_to_file();
		}else{
			alpha.print_all_extns();
		}
	}
	
	
	private static void file_extn_return(File file, Scanner s){
		if(file == null){
			System.out.println("Please select a file first.");
			return;
		}
		String extn = get_extn(file);
		System.out.println("The file extension we detected is: " + extn);
	}
	
	private static boolean file_or_no(Scanner s){
		boolean file = false;
		int response;
		do{
			response = getInt(s, "Select 1 for a file version or select 2 to see the list here: ");
			System.out.println(response);
		}while(response != 1 && response != 2);
		if(response == 1)
			file = true;
		return file;
	}
	
	private static boolean check_valid_extn(File file) throws FileNotFoundException{
		Text_File_Formats testing = new Text_File_Formats();
		String token = get_extn(file);
		ArrayList<String> extn_list = testing.list_of_extns();
		Boolean valid_extn = false;
		
		for(int i = 0; i < extn_list.size(); i++){
			if(extn_list.get(i).equalsIgnoreCase(token)){
				valid_extn = true;
			}
		}
		return valid_extn;
	}
	
	private static String get_extn(File file){
		String file_name = file.getName();
		String delim = "[.]+";
		String[] tokens = file_name.split(delim);
		return tokens[1];
	}
	
	private static int getInt(Scanner s, String prompt) {
        System.out.print(prompt);
        while( !s.hasNextInt() ){
            s.next();
            System.out.println("That was not an int.");
            System.out.print(prompt);
        }
        return s.nextInt();
    }
	
	
	private static int getChoice(Scanner keyboard) {
		int choice = getInt(keyboard, "Enter choice: ");
		keyboard.nextLine();
		while( choice < QUIT || choice > WORDS_AND_FREQ){
			System.out.println("\n" + choice + " is not a valid choice");
			choice = getInt(keyboard, "Enter choice: ");
			keyboard.nextLine();
		}
		return choice;
	}
	
	
	private static void showMenu() {
		System.out.println("\nOptions:");
		System.out.println("Enter " + GET_FILE + " to retreive the file to evaluate.");
		System.out.println("Enter " + EXTN + " to see the file extenstion that we detected.");
		System.out.println("Enter " + EXTN_LIST + " to see a list of viable file extensions.");
		System.out.println("Enter " + WORDS_AND_FREQ + " to see a list of words and their frequency.");
		System.out.println("Enter " + LETTERS_AND_FREQ + " to see a list of letters and their frequency.");
		System.out.println("Enter " + SEARCH + " to search for a particular word in this document.");		
		System.out.println("Enter " + QUIT + " to quit.\n");
	}
	
	
	public static File getFile() {
        // create a GUI window to pick the text to evaluate
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select File.");
        int retval = chooser.showOpenDialog(null);
        File f = null;
        chooser.grabFocus();
        if (retval == JFileChooser.APPROVE_OPTION)
           f = chooser.getSelectedFile();
        return f;
    }

}
