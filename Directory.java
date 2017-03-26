//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;

import java.util.*;
public class Directory extends FSElement{
	private ArrayList<FSElement> children;
	protected FileSystem fileSystem;
 
	public Directory(Directory parent,String name,String owner){	
		super(parent,name,owner,0);
		this.created = new Date();
		this.lastModified = this.created;
		this.size = 0;
	
		fileSystem = FileSystem.getFileSystem(); 
		this.children = new ArrayList<FSElement>();	   		 
	}
 
	public boolean isFile(){
		return false;
	}
 
	public ArrayList<FSElement> getChildren(){
		return this.children;
	}
	
	public void addChild(FSElement newChild,int index){
		Date date = new Date();
		this.children.add(index,newChild);
		this.setLastModifiedDate(date);
	}
 
	public void appendChild(FSElement newChild){
		Date date = new Date();
		this.children.add(newChild);
		this.setLastModifiedDate(date);		
	}
	
	public boolean removeChild(FSElement child){		
		if(this.children.remove(child)){
			Date date = new Date();
			this.setLastModifiedDate(date);
			return true;
		}
		return false;
	}
 
	public int getSize(){
		int total = 0;
		for(FSElement child: this.children){
			total += child.getSize();
		}
		return total;
	} 
 
	public String toString(){
		StringBuilder sb = new StringBuilder();  
		if(this.parent != null){
			sb.append("parent folder: "+this.parent.getName()+"\n");
		}		
		sb.append("Directory: " + this.name+"\n");
		sb.append("size: "+this.getSize()+"\n");
		sb.append("owner: "+this.owner+"\n");
		sb.append("date created: "+this.created+"\n");
		sb.append("has total "+this.children.size()+" children.");
		if(this.children.size() > 0){
			for(FSElement child:children){
				sb.append("---"+child.getName()+"  "+child.getClass().getSimpleName()+". \n");
			}
		}
		return sb.toString();
	}
	public void printDirectoryTree(){
		fileSystem.showAllElements();
	}
	
	public void accept(FSVisitor v){
		v.visit(this);
		for(FSElement e:children){
			e.accept(v);
		}
	}
}