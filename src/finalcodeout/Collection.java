package finalcodeout;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 * ClassName: Collection
 * @author Brock Gast
 * @since 07/30/2018
 * Description - In its own thread, this takes a file and pots into the stack. 
 */
public class Collection implements Runnable {
    
    boolean threadStopCheck = true;    
    private Stack<String> thePitInList;
        
    @Override
    public void run() {
        
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("ThePit.txt"));
        } catch (FileNotFoundException ex) {
            System.out.print("There is an ERROR in putting the file into a collection.");
        } finally {
        setThePitInList(new Stack<>());
        while (scanner.hasNext()){
            getThePitInList().add(scanner.next());
        }
        scanner.close();
        Runnable moveCollection = new ListToFile(thePitInList);
        Thread thread2 = new Thread(moveCollection);
        thread2.start();
        
        while(threadStopCheck) 
            threadStopCheck = thread2.isAlive();        
        }
    }
    /**
     * @return the thePitInList
     */
    protected Stack<String> getThePitInList() {
        return thePitInList;
    }
    /**
     * @param thePitInList the thePitInList to set
     */
    protected void setThePitInList(Stack<String> thePitInList) {
        this.thePitInList = thePitInList;
    }
}
