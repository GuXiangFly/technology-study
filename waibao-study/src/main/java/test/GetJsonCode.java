package test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author guxiang02
 * @Date 2020/6/10
 **/
public class GetJsonCode {

    public static String file  = "/Users/didi/Downloads/查询任务生产点位_guxiang_20220110115402.csv";
    public static String filePathTest  = "/Users/mtdp/dev/ideaworkspace/guxiangwork/technology-study/waibao-study/src/main/resources/考核目标-new.txt";


    public static void main(String[] args)  {
        try {


            ArrayList<String> poiId_test = new ArrayList<String>();
            ArrayList<String> poiId_prod = new ArrayList<String>();

            BufferedReader in = new BufferedReader(new FileReader(file));
            String str;
            while ((str = in.readLine()) != null) {
                str = str.replace("\"","");
                if (str.length() == 3){
                    continue;
                }
                poiId_prod.add(str);
                if (poiId_prod.size() == 1000){
                    String s = JSON.toJSONString(poiId_prod);
                    String s1 = GZipUtil.compress(s);
                    System.out.println("origin:" + s);
                    System.out.println(s1);
                    poiId_prod.clear();
                    System.out.println("------------------");
                }
            }
            in.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
