//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;
import java.util.*;

public class Chown implements Command{
 private FileSystem fs;
 private String targetName;
 private String newOwner;
 private FSElement target;

 public Chown(FileSystem fs, String targetName,String newOwner){
  this.fs = fs;
  this.targetName = targetName;
  this.newOwner = newOwner;
  
 }

 public void execute(){
  if(!(fs.findChildByName(fs.getCurrent(),targetName))){
   System.out.println("Invalid target. Please try again later." );
   
   return;
  }  
  target = fs.getChildByName(fs.getCurrent(),targetName);  
  target.setOwner(newOwner);
  
  return;
 } 
 public String toString(){  
  return "Command chown--change ownership";
 }
}
