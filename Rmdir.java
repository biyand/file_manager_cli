//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;
import java.util.*;

public class Rmdir implements Command{
 private FileSystem fs;
 protected String targetDirName; 

 
 public Rmdir(FileSystem fs, String name){
  this.fs = fs;
  this.targetDirName = name;   
 
 }

 public void execute(){  
  if(!(fs.findChildByName(fs.getCurrent(),targetDirName) && (fs.getChildByName(fs.getCurrent(),targetDirName) instanceof Directory))){
   System.out.println("The directory--"+targetDirName+" doesn't exist, please try again.");  
   return;
  }
	fs.removeChildByName(fs.getCurrent(),targetDirName);
 }
 
 public String toString(){
  return "Command rmdir--remove directory";
 }
}
