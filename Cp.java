//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;
import java.util.*;

public class Cp implements Command{
 private FileSystem fs;
 private String source,destination;
 private String[] sourceNames;
 private String[] destinationNames;
 private FSElement sourceElement;
 private Directory sourceDir;
 private Directory destinationDir;
 private String separator = java.io.File.separator;
 private String spliter = "[/\\\\]+";

 public Cp(FileSystem fs, String source,String destination){
  this.fs = fs;
  this.source = source;
  this.destination = destination; 

 }
 private boolean getSource(){
  if(source.equals(destination))
   return false;
  if(this.source.equals(separator)){
   return false;//can't copy entire root
  }
  if(source.startsWith(separator))
   sourceDir = fs.getRoot();//traverse from root
  else
   sourceDir = fs.getCurrent();//traverse from current
  int i = 0;
  sourceNames = source.split(spliter);
  while(i < sourceNames.length-1){   //traverse till one-before-last
	  if(sourceNames[i].equals("")){
		  i++;
		  continue;}
   if(!(fs.findChildByName(sourceDir,sourceNames[i]) && (fs.getChildByName(sourceDir,sourceNames[i]) instanceof Directory))){     
    return false;
   }
   sourceDir = (Directory)(fs.getChildByName(sourceDir,sourceNames[i]));
   i++;
  } //last element is a FSElement(can be Directory or File/Link ) 
  if(fs.findChildByName(sourceDir,sourceNames[sourceNames.length-1])){
   sourceElement = fs.getChildByName(sourceDir,sourceNames[sourceNames.length-1]);
  }else {
   return false;
  }
  return true;
 }
 private boolean getDestination(){
  if(destination.equals(separator)){
   destinationDir = fs.getRoot();
   return true;
  }
  if(destination.startsWith(separator))
   destinationDir = fs.getRoot();//traverse from root
  else
   destinationDir = fs.getCurrent();//traverse from current
  int j = 0;
  destinationNames = destination.split(spliter);
  while(j < destinationNames.length){  //traverse to the last
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
 //make deep copy of Directory children by recursion
 private void deepCopyChildren(Directory fromDir,Directory toDir){
  if(fromDir.getChildren().size() == 0)
   return;
  for(FSElement child:fromDir.getChildren()){
   if(child instanceof File){
    new File(toDir,child.getName(),child.getOwner(),child.getSize());
   }
   else if(child instanceof Link){
    new Link(toDir,child.getName(),child.getOwner(),((Link)child).getTarget());
   }
   else if(child instanceof Directory){
    Directory newDir = new Directory(toDir,child.getName(),child.getOwner());    
    deepCopyChildren((Directory)child,newDir);    
   }
  }
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
  if(Arrays.equals(sourceNames,destinationNames)){
   System.out.println("Source and Destination must be different, please try again");

   return;
  }
  
   if(fs.findChildByName(destinationDir,sourceElement.getName())){
	  FSElement destElement=fs.getChildByName(destinationDir,sourceElement.getName());
	  if(sourceElement.getClass().equals(destElement.getClass()))
		System.out.println("File with same name already exist in destination. Overwrite? (Y/N)");
	  Scanner input = new Scanner(System.in,"UTF_8");
	  String answer = input.nextLine().toLowerCase().trim();
	  if(answer.charAt(0) != 'y')
		  return;
	  destinationDir.removeChild(destElement);
  }
  
  if(sourceElement instanceof File){
   new File(destinationDir,sourceElement.getName(),sourceElement.getOwner(),sourceElement.getSize());

   return;
  }
  else if(sourceElement instanceof Link){
   new Link(destinationDir,sourceElement.getName(),sourceElement.getOwner(),((Link)sourceElement).getTarget());

   return;
  }
  else if(sourceElement instanceof Directory){
   Directory destinationFolder = new Directory(destinationDir,sourceElement.getName(),sourceElement.getOwner());
   deepCopyChildren((Directory)sourceElement,destinationFolder);

   return;
  }
 } 
 public String toString(){
  return "Command cp--copy the file/folder/link";
 }
}
