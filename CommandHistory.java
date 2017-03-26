//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;
import java.util.*;
import java.lang.*;

public class CommandHistory{
	Stack<Command> commands;

	public CommandHistory(){
		this.commands = new Stack<Command>();		
	}
	
	public void push(Command command){
		this.commands.push(command);
	}
	
	public Command pop(){
		if(this.commands.size() > 0){
			return this.commands.pop(); 
		}
		return null;
	}
	
	public Command peek(){
		if(this.commands.size() > 0){
			return this.commands.peek();
		}
		return null;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(Command c:commands){
			sb.append(c.toString()+"\n");
		}
		return sb.toString();
	}
}
