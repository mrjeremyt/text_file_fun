package text_file_fun;
import java.io.*;
import java.util.*;


class Text_File_Formats{
	File file_extensions;
	ArrayList<String> extns;
	
	public Text_File_Formats() {
		file_extensions = new File("text_file_listing");
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

	
	public static void main(String[] args) throws FileNotFoundException {
		//grab the input file
		File in_file = new File(args[0]);
		
		//try to create scanner to evaluate input file
		try {
			Scanner sc = new Scanner(in_file);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Input file is incorrect."); 
		}
		
		
		

	}

}
