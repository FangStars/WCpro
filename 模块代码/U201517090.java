import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class sort {

	 public static String SortMap(Map<String,Integer> oldmap){    
         
	        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(oldmap.entrySet());    
	            
	        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){    
	            @Override    
	            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {    
	                return o2.getValue() - o1.getValue();  //½µÐò    
	            }    
	        });    
	        StringBuilder p=new StringBuilder();
	        for(int i = 0; i<list.size(); i++){    
	            System.out.println(list.get(i).getKey()+ ": " +list.get(i).getValue()); 
	            p.append(list.get(i).getKey()+ ": " +list.get(i).getValue()+"\r\n");
	        }       
	        String res=p.toString();
	        return res;
	    }

}
