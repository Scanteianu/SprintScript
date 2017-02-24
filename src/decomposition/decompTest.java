package decomposition;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class decompTest {
	public static void main(String[] args) {
		try {
			String file="";
			Scanner sc = new Scanner(new File("input.txt"));
			while(sc.hasNextLine()){
				file+=(sc.nextLine())+"\n";
			}
			ProgramDecomposer.decomposeProgram(file);
			sc.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
