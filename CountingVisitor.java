//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091

package filesystemfacade;
public class CountingVisitor implements FSVisitor{
	private int dirNum=0;
	private int fileNum=0;
	private int linkNum=0;
	
	public void visit(Directory dir){
		this.dirNum++;
	}
	
	public void visit(File file){
		this.fileNum++;
	}
	
	public void visit(Link link){
		this.linkNum++;
	}
	
	public int getDirNum(){
		return this.dirNum;
	}
	
	public int getFileNum(){
		return this.fileNum;
	}
	
	public int getLinkNum(){
		return this.linkNum;
	}
}