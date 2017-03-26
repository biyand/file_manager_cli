//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;
import java.util.*;

public class Search implements Command{
 private FileSystem fs;
 private FileSearchVisitor visitor;
 private String suffix;
 public Search(FileSystem fs, String suffix){
  this.fs = fs;
  this.suffix = suffix;
  this.visitor = new FileSearchVisitor(suffix);
 }

 public void execute(){
  this.fs.getCurrent().accept(visitor);
  int found = visitor.getFoundFiles().size();
  System.out.println(" Total "+found+" matches found that have suffix-"+suffix);
  if(found > 0){
   for(File f:visitor.getFoundFiles()){
    System.out.println(f.getName()+"\t"+f.getSize()+"\t"+f.getOwner()+"\t"+f.getCreatedDate());
   }   
  }
 } 
 public String toString(){
  return "Command Search--search the file with giving suffix";
 }
}
