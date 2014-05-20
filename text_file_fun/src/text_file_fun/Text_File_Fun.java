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
			extns.add(tokens[0]);
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

	public static final int SEARCH = 1;
	public static final int ONE_NAME = 2;
	public static final int APPEAR_ONCE = 3;
	public static final int APPEAR_ALWAYS = 4;
	public static final int Increasing_Popularity = 5;
	public static final int Decreasing_Popularity = 6;
	public static final int You_Choose_Num_Letters = 7;
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
		
		try {
		    System.out.println("Opening GUI to choose file...");
		    // next line for GUI
		    //grab the input file
		    File file = getFile();
		    //Scanner fileScanner = new Scanner(file);
		    int choice;
			Scanner keyboard = new Scanner(System.in);
			//fileScanner.close();
	
			boolean valid_extn = check_valid_extn(file);
			
		    
			do {
				showMenu();
				choice = getChoice(keyboard);
				if(valid_extn ==  false)
					 choice = QUIT;
				if( choice == SEARCH)
					return;
				else{
					//System.out.println();
					System.out.println("\nGoodbye.");
				}

			} while( choice != QUIT);
		    
		    keyboard.close();
		    
		}
		catch(FileNotFoundException e) {
			System.out.println("Problem reading the data file. Exiting the program." + e);
		}
		
	}
	
	private static boolean check_valid_extn(File file) throws FileNotFoundException{
		Text_File_Formats testing = new Text_File_Formats();
//		testing.print_all_extns(); 
		
		String file_name = file.getName();
		String delim = "[.]+";
		String[] tokens = file_name.split(delim);
		//System.out.println(tokens.length);
		ArrayList<String> extn_list = testing.list_of_extns();
		Boolean valid_extn = false;
		
		for(int i = 0; i < extn_list.size(); i++){
			if(extn_list.get(i) == tokens[1])
				valid_extn = true;
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
		while( choice < QUIT || choice > SEARCH){
			System.out.println("\n" + choice + " is not a valid choice");
			choice = getInt(keyboard, "Enter choice: ");
			keyboard.nextLine();
		}
		return choice;
	}
	
	
	private static void showMenu() {
		System.out.println("\nOptions:");
		System.out.println("Enter " + SEARCH + " to search for names.");
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
