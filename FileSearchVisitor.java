//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091

package filesystemfacade;
import java.util.*;
public class FileSearchVisitor implements FSVisitor{
	private String extension;
	private String pattern;
	private ArrayList<File> foundFiles;
	
	public FileSearchVisitor(String extension){
		this.extension = extension.toLowerCase();
		if(extension.charAt(0) == '.'){
			this.pattern = ".+\\" + this.extension + "$";
		}
		else
			this.pattern = ".+\\." + this.extension + "$";
		
		foundFiles = new ArrayList<File>();
	}
	
	public void visit(Directory dir){
		return;
	}
	
	public void visit(File file){
		if(file.getName().toLowerCase().matches(pattern))
			this.foundFiles.add(file);
	}
	
	public void visit(Link link){
		return;
	}
	
	public ArrayList<File> getFoundFiles(){
		return this.foundFiles;
	}
}