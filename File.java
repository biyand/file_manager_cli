//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;

import java.util.*;
public class File extends FSElement{	

	public File(Directory parent,String name,String owner,int size){
		super(parent,name,owner,size);
		this.name = name;
		this.owner = owner;
		this.created = new Date();
		this.lastModified = this.created;
		this.size = size;
	}
	
	public boolean isFile(){
		return true;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public void accept(FSVisitor v){
		v.visit(this);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();			
		sb.append("parent folder: "+this.parent.getName()+"\n");
		sb.append("File: " + this.name+"\n");
		sb.append("size: "+this.getSize()+"\n");
		sb.append("owner: "+this.owner+"\n");
		sb.append("date created: "+this.created+"\n");
		return sb.toString();
	}	
}