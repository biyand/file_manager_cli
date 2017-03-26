//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091

package filesystemfacade;
public class SizeCountingVisitor implements FSVisitor{
	private int totalSize=0;
	
	public void visit(Directory dir){
		this.totalSize += dir.getDiskUtil();
	}
	
	public void visit(File file){
		this.totalSize += file.getDiskUtil();
	}
	
	public void visit(Link link){
		this.totalSize += link.getDiskUtil();
	}
	
	public int getTotalSize(){
		return this.totalSize;
	}
}