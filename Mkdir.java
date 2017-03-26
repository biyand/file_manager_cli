//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;
import java.util.*;
public class Mkdir implements Command{
 private FileSystem fs;
 protected String newDirName;
 protected String owner;

 
 public Mkdir(FileSystem fs, String name){
  this.fs = fs;
  this.newDirName = name; 
  this.owner = fs.getCurrent().getOwner();

 }
 public Mkdir(FileSystem fs,String name,String owner){
  this.fs = fs;
  this.newDirName = name;
  this.owner = owner;
 }
 public void execute(){  
  if(fs.findChildByName(fs.getCurrent(),newDirName)){
   System.out.println("The name--"+newDirName+" has been taken, please try a different one.");
   return;
  }
  new Directory(fs.getCurrent(),newDirName,owner);

 }
 
 public String toString(){
  return "Command mkdir--create a new directory";
 }
}
