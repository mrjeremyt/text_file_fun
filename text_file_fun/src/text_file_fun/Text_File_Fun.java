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
}

public class Text_File_Fun {

	public static final int GET_FILE = 1;
	public static final int EXTN = 2;
	public static final int EXTN_LIST = 3;
	public static final int SEARCH = 4;
	public static final int LETTERS_AND_FREQ = 5;
	public static final int WORDS_AND_FREQ = 6;
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
		

		File file;
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
	
	private static boolean check_valid_extn(File file) throws FileNotFoundException{
		Text_File_Formats testing = new Text_File_Formats();
		String file_name = file.getName();
		String delim = "[.]+";
		String[] tokens = file_name.split(delim);
		ArrayList<String> extn_list = testing.list_of_extns();
		Boolean valid_extn = false;
		
		for(int i = 0; i < extn_list.size(); i++){
			if(extn_list.get(i).equalsIgnoreCase(tokens[1])){
				valid_extn = true;
			}
		}
		return valid_extn;
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
		System.out.println("Enter " + SEARCH + " to search for a particular word in this document.");
		System.out.println("Enter " + LETTERS_AND_FREQ + " to see a list of letters and their frequency.");
		System.out.println("Enter " + WORDS_AND_FREQ + " to see a list of words and their frequency.");
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
