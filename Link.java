//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;

import java.util.*;
import java.util.regex.*;
public class Link extends FSElement{
	protected FSElement target;
	
	public Link(Directory parent,String targetName,String owner,FSElement target){
		super(parent,target.getName()+".link",owner,0);
		this.name = target.getName()+".link";
		this.owner = owner;
		this.created = new Date();
		this.lastModified = this.created;
		this.target = target;
		this.size = 0;
	}
	
	public boolean isFile(){
		return false;
	}
	
	public int getSize(){
		return 0;
	}
	
	public int getTargetSize(){
		//if(this.trget.getTarget() != null)
		if(this.target instanceof Link )
			return ((Link)(this.target)).getTargetSize();
		return this.getTarget().getSize();
	}
	
	public FSElement getTarget(){
		return this.target;
	}
	
	public void accept(FSVisitor v){
		v.visit(this);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("parent folder: "+this.parent.getName()+"\n");
		sb.append("Link to: " + this.target.getName()+"\n");
		sb.append("date created: "+this.created+"\n");
		return sb.toString();
	}
}
