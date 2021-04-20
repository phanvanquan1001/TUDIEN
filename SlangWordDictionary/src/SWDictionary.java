import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import jdk.internal.jshell.tool.resources.version;

public class SWDictionary {

    public final static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pauseScreen(){
        System.out.println("Nhấn phím bất kỳ để tiếp tục...");
        new java.util.Scanner(System.in).nextLine();
    }

    public static HashMap<String,List<String>> m=new HashMap<String,List<String>>();
    public static List<String> lichsuSlangWord=new ArrayList()
    public static Scanner word=new Scanner(System.in);

    public static void GetLichsu()
    {
        try
        {
            File f=new File("./data/lichsu.txt");
            FileReader fr=new FileReader(f);
            BufferedReader br=new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null)
            {
                lichsuSlangWord.add(line);
            }
            fr.close();
            br.close();
        }
        catch(Exception ex)
        {
            System.out.println("ERROR"+ex);
        }
    }

    public static void GetData(){
        try
        {
            File f=new File("./data/slang.txt");
            FileReader fr=new FileReader(f);
            BufferedReader br=new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null)
            {
                if(line.contains("`"))
                {
                    String[] s=line.split("`");
                    String[] tmp =s[1].split("\\|");
                    List<String>  temp=Arrays.asList(tmp);
                    m.put(s[0],temp);
                }
            }
            fr.close();
            br.close();
        }
        catch(Exception ex)
        {
            System.out.println("ERROR"+ex);
        }
    }

    //Tìm kiếm theo Slang word 
    public static void TimkiemSlangWord()
    {
        clearScreen();
        System.out.print("Nhập SlangWord bạn muốn tìm kiếm: ");
        String check=word.nextLine();
        check=check.toUpperCase();
        List<String> test=m.get(check);
        lichsuSlangWord.add(check);
        System.out.println(test);
        pauseScreen();
        Menu();
    }

    //Tìm kiếm theo Definition
    public static void TimkiemDefinition()
    {
        clearScreen();
        System.out.print("Nhập Definition bạn muốn tìm kiếm: ");
        String check=word.nextLine();
        lichsuSlangWord.add(check);
        List<String> answer=new ArrayList();
        for(String i: m.keySet())
        {
            if(m.get(i).contains(check))
            {
                answer.add(i);
            }
        }
        System.out.println(answer);
        pauseScreen();
        Menu();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
