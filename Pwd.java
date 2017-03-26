//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;
public class Pwd implements Command{
 private FileSystem fs;
 
 public Pwd(FileSystem fs){
  this.fs = fs;
 }
 public void execute(){  //print current working directory
  System.out.println("Current working directory: " + fs.getCurrentFullPath());
 }
 
 public String toString(){
  return "Command pwd--print current working directory";
 }
}
