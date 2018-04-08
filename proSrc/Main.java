import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
public class Main {
	public static void main(String[] args)throws Exception {
        if(args.length<1){
            throw new IllegalArgumentException("请输入足够的参数");
        }
        if(args[args.length-1].indexOf(".txt")==-1)
        {
        	throw new IllegalArgumentException("请输入txt文件");
        }
        String filepath=null;//默认被统计文件路径
        String outpath="result.txt";//默认结果保存路径
        filepath=args[args.length-1];//被统计文件路径
        Map<String, Integer> sum=scan(filepath);
        String res=SortMap(sum);    //按值进行排序
        writetxt(res,outpath);
        
	}
	public static Map<String, Integer> scan(String path)throws IOException
	{
		//读取文档并将所有单词放入list并统计
        InputStreamReader isr = new InputStreamReader(new FileInputStream(path));
        BufferedReader br = new BufferedReader(isr);
        String str=null;
        List<String> lists = new ArrayList<String>();  //存储过滤后单词的列表  
        while((str=br.readLine())!=null){
           // String[] tmp= str.split(" |,|'");
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
                            //System.out.println(tmp[i].substring(0, tmp[i].length()-1));
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
                        //System.out.println(tmp[i]);
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
    //按value的大小进行排序    
    public static String SortMap(Map<String,Integer> oldmap){    
            
        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(oldmap.entrySet());    
            
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){    
            @Override    
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {    
                return o2.getValue() - o1.getValue();  //降序    
            }    
        });    
        StringBuilder p=new StringBuilder();
        for(int i = 0; i<list.size(); i++){    
            //System.out.println(list.get(i).getKey()+ ": " +list.get(i).getValue()); 
            p.append(list.get(i).getKey()+ ": " +list.get(i).getValue()+"\r\n");
        }       
        String res=p.toString();
        return res;
    }
    public static void writetxt(String res,String path) throws IOException
    {
    	//输出到文本
    	File myFile=new File(path);
    	BufferedWriter out = new BufferedWriter(new FileWriter(myFile));
        out.write(res);
        out.close();
    }
}
