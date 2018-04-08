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
            throw new IllegalArgumentException("�������㹻�Ĳ���");
        }
        if(!args[args.length-1].endsWith(".txt"))
        {
        	throw new IllegalArgumentException("������txt�ļ�");
        }
        String filepath=null;//Ĭ�ϱ�ͳ���ļ�·��
        String outpath="result.txt";//Ĭ�Ͻ������·��
        filepath=args[args.length-1];//��ͳ���ļ�·��
        Map<String, Integer> sum=scan(filepath);
        String res=SortMap(sum);    //��ֵ��������
        writetxt(res,outpath);
        
	}
	public static Map<String, Integer> scan(String path)throws IOException
	{
		//��ȡ�ĵ��������е��ʷ���list��ͳ��
        InputStreamReader isr = new InputStreamReader(new FileInputStream(path));
        BufferedReader br = new BufferedReader(isr);
        String str=null;
        List<String> lists = new ArrayList<String>();  //�洢���˺󵥴ʵ��б�  
        while((str=br.readLine())!=null){
           // String[] tmp= str.split(" |,|'");
            String[] tmp = str.split("[^a-zA-Z-]");  //���˳�ֻ������ĸ��
            for(int i=0;i<tmp.length;i++){
                if(tmp[i].length()!=0)
                {
                	//�����̺��ߵĵ�δ���ӵĵ���ȥ������
                	if((tmp[i].substring(tmp[i].length()-1, tmp[i].length())).equals("-"))
                	{
                		if(tmp[i].length()!=1)//���ⵥ������ʱ������ַ�
                		{
                    		lists.add(tmp[i].substring(0, tmp[i].length()-1)); 
                            //System.out.println(tmp[i].substring(0, tmp[i].length()-1));
                		}
                	}
                	//��һ�����̺��ߵĵ�δ���ӵĵ���ȥ������
                	else if((tmp[i].substring(0,1)).equals("-"))
                	{
                		if(tmp[i].length()!=1)//���ⵥ������ʱ������ַ�
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
        Map<String, Integer> wordsCount = new TreeMap<String,Integer>();  //�洢���ʼ�����Ϣ��keyֵΪ���ʣ�valueΪ������      
        //���ʵĴ�Ƶͳ��    
        for (String li : lists) {    
            if(wordsCount.get(li) != null){    
                wordsCount.put(li,wordsCount.get(li) + 1);    
            }else{    
                wordsCount.put(li,1);    
            }    
    
        }
        return wordsCount;
	}
    //��value�Ĵ�С��������    
    public static String SortMap(Map<String,Integer> oldmap){    
            
        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(oldmap.entrySet());    
            
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){    
            @Override    
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {    
                return o2.getValue() - o1.getValue();  //����    
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
    	//������ı�
    	File myFile=new File(path);
    	BufferedWriter out = new BufferedWriter(new FileWriter(myFile));
        out.write(res);
        out.close();
    }
}