//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;
import java.util.*;

public class Dir implements Command{
 private FileSystem fs;
 private String path;
 private String[] pathElements;
 private ArrayList<FSElement> listElements;
 private String separator = java.io.File.separator;
 private String spliter = "[/\\\\]+";
 private Directory listDir;

 
 public Dir(FileSystem fs, String path){
  this.fs = fs;
  this.path = path;

 }
 
 private boolean validatePath(){
  if(this.path.equals("..")){
   if(fs.getCurrent().getParent() != null){
    listElements = fs.getCurrent().getParent().getChildren();
   } else return false;  
  }
  else if(this.path.equals("") || this.path.equals(".")){
   listElements = fs.getCurrent().getChildren();
  }
  else if(this.path.equals(separator)){
   listElements = fs.getRoot().getChildren();
  }
  else {
   pathElements = path.split(spliter);
   if((path.charAt(0)+"").matches(spliter)){
    listDir = fs.getRoot();
   } else if(path.startsWith("..") && !fs.getCurrent().getName().equals("root")){
    listDir = fs.getCurrent().getParent();
   } else {
    listDir = fs.getCurrent();
   }    
   int i = 0;
   if(pathElements[0].equals(".")){
    listDir = fs.getCurrent();
    i++;
   }
   while(i < pathElements.length-1){
	   if(pathElements[i].equals("")){
		   i++;
		   continue;
	   }
    if(!(fs.findChildByName(listDir,pathElements[i]) && (fs.getChildByName(listDir,pathElements[i]) instanceof Directory))){     
     return false;
    }
    listDir = (Directory)(fs.getChildByName(listDir,pathElements[i]));
    i++;
   }
   FSElement last;  //path target
   if(fs.findChildByName(listDir,pathElements[pathElements.length-1])){
    last = fs.getChildByName(listDir,pathElements[pathElements.length-1]);
    if(last instanceof Directory){//if path points to a folder
     listElements = ((Directory)last).getChildren();//get the folder content(children)
    } else{//path points to a file or link, or files/links
     listElements = new ArrayList<FSElement>();
     //go to the parent of path target, scan it's children
     for(FSElement child:listDir.getChildren()){
      if(child.getName().equals(last.getName())){
       listElements.add(child);
      }
     }
    }
   }else {//if path target name contains wildcard
    listElements = new ArrayList<FSElement>();
    for(FSElement child:listDir.getChildren()){
     if(child.getName().contains(pathElements[pathElements.length-1]))
      listElements.add(child);
    }
   }
  }
  return true;
 }
 public void execute(){
  if(! validatePath()){
   System.out.println("Not a valid path. Please try again.");

   return;
  }
  for(FSElement element:listElements){
   System.out.println(element.getName()+"\t"+element.getClass().getSimpleName()+"\t"+element.getSize()+"\t"+element.getOwner()+"\t"+element.getLastModifiedDate());
  }

 } 
 public String toString(){  
  return "Command dir--show directory content";
 }
}
