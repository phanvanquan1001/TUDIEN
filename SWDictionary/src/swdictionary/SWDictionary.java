
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swdictionary;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedWriter;

/**
 *
 * @author Minh-Quaa
 */

public class SWDictionary
{
    public final static void clearScreen() {
        System.out.flush();
    }

    public static void pauseScreen(){
        System.out.println("NHAN PHIM BAT KY DE TIEP TUC...");
        new java.util.Scanner(System.in).nextLine();
    }

    public static HashMap<String,List<String>> m=new HashMap<String,List<String>>();
    public static List<String> LichSuSlLangWord=new ArrayList();
    public static Scanner word= new Scanner(System.in);

    //DỌC LỊCH SỬ
    public static void DocLichsu()
    {
        try
     {
        File f=new File("./data/lichsu.txt");
        FileReader fr=new FileReader(f);
        BufferedReader br=new BufferedReader(fr);
        String line;
        while((line=br.readLine())!=null)
        {
            LichSuSlLangWord.add(line);
        }
        fr.close();
        br.close();
    }
    catch (Exception ex)
    {
        System.out.println("ERROR"+ex);
    }
    }

    //ĐỌC FILE
    public static void Docfile(){
     try
     {
        File f=new File("./data/slang.txt");
        FileReader fr=new FileReader(f);
        BufferedReader br=new BufferedReader(fr);
        String line;
        while((line=br.readLine())!=null)
        {
            if (line.contains("`"))
            {
                String[] s=line.split("`");
                String[] tmp=s[1].split("\\|");
                List<String> temp=Arrays.asList(tmp);
                m.put(s[0],temp);
            }
        }
        fr.close();
        br.close();
    }
    catch (Exception ex)
    {
        System.out.println("ERROR"+ex);
    }
    }

    //GHI LỊCH SỬ
    public static void Ghilichsu(){
        try {
            File f = new File("./data/lichsu.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String temp:LichSuSlLangWord) {
                bw.write(temp + "\n");
            }
            fw.close();
            bw.close();
        }
        catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
    }

    //GHI FILE
    public static void Ghifile()
    {
        try {
            File f = new File("./data/slang.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String i: m.keySet())
            {
                fw.write(i +"`");
                List<String> temp=m.get(i);
                for (int t=0;t<temp.size();t++)
                {
                    fw.write(temp.get(t));
                    if (t+1<temp.size()) fw.write("\\|");
                }
                fw.write("\n");
            }
            fw.close();
            bw.close();
        }
        catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
    }

    //TÌM KIẾM Slang Word
    public static void TimkiemSlangWord()
    {
        clearScreen();
        System.out.print("NHAP TU BAN MUON TIM: ");
        String check=word.nextLine();
        check=check.toUpperCase();
        List<String> test=m.get(check);
        LichSuSlLangWord.add(check);
        System.out.println(test);
        pauseScreen();
        menu();
    }

    //TÌM KIẾM Definition
    public static void TimkiemDefinition()
    {
        clearScreen();
        System.out.println("NHAP DEFINITION BAN MUON TIM: ");
        String check=word.nextLine();
        List<String> answer=new ArrayList();
        LichSuSlLangWord.add(check);
        for (String i: m.keySet())
        {
            if (m.get(i).contains(check))
            {
                answer.add(i);
            }
        }
        System.out.println(answer);
        pauseScreen();
        menu();
    }

    //TÌM KIẾM LỊCH SỬ
    static void Timkiemlichsu()
    {
        clearScreen();
        System.out.println("LICH SU TIM KIEM: ");
        for (String temp: LichSuSlLangWord)
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
        System.out.println("NHAP SLANGWORD VAO: ");
        String check=word.nextLine();
        check=check.toUpperCase();
        System.out.println("DEFINITION: ");
        String check1=word.nextLine();
        List<String> t=new ArrayList();
        t.add(check1);
        if (m.containsKey(check))
        {
            System.out.println("NHAP 'O' HOAC 'o' DE OVERWRITE. NHAN PHIM BAT KY DUPLICATE: ");
            String confirm=word.nextLine();
            if (confirm.equals("O") || confirm.equals("o") ) m.put(check,t);
            else
            {
                List<String> i=m.get(check);
                for (String j:i)
                {
                    t.add(j);
                }
                m.put(check,t);
            }
        }
        else
        {
            m.put(check,t);
            System.out.println("THEM MOI SLANGWORD THANH CONG");
        }
        menu();
    }


    //Sửa SlangWord
    static void SuaSlangWord(){
        clearScreen();
        System.out.print("SLANGWORD BAN MUON SUA: ");
        String check=word.nextLine();
        check=check.toUpperCase();
        if (!m.containsKey(check))
        {
            System.out.println("SLANGWORD KHONG TON TAI");
            pauseScreen();
            menu();
        }
        clearScreen();
        System.out.println("DEFINITION: " );

        List<String> showCase=m.get(check);
        List<String> rshowCase=new ArrayList();
        for (String i:showCase)
        {
            rshowCase.add(i);
        }
        int count=1;
        for (String i: showCase)
        {
            System.out.println(count+ "." + i);
            count++;
        }
        System.out.println("CHON TU BAN MUON SUA: ");
        int index=word.nextInt();
        clearScreen();

        System.out.println("VUI LONG CHON: ");
        System.out.println("1. GHI DE DEFINITION ");
        System.out.println("2. XOA DEFINITION ");
        System.out.println("3. THEM DEFINITION ");
        System.out.println("TOI CHON:");
        int Chon=word.nextInt();
        String pass=word.nextLine();
        if (Chon==1) 
        {
            rshowCase.remove(index-1);
            System.out.print("DEFINITION MOI: ");
            String temp=word.nextLine();
            rshowCase.add(temp);
            m.put(check,rshowCase);
        }
        else if (Chon==2)
        {
            if (rshowCase.size()==1) 
            {
                System.out.println("KHONG THE XOA ");
                pauseScreen();
                menu();
            }
            rshowCase.remove(index-1);
            m.put(check,rshowCase);
        }
        else if (Chon==3)
        {
            System.out.print("DEFINITION MOI: ");
            String temp=word.nextLine();
            rshowCase.add(temp);
            m.put(check,rshowCase);
        }
        menu();
    }

    //Xóa Slang Word
    static void XoaSlangWord()
    {
        clearScreen();
        System.out.println("SLANGWORD MUON XOA: ");
        String check=word.nextLine();
        if (m.containsKey(check))
        {
            System.out.println("VUI LONG NHAP 'Y' DE XOA VA NHAN PHIM BAT KY DE THOAT. ");
            String confirm=word.nextLine();
            if (confirm.equals("Y") || confirm.equals("y") ) m.remove(check);
            
        }
        else
        {
            System.out.println("[DL TRONG]");
        }
        menu();
    }

    //Reset vá»� file gá»‘c
    public static void Resetfilegoc()
    {
        clearScreen();
        m.clear();
        try
     {
        File f=new File("./data/filegoc.txt");
        FileReader fr=new FileReader(f);
        BufferedReader br=new BufferedReader(fr);
        String line;
        while((line=br.readLine())!=null)
        {
            if (line.contains("`"))
            {
                String[] s=line.split("`");
                String[] tmp=s[1].split("\\|");
                List<String> temp=Arrays.asList(tmp);
                m.put(s[0],temp);
            }
        }
        fr.close();
        br.close();
    }
    catch (Exception ex)
    {
        System.out.println("ERROR"+ex);
    }
        System.out.println("RESET VE FILE GOC !!!");
        pauseScreen();
        menu();
    }

    //SlangWord ngẫu nhiên
    public static String RandomSlangWord(){
        clearScreen();
        int count=0;
        Random rd=new Random();
        int magicNumber=rd.nextInt(m.size());
        String ans="";
        for (String i: m.keySet())
        {
            if (count==magicNumber)
            {
                ans=i;
                break;
            }else count++;
        }
        return ans;
    }

    //Đố vui Slang Word 
    public static void DovuiSlangWord()
    {
        clearScreen();
        Random Rd = new Random();
        List<String> poll=new ArrayList();

        String word1=RandomSlangWord();
        String qword=word1;

        List<String> w1=m.get(word1);
        word1=w1.get(Rd.nextInt(w1.size()));
        String win=word1;
        poll.add(word1);

        String word2=RandomSlangWord();
        List<String> w2=m.get(word2);
        word2=w2.get(Rd.nextInt(w2.size()));
        poll.add(word2);

        String word3=RandomSlangWord();
        List<String> w3=m.get(word3);
        word3=w3.get(Rd.nextInt(w3.size()));
        poll.add(word3);

        String word4=RandomSlangWord();
        List<String> w4=m.get(word4);
        word4=w4.get(Rd.nextInt(w4.size()));
        poll.add(word4);


        System.out.println("CAU HOI: TIM DEFINITION CHO: " + qword);

        word1=poll.get(Rd.nextInt(poll.size()));
        poll.remove(word1);
        System.out.println("A.  " + word1);

        word2=poll.get(Rd.nextInt(poll.size()));
        poll.remove(word2);
        System.out.println("B.  " + word2);

        word3=poll.get(Rd.nextInt(poll.size()));
        poll.remove(word3);
        System.out.println("C.  " + word3);

        word4=poll.get(Rd.nextInt(poll.size()));
        poll.remove(word4);
        System.out.println("D.  " + word4);

        System.out.println("ĐAP AN CUA BAN LA: ");
        String Chon=word.nextLine();
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^.");

        if ( (Chon.equals("A") || Chon.equals("a")) && word1==win) 
        System.out.println(">>>>>>CHUC MUNG BAN.");

        else if ((Chon.equals("B") || Chon.equals("b")) && word2==win) 
        System.out.println(">>>>>>CHUC MUNG BAN.");

        else if ((Chon.equals("C") || Chon.equals("c")) && word3==win) 
        System.out.println(">>>>>>CHUC MUNG BAN.");
        
        else if ((Chon.equals("D") || Chon.equals("d")) && word4==win) 
        System.out.println(">>>>>>CHUC MUNG BAN.");

        else System.out.println("SAI ROI . ĐAP AN ĐUNG LA : " + win);
        pauseScreen();
        menu();
    }

    //Đố vui Definition 
    public static void DovuiDefinition()
    {
        clearScreen();
        Random Rd = new Random();
        List<String> poll=new ArrayList();

        String word1=RandomSlangWord();
        poll.add(word1);

        String word2=RandomSlangWord();
        poll.add(word2);

        String word3=RandomSlangWord();
        poll.add(word3);

        String word4=RandomSlangWord();
        poll.add(word4);

        List<String> qword=m.get(word1);
        String win=word1;
        System.out.println("CAU HOI: TIM SLANG WORD CHO: " + qword.get(Rd.nextInt(qword.size())));

        word1=poll.get(Rd.nextInt(poll.size()));
        poll.remove(word1);
        System.out.println("A.  " + word1);

        word2=poll.get(Rd.nextInt(poll.size()));
        poll.remove(word2);
        System.out.println("B.  " + word2);

        word3=poll.get(Rd.nextInt(poll.size()));
        poll.remove(word3);
        System.out.println("C.  " + word3);

        word4=poll.get(Rd.nextInt(poll.size()));
        poll.remove(word4);
        System.out.println("D.  " + word4);

        System.out.println("ĐAP AN CUA BAN LA : ");
        String Chon=word.nextLine();

        if ( (Chon.equals("A") || Chon.equals("a")) && word1==win) 
        System.out.println(">>>>>>CHUC MUNG BAN.");

        else if ((Chon.equals("B") || Chon.equals("b")) && word2==win)
         System.out.println(">>>>>>CHUC MUNG BAN.");

        else if ((Chon.equals("C") || Chon.equals("c")) && word3==win)
         System.out.println(">>>>>>CHUC MUNG BAN.");

        else if ((Chon.equals("D") || Chon.equals("d")) && word4==win) 
        System.out.println(">>>>>>CHUC MUNG BAN.");

        else System.out.println(">>>>>>SAI ROI. ĐAP AN ĐUNG LA:  " + win);
        pauseScreen();
        menu();
    }


     

    //menu
    public static void menu()
    {
        clearScreen();
        System.out.println("_________________MENU___________________ ");
        System.out.println("* 1. TIM KIEM THEO SLANGWORD:        *_* ");
        System.out.println("* 2. TIM KIEM THEO DEFINITION :      *_* ");
        System.out.println("* 3. LICH SU TIM KIEM:               *_* ");
        System.out.println("* 4. THEM SLANGWORD:                 *_* ");
        System.out.println("* 5. SUA SLANGWORD:                  *_* ");
        System.out.println("* 6. XOA SLANGWORD:                  *_* ");
        System.out.println("* 7. RESET SLANGWORD GOC:            *_* ");
        System.out.println("* 8. RANDOM SLANGWORD:               *_* ");
        System.out.println("* 9. DO VUI SLANGWORD:               *_* ");
        System.out.println("* 10. DO VUI DEFINITION:             *_* ");
        System.out.println("* 0. NHAP 0 DE KET THUC CHUONG TRINH:*_* ");
        System.out.println("--*--*--*--*--*--*--*--*--*--*--*--*--* ");
        int chon=word.nextInt();
        String pass=word.nextLine();
        if(chon==1) TimkiemSlangWord();
        else if(chon==2) TimkiemDefinition();
        else if(chon==3) Timkiemlichsu();
        else if (chon==4) ThemSlangWord();
        else if (chon==5) SuaSlangWord();
        else if(chon==6) XoaSlangWord();
        else if (chon==7) Resetfilegoc();
        else if(chon==8) 
        {
            String randomSW=RandomSlangWord();
            System.out.println("SLANG WORD LA : ");
            System.out.println(randomSW);
            System.out.println("DEFINTION LA : ");
            List<String> t=m.get(randomSW);
            System.out.println(t);
            pauseScreen();
            menu();
        }
        else if(chon==9) DovuiSlangWord();
        else if (chon==10) DovuiDefinition();
        else 
        {
            clearScreen();
            Ghifile();
            Ghilichsu();
            System.exit(0);
         
        }
           
    }
    public static void main(String[] args)  {
        
        Docfile();
        DocLichsu();
        menu();
    }
}

