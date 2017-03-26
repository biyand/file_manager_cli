//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;
import java.util.*;
public class History implements Command{ 
 private CommandHistory history;
 
 public History(CommandHistory history){  
  this.history = history; 
 }

 public void execute(){  
  System.out.println("Command history: \n" + this.history);
 }
 
 public String toString(){  
  return "Command history--show command history";  
 }
}
