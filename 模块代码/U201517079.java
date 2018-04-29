import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Scan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static Map<String, Integer> scan(String path)throws IOException
	{
		//读取文档并将所有单词放入list并统计
        InputStreamReader isr = new InputStreamReader(new FileInputStream(path));
        BufferedReader br = new BufferedReader(isr);
        String str=null;
        List<String> lists = new ArrayList<String>();  //存储过滤后单词的列表  
        while((str=br.readLine())!=null){
            String[] tmp = str.split("[^a-zA-Z-]");  //过滤出只含有字母的
            for(int i=0;i<tmp.length;i++){
                if(tmp[i].length()!=0)
                {
                	//最后带短横线的但未链接的单词去掉短线
                	if((tmp[i].substring(tmp[i].length()-1, tmp[i].length())).equals("-"))
                	{
                		if(tmp[i].length()!=1)//避免单个横线时输出空字符
                		{
                    		lists.add(tmp[i].substring(0, tmp[i].length()-1)); 
                		}
                	}
                	//第一个带短横线的但未链接的单词去掉短线
                	else if((tmp[i].substring(0,1)).equals("-"))
                	{
                		if(tmp[i].length()!=1)//避免单个横线时输出空字符
                		{
                    		lists.add(tmp[i].substring(1, tmp[i].length())); 
                		}
                	}
                	else
                	{
                		lists.add(tmp[i]); 
                	}
                }
            }
        }  
        isr.close();
        Map<String, Integer> wordsCount = new TreeMap<String,Integer>();  //存储单词计数信息，key值为单词，value为单词数      
        //单词的词频统计    
        for (String li : lists) {    
            if(wordsCount.get(li) != null){    
                wordsCount.put(li,wordsCount.get(li) + 1);    
            }else{    
                wordsCount.put(li,1);    
            }    
    
        }
        return wordsCount;
	}
}
