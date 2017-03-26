//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;
import java.util.*;

public class Ln implements Command{
	private FileSystem fs;
	private String path;
	private String linkName;
	private FSElement target;
	private String separator = java.io.File.separator;
	private String spliter = "[/\\\\]+";
	private Directory listDir;

	public Ln(FileSystem fs, String path,String linkName){
		this.fs = fs;
		this.path = path;
		this.linkName = linkName;

	}

	public void execute(){		
		if(this.path.equals("..")){
			if(fs.getCurrent().getParent() != null){
				this.target = fs.getCurrent().getParent();
			}			
		}
		else if(this.path.equals("") || this.path.equals(".")){
			this.target = fs.getCurrent();
		}
		else if(this.path.equals(separator)){
			this.target = fs.getRoot();
		}
		else {
			String[] pathElements = path.split(spliter);
			if((path.charAt(0)+"").matches(spliter))
				listDir = fs.getRoot();
			else
				listDir = fs.getCurrent();
			int i = 0;
			while(i < pathElements.length-1){
				if(pathElements[i].equals("")){
					i++;
					continue;
				}
				if(!(fs.findChildByName(listDir,pathElements[i]) && (fs.getChildByName(listDir,pathElements[i]) instanceof Directory))){
					System.out.println("Not a valid path. Please try again.");
					return;
				}
				listDir = (Directory)(fs.getChildByName(listDir,pathElements[i]));
				i++;
			}			
			if(fs.findChildByName(listDir,pathElements[pathElements.length-1])){
				this.target = fs.getChildByName(listDir,pathElements[pathElements.length-1]);
			}else {
				System.out.println("Not a valid path. Please try again.");
				return;
			}
		}
		new Link(fs.getCurrent(),linkName,target.getOwner(),target);

	}	
	public String toString(){
		return "Command ln--make a link";
	}
}
