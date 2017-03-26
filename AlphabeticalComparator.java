//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;
import java.util.*;
import java.io.*;
public class AlphabeticalComparator implements Serializable, Comparator<FSElement>{
	private static final long serialVersionUID = 1L;
	@Override
	public int compare(FSElement fse1, FSElement fse2){
		return fse1.getName().toLowerCase().compareTo(fse2.getName().toLowerCase());
	}
}