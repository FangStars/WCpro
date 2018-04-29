public static void writetxt(String res,String path) throws IOException
{
    //输出到文本
    File myFile=new File(path);
    BufferedWriter out = new BufferedWriter(new FileWriter(myFile));
    out.write(res);
    out.close();
}

