//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;
import java.util.*;
import java.io.*;

public class Mv implements Command{
 private FileSystem fs;
 private String source,destination;
 private String[] sourceNames;
 private String[] destinationNames;
 private FSElement sourceElement;
 private Directory sourceDir;
 private Directory destinationDir;
 private String separator = java.io.File.separator;
 private String spliter = "[/\\\\]+";

 public Mv(FileSystem fs, String source,String destination){
  this.fs = fs;
  this.source = source;
  this.destination = destination; 
   
 }
 private boolean getSource(){
  if(this.source.equals(separator)){//cannot move root
   return false;
  }
  if((source.charAt(0)+"").matches(spliter))
   sourceDir = fs.getRoot();//scan source path based on root
  else
   sourceDir = fs.getCurrent();//scan source path based on current
  int i = 0;
  sourceNames = source.split(spliter);
  while(i < sourceNames.length-1){//scan source path till next-to-last element
	  if(sourceNames[i].equals("")){
		  i++;
		  continue;
	  }
   if(!(fs.findChildByName(sourceDir,sourceNames[i]) && (fs.getChildByName(sourceDir,sourceNames[i]) instanceof Directory))){     
    return false;
   }
   sourceDir = (Directory)(fs.getChildByName(sourceDir,sourceNames[i]));
   i++;
  }   
  if(fs.findChildByName(sourceDir,sourceNames[sourceNames.length-1])){
   sourceElement = fs.getChildByName(sourceDir,sourceNames[sourceNames.length-1]);
  }else {
   return false;
  }
  return true;
 }
 private boolean getDestination(){
  if(destination.equals(separator)){//root as destination 
   destinationDir = fs.getRoot();
   return true;
  } //scan destination path starting from root
  if((destination.charAt(0)+"").matches(spliter))
   destinationDir = fs.getRoot();
  else //scan destination path based on current
   destinationDir = fs.getCurrent();
  int j = 0;
  destinationNames = destination.split(spliter);
  while(j < destinationNames.length){ //scan destination path to the end
	  if(destinationNames[j].equals("")){
		  j++;
		  continue;
	  }
   if(!(fs.findChildByName(destinationDir,destinationNames[j]) && (fs.getChildByName(destinationDir,destinationNames[j]) instanceof Directory))){
    return false;
   }
   destinationDir = (Directory)(fs.getChildByName(destinationDir,destinationNames[j]));
   j++;
  }
  return true;
 }
 public void execute(){
  if(!getSource()){
   System.out.println("Invalid source path, please try again.");
   
   return;
  }
  if(!getDestination()){
   System.out.println("Invalid destination path, please try again.");
   
   return;
  }
  if(fs.findChildByName(destinationDir,sourceElement.getName())){
	  FSElement destElement=fs.getChildByName(destinationDir,sourceElement.getName());
	  if(sourceElement.getClass().equals(destElement.getClass()))
		System.out.println("File with same name already exist in destination. Overwrite? (Y/N)");
	  Scanner input = new Scanner(System.in,"UTF-8");
	  String answer = input.nextLine().toLowerCase().trim();
	  if(answer.charAt(0) != 'y')
		  return;
	  destinationDir.removeChild(destElement);
  }
  sourceElement.getParent().removeChild(sourceElement);
  sourceElement.setParent(destinationDir);
  
 } 
 public String toString(){
  return "Command mv-- move a file/folder/link";
 }
}
