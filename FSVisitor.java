//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091

package filesystemfacade;
public interface FSVisitor{
	public void visit(Link link);
	public void visit(Directory dir);
	public void visit(File file);
}