//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;

public class Redo implements Command{ 
 private Command lastCommand;
 private CommandHistory ch;
 public Redo(CommandHistory ch){   
  this.ch = ch;
 }

 public void execute(){
	lastCommand = ch.peek();    
  lastCommand.execute();
  
 }
 
 public Command getLastCommand(){	 
  return ch.peek();
 }
 
 public String toString(){
  return "Command redo--repeat tha last command";
 }
}
