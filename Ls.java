//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;
public class Ls implements Command{
 private FileSystem fs;
 
 public Ls(FileSystem fs){
  this.fs = fs;
 }
 public void execute(){
  if(fs.getCurrent() == null){
   fs.setCurrent(fs.getRoot());
  }
  for(FSElement element:fs.getCurrent().getChildren()){
   System.out.println(element.getName() + "\t\t" + element.getClass().getSimpleName()+"\t"+element.getDiskUtil()+"(KB)"+"\t"+element.getCreatedDate()+"\t"+element.getOwner());
  }  
 }
 public String toString(){
  return "Command ls--list the content";
 }
}
