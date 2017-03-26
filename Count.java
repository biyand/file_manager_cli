//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;
import java.util.*;

public class Count implements Command{
 private FileSystem fs;
 private CountingVisitor visitor;
 
 public Count(FileSystem fs){
  this.fs = fs;
  this.visitor = new CountingVisitor();
 }

 public void execute(){
  fs.getCurrent().accept(visitor);  
  System.out.println(" Number of directory: "+visitor.getDirNum());
   System.out.println(" Number of File: "+visitor.getFileNum());
   System.out.println(" Number of Link: "+visitor.getLinkNum());
 } 
 public String toString(){
  return "Command Count--get the number of file, folder and link of a folder";
 }
}
