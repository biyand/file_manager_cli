//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;
import java.util.*;
public class FileSystem{
	private static FileSystem fileSystem = null;
	protected Directory root;
	protected Directory current;	
	
	private FileSystem(){}
	
	public static FileSystem getFileSystem(){
		if(fileSystem == null)
			fileSystem = new FileSystem();
		return fileSystem;
	}
	
	public void showAllElements(){
	//print all FSElements in the list by breadth-first search
	Queue<FSElement> allFSElements = new LinkedList<FSElement>();
	allFSElements.offer(root);
	while(!allFSElements.isEmpty()){
		FSElement fse = allFSElements.poll();
		System.out.println(fse);
		if(!(fse.isFile()|| fse.getName().matches(".*.link$") ) ){//if fse is a folder
			ArrayList<FSElement> fseChildren = ((Directory)fse).getChildren();
			for(FSElement child:fseChildren){
				allFSElements.offer(child);
			}
		}				
		}		
	}
	
	public Directory getRoot(){
		return this.root;
	}
	public void setRoot(Directory root){
		this.root = root;
	}
	
	public Directory getCurrent(){
		return this.current;
	}	
	public void setCurrent(Directory current){
		this.current = current;
	}
	
	public ArrayList<FSElement> sort(Directory dir, Comparator<FSElement> comparator){
		ArrayList<FSElement> children = dir.getChildren();
		Collections.sort(children,comparator);
		return children;
	}
	
	public int getInsertionLocation(Directory dir, FSElement element){
		int index = 0;
		for(FSElement e:dir.getChildren()){
			if(e.getName().compareTo(element.getName()) < 0)
				index++;
			else break;
		}
		return index;
	}
	public void addChild(Directory parent, FSElement child){
		parent.addChild(child,getInsertionLocation(parent,child));
		child.parent = parent;
	}
	public ArrayList<FSElement> getChildren(Directory current){
		return current.getChildren();
	}
	
	public boolean findChildByName(Directory parent,String name){
		ArrayList<FSElement> children = parent.getChildren();
		for(FSElement child:children){
			if(child.getName().equals(name))
				return true;
		}
		return false;
	}
	public FSElement getChildByName(Directory parent,String name){
		ArrayList<FSElement> children = parent.getChildren();
		for(FSElement child:children){
			if(child.getName().equals(name))
				return child;
		}
		return null;		
	}
	public boolean removeChildByName(Directory parent, String name){
		FSElement target = this.getChildByName(parent,name);
		if(parent.removeChild(target))
			return true;
		return false;
	}
	public String getCurrentFullPath(){
		if(current.equals(this.root))
			return java.io.File.separator;
		Directory dir = current;
		Deque<Directory> pathStack = new ArrayDeque<Directory>();
		while(dir.getParent() != null){
			pathStack.push(dir);
			dir = dir.getParent();
		}
		StringBuilder pathSb = new StringBuilder();
		for(Directory d:pathStack){
			pathSb.append(java.io.File.separator+d.getName());
		}
		return pathSb.toString();
	}
}