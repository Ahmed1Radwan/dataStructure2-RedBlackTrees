package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;



public class MAIN {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//TreeMap<Integer, String> t = new TreeMap();
		ITreeMap<Integer,String> treemap = new TreeMap();
		
        treemap.put(5, "soso5");
       System.out.println(treemap.lastEntry());
		
	}
	
	
}
