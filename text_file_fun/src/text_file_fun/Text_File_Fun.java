package text_file_fun;
import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.UIManager;


class Text_File_Formats{
	File file_extensions = null;
	ArrayList<String> extns = null;
	
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
		return extns;
	}
	
	protected void print_all_extns() throws FileNotFoundException{
		Scanner sc = new Scanner(file_extensions);
		
		while(sc.hasNextLine()){
		 System.out.println(sc.nextLine());
		}
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
	public static final int QUIT = 8;
	
	/*MAIN  METHOD ******************************************************************************************************/
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
		    Scanner fileScanner = new Scanner(getFile());
		    int choice;
			Scanner keyboard = new Scanner(System.in);
			fileScanner.close();
	
			int n;
//			Text_File_Formats testing = new Text_File_Formats();
//			testing.print_all_extns();
		    
			do {
				showMenu();
				choice = getChoice(keyboard);
				if( choice == SEARCH)
					return;
				else{
					//System.out.println();
					System.out.println("\nGoodbye.");
				}

			} while( choice != QUIT);
		    
		    
		    
		}
		catch(FileNotFoundException e) {
			System.out.println("Problem reading the data file. Exiting the program." + e);
		}
		
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
		while( choice < SEARCH || choice > QUIT){
			System.out.println("\n" + choice + " is not a valid choice");
			choice = getInt(keyboard, "Enter choice: ");
			keyboard.nextLine();
		}
		return choice;
	}
	
	
	private static void showMenu() {
		System.out.println("\nOptions:");
		System.out.println("Enter " + SEARCH + " to search for names.");
		System.out.println("Enter " + ONE_NAME + " to display data for one name.");
		System.out.println("Enter " + APPEAR_ONCE+ " to display all names that appear in only one decade.");
		System.out.println("Enter " + APPEAR_ALWAYS + " to display all names that appear in all decades.");
		System.out.println("Enter " + Increasing_Popularity + " to display all the names that increase in popularity over all the decades.");
		System.out.println("Enter " + Decreasing_Popularity + " to display all the names that decrease in popularity over all the decades.");
		System.out.println("Enter " + You_Choose_Num_Letters + " to display all the names that have 5 letters or more in the name.");
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
