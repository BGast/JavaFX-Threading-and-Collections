package finalcodeout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Stack;

/**
 * ClassName: ListToFile
 * @author Brock Gast
 * @since 07/30/2018
 * Description - In its own thread, this takes the stack and dumps it into a new file.
 */
public class ListToFile implements Runnable{
    
    Stack<String> thePitInList; 

    ListToFile(Stack c) {
        thePitInList = c;
    }
    @Override
    public void run(){    
        File file = new File("ThePitAfterCollection.txt");

        try(PrintWriter pw = new PrintWriter(new FileOutputStream(file))){
            thePitInList.forEach((s) -> {
                pw.println(s);       
            });
        } 
        catch (FileNotFoundException ex) {
            System.out.print("There is an ERROR in writing the collection to a file.");
        }
    }
}    


