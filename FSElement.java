//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;
import java.util.*;

public class FSElement{
	protected String name;
	protected String owner;
	protected Date created;
	protected Date lastModified;
	protected int size;
	protected Directory parent;
	
	public FSElement(Directory parent,String name,String owner,int size){						
		this.name = name;
		this.owner = owner;
		this.created = new Date();
		this.lastModified = this.created;
		this.size = size;
		this.parent = parent;
		if(parent != null){			
			this.parent.appendChild(this);
		}
	}
	
	public Directory getParent(){
		return this.parent;
	}
	protected void setParent(Directory newParent){
		this.parent = newParent;
		newParent.appendChild(this);
		this.lastModified = new Date();
	}
	public boolean isFile(){
		return (this.getClass().getSimpleName().equals("File"));
	}
	public int getSize(){		
		return this.size;		
	}
	public String getName(){
		return this.name;
	}
	public String getOwner(){
		return this.owner;
	}
	protected void setOwner(String newOwner){
		this.owner = newOwner;
		this.setLastModifiedDate(new Date());
	}
	public Date getCreatedDate(){
		return new Date(this.created.getTime());
	}
	public Date getLastModifiedDate(){
		return new Date(this.lastModified.getTime());
	}
	public ArrayList<FSElement> getChildren(){
		return null;
	}	
	protected void setLastModifiedDate(Date newDate){
		this.lastModified = new Date(newDate.getTime());
	}
	public int getDiskUtil(){
		return this.size;
	}
	public void accept(FSVisitor v){
		return;
	}
}