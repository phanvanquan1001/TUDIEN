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

import javax.print.attribute.standard.Media;


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
    public static List<String> lichsuSlangWord=new ArrayList();
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
        menu();
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
        menu();
    }
    

    //Tìm kiếm lịch sử
 static void Hienthilichsu()
    {
        clearScreen();
        System.out.println("Lịch Sử Tìm Kiếm: ");
        for (String temp: lichsuSlangWord )
        {
            System.out.println(temp);
        }
        pauseScreen();
        menu();
    }
    
    //Thêm SlangWord

    public static void ThemSlangWord()
    {
        clearScreen();
        System.out.println("Vui lòng nhập SlangWord: ");
        String check = word.nextLine();
        check=check.toUpperCase();
        System.out.println("Nhập definition: ");
        String check0=word.nextLine();
        List<String> t=new ArrayList<>();
        t.add(check0);
        if(m.containsKey(check))
        {
            System.out.println("Overwrite: (Y/N)");
            String confirm=word.nextLine();
            if(confirm.equals("Y")|| confirm.equals("y")) m.put(check,t);
            else
            {
                List<String> i=m.get(check);
                for(String j:i)
                {
                    t.add(j);
                }
                m.put(check, t);
            }
        }
        else
        {
            m.put(check, t);
            System.out.println("Thêm SlangWord thành công");
        }
        menu();
    }


    //Sửa SlangWord
    public static void SuaSlangWord()
    {
        clearScreen();
        System.out.println("Nhập SlangWord muốn sửa: ");
        String check=word.nextLine();
        check=check.toUpperCase();
        if(!m.containsKey(check))
        {
            System.out.println("SlangWord không tồn tại.");
            pauseScreen();
            menu();

        }
        clearScreen();
        System.out.println("Nhập Definition: ");
        List<String> showCase=m.get(check);
        List<String> rshowCase=new ArrayList<>();
        for(String i:showCase)
        {
            rshowCase.add(i);
        }
        int count=1;
        for(String i: showCase)
        {
            System.out.println(count+"." + i);
            count++;
        }
        System.out.println("Từ bạn muốn thay đổi: ");
        int index=word.nextInt();
        clearScreen();

        System.out.println("Bạn muốn: ");
        System.out.println("1. Ghi đè Definition ");
        System.out.println("2. Xóa Definition ");
        System.out.println("3. Thêm Definition ");
        System.out.println("Tôi muốn: ");
        int chon=word.nextInt();
        String pass=word.nextLine();
        if(chon==1)
        {
            rshowCase.remove(index-1);
            System.out.println("Nhập Definition mới: ");
            String temp=word.nextLine();
            rshowCase.add(temp);
            m.put(check, rshowCase);

        }
        else if(chon==2)
        {
            if(rshowCase.size()==1)
            {
                System.out.println("Không thể xóa");
                pauseScreen();
                menu();

            }
            rshowCase.remove(index-1);
            m.put(check,rshowCase);
        }
        else if(chon==3)
        {
            System.out.println("Nhập Definition mới: ");
            String temp=word.nextLine();
            rshowCase.add(temp);
            m.put(check, rshowCase);

        }
        menu();

    }

    //menu
    public static void menu()
    {
        clearScreen();
        System.out.println("***********MENU************** ");
        System.out.println("1. Tìm kiếm theo SlangWord: *");
        int chon=word.nextInt();
        String pass=word.nextLine();
        if(chon==1) TimkiemSlangWord();
        else
        {
            clearScreen();
            System.exit(0);
        }
    }
    public static void main(String[] args)  {
        GetData();
        GetLichsu();
        menu();
    }
}
