//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;
import java.util.*;

public class Cd implements Command{
	private FileSystem fs;
	protected String path;
	protected String[] pathElements;
	protected String separator = java.io.File.separator;
	private String spliter = "[/\\\\]+";

	private Directory currentDir;
	public Cd(FileSystem fs){
		this.fs = fs;
		this.path = separator;
	}
	public Cd(FileSystem fs, String path){
		this.fs = fs;
		this.path = path;		
	}
	public void execute(){		
		if(this.path.equals("..")){
			if(fs.getCurrent().getParent() != null){
				fs.setCurrent(fs.getCurrent().getParent());
			}
			return;
		}
		else if(this.path.equals("") || this.path.equals(separator)){
			fs.setCurrent(fs.getRoot());
			return;
		}
		else if(this.path.equals(".")){
			return;
		}		
		
		if((path.charAt(0)+"").matches(spliter))//if starts from root
			{currentDir = fs.getRoot();			
			}
		else
			currentDir = fs.getCurrent();//else, starts from current folder
		//split path to individual element
		pathElements = path.split(spliter);		
		for(String element:pathElements){//loop through each path element
			if(element.equals(""))
				continue;
			if(element.equals("..")){
				if(currentDir.equals(fs.getRoot()))
					return;
				continue;
			}
			if(!(fs.findChildByName(currentDir,element) && (fs.getChildByName(currentDir,element) instanceof Directory))){
				System.out.println(path+" Is Not a valid path. Please try again.");
				return;				
			}
			currentDir = (Directory)(fs.getChildByName(currentDir,element));
		}
		fs.setCurrent(currentDir);
	}
	
	public String toString(){
		return "Command cd--change directory";
	}
}
