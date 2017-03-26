//UMB CS680 fall2016, HW16
//Biyan Duan, 01429091
package filesystemfacade;
import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shell{
 protected static FileSystem fs;

 private String spliter = "[/\\\\]+";
 static CommandHistory commandHistory;
 static boolean ifQuit;
 
 	static Directory root,system,home,pictures,media; 
	static File a,b,c,d,e,f,g,h,log,config;
	static Link x,y,z;
  
 public Shell(){
  Shell.ifQuit = false; 
 }
 protected static void setup(){
	 fs = FileSystem.getFileSystem();
	 commandHistory = new CommandHistory(); 
	
	root = new Directory(null,"root","admin");
	fs.setRoot(root);
	system = new Directory(root,"system","admin");
	home = new Directory(root,"home","Andy");
	pictures = new Directory(home,"pictures","Andy");
	media = new Directory(home,"media","public");
  
	a = new File(system,"a.txt","Anna",2048);
	b = new File(system,"b.exe","Brian",3072);
	c = new File(system,"c.txt","Cathy",1024);
	d = new File(home,"d.txt","Dan",4096);
	e = new File(pictures,"e.jpg","Emma",5120);
	f = new File(pictures,"f.jpg","Frank",6144);
	log = new File(root,"log.txt","admin",7168);
	config = new File(system,"config.txt","admin",8192);
	g = new File(media,"g.txt","public",5120);
	h = new File(media,"h.mp3","public",5120);
  
	x = new Link(home,"system","Greg",system);
	y = new Link(pictures,"e","Hana",e);
	z = new Link(pictures,"x","John",x);
 }
 
 public static void main(String[] args) throws IOException{
 
	setup();
	Shell shell = new Shell();
  
  fs.setCurrent(root);
  System.out.println("File System Shell \n Available commands: cd,chown,count,cp,dir,du,history,ln,ls,mkdir,mv,pwd,redo,rmdir,search,sort.");
  while(!Shell.ifQuit){
   System.out.println(fs.getCurrentFullPath()+">");
   String[] arguments = shell.getCommand();
   if(arguments == null)
	   continue;
   shell.parseCommand(arguments);
   
  }
  System.out.println(" Quiting shell... Bye!");
  //System.exit(0);
  return;
 }

 public CommandHistory getCommandHistory(){
  return this.commandHistory;
 }
 
 public String[] getCommand()throws IOException{
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
  String commandLine = br.readLine();
  if(commandLine == null)
	  return null;
  String args[] = commandLine.toLowerCase().trim().split("[ ]+");
  return args;
 }
 public void parseCommand(String[] params){ 	
	 String cmd = params[0].split(spliter)[0].trim();
	 int paramlen = params.length;
  switch (cmd){
    case "quit": case "q": case "exit":
     Shell.ifQuit = true;
     break;
    case "pwd":
     Pwd pwd = new Pwd(fs);
     pwd.execute();
     commandHistory.push(pwd);
     break;
    case "cd":
     Cd cd;
     if(paramlen == 1)
      cd = new Cd(fs);
     else
      cd = new Cd(fs,params[1]);
     cd.execute();
     commandHistory.push(cd);
     break;
    case "ls":
     Ls ls = new Ls(fs);
     ls.execute();
     commandHistory.push(ls);
     break;
    case "dir":
     Dir dir;
     if(paramlen == 1)
      dir = new Dir(fs,".");
     else 
      dir = new Dir(fs,params[1]);
     dir.execute();
     commandHistory.push(dir);
     break;
    case "mkdir":
     Mkdir mkdir;
     if(paramlen == 2)
      mkdir = new Mkdir(fs,params[1]);
     else
      mkdir = new Mkdir(fs,params[1],params[2]);
     mkdir.execute();
     commandHistory.push(mkdir);
     break;
    case "rmdir":
    	if(paramlen <2 )
    		break;
     Rmdir rmdir = new Rmdir(fs,params[1]);
     rmdir.execute();
     commandHistory.push(rmdir);
     break;
    case "ln":
    	if(paramlen <3 )
    		break;
     Ln ln = new Ln(fs,params[1],params[2]);     
     ln.execute();     
     commandHistory.push(ln);
     break;
    case "mv":
    	if(paramlen <3 )
    		break;
     Mv mv = new Mv(fs,params[1], params[2]);
     mv.execute();
     commandHistory.push(mv);
     break;
    case "cp":
    	if(paramlen <3 )
    		break;
     Cp cp = new Cp(fs,params[1],params[2]);
     cp.execute();
     commandHistory.push(cp);
     break;
    case "chown":
    	if(paramlen <3 )
    		break;
     Chown chown = new Chown(fs,params[1],params[2]);
     chown.execute();
     commandHistory.push(chown);
     break;
    case "history":
     History history = new History(commandHistory);
	 commandHistory.push(history);
     history.execute();     
     break;
    case "redo":
     Redo redo = new Redo(commandHistory);
     redo.execute();
     //push last command instead of redo
     commandHistory.push(redo.getLastCommand());
     break;
    case "sort":
    	Comparator<FSElement> c=null;
    	if(paramlen < 2)
    		c = new AlphabeticalComparator();
    	else{
    	if(params[1].matches("^a.*"))
    		c=new AlphabeticalComparator();
    	if(params[1].matches("^r.*"))
    		c=new ReverseAlphabeticalComparator();
    	if(params[1].matches("^t.*"))
    		c=new TimeStampComparator();
    	}    	
   	
     Sort sort = new Sort(fs,c);     
     sort.execute();
     commandHistory.push(sort);
     break;
    case "count":
     Count count = new Count(fs);
     count.execute();
     commandHistory.push(count);
     break;
    case "du":
     Du du = new Du(fs);
     du.execute();
     commandHistory.push(du);
     break;
    case "search":
    	if(paramlen <2 )
    		break;
     Search search = new Search(fs,params[1]);
     search.execute();
     commandHistory.push(search);
     break;
    
   default: 
    System.out.println("Invalid conmmand/arguments. Please try again.");     
   }
  return;
 } 
}
