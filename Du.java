//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;
import java.util.*;

public class Du implements Command{
 private FileSystem fs;

 private SizeCountingVisitor visitor;
 public Du(FileSystem fs){
  this.fs = fs;
  this.visitor = new SizeCountingVisitor();
 }

 public void execute(){
  this.fs.getCurrent().accept(visitor);  
  System.out.println(" total size of directory--"+fs.getCurrent().getName()+" is "+visitor.getTotalSize());
 } 
 public String toString(){
  return "Command Du--get total size";   
 }
}
