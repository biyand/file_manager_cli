//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;
import java.util.*;

public class Sort implements Command{
 private FileSystem fs;
 private Comparator<FSElement> comparator;
 
 public Sort(FileSystem fs, Comparator<FSElement> comparator){
  this.fs = fs;
  this.comparator = comparator;
 }

 public void execute(){
	 
  Collections.sort(fs.getCurrent().getChildren(),this.comparator);
  for(FSElement element:fs.getCurrent().getChildren()){
   System.out.println(element.getName()+"\t"+element.getClass().getSimpleName()+"\t"+element.getCreatedDate()+"\t"+element.getOwner());
  }
 } 
 public String toString(){
  return "Command sort--sort the content of current directory";  
 }
}
