
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
import java.io.IOException;

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

    public static HashMap<String,List<String>> Hm=new HashMap<String,List<String>>();
    public static List<String> LichSu=new ArrayList();
    public static Scanner word= new Scanner(System.in);

    //DỌC LỊCH SỬ
    public static void DocLichsu()
    {
        try
     {
        File f=new File("./data/lichsu.txt");
        BufferedReader br;
            try (FileReader fr = new FileReader(f)) {
                br = new BufferedReader(fr);
                String line;
                while((line=br.readLine())!=null)
                {
                    LichSu.add(line);
                }   }
        br.close();
    }
    catch (IOException ex)
    {
        System.out.println("ERROR"+ex);
    }
    }

    //ĐỌC FILE
    public static void Docfile(){
     try
     {
        File f=new File("./data/slang.txt");
        BufferedReader br;
         try (FileReader fr = new FileReader(f)) {
             br = new BufferedReader(fr);
             String line;
             while((line=br.readLine())!=null)
             {
                 if (line.contains("`"))
                 {
                     String[] s=line.split("`");
                     String[] tmp=s[1].split("\\|");
                     List<String> temp=Arrays.asList(tmp);
                     Hm.put(s[0],temp);
                 }
             }}
        br.close();
    }
    catch (IOException ex)
    {
        System.out.println("ERROR"+ex);
    }
    }

    //GHI LỊCH SỬ
    public static void Ghilichsu(){
        try {
            File f = new File("./data/lichsu.txt");
            BufferedWriter bw;
            try (FileWriter fw = new FileWriter(f)) {
                bw = new BufferedWriter(fw);
                for (String temp:LichSu) {
                    bw.write(temp + "\n");
                }
            }
            bw.close();
        }
        catch (IOException ex) {
            System.out.println("Error: "+ex);
        }
    }

    //GHI FILE
    public static void Ghifile()
    {
        try {
            File f = new File("./data/slang.txt");
            BufferedWriter bw;
            try (FileWriter fw = new FileWriter(f)) {
                bw = new BufferedWriter(fw);
                for (String i: Hm.keySet())
                {
                    fw.write(i +"`");
                    List<String> temp=Hm.get(i);
                    for (int t=0;t<temp.size();t++)
                    {
                        fw.write(temp.get(t));
                        if (t+1<temp.size()) fw.write("\\|");
                    }
                    fw.write("\n");
                }
            }
            bw.close();
        }
        catch (IOException ex) {
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
        List<String> test=Hm.get(check);
        LichSu.add(check);
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
        LichSu.add(check);
        Hm.keySet().stream().filter((i) -> (Hm.get(i).contains(check))).forEachOrdered((i) -> {
            answer.add(i);
        });
        System.out.println(answer);
        pauseScreen();
        menu();
    }

    //LỊCH SỬ TÌM KIẾM
    static void Lichsutimkiem()
    {
        clearScreen();
        System.out.println("LICH SU TIM KIEM: ");
        LichSu.forEach((temp) -> {
            System.out.println(temp);
        });
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
        if (Hm.containsKey(check))
        {
            System.out.println("NHAP 'O' HOAC 'o' DE OVERWRITE. NHAN PHIM BAT KY DUPLICATE: ");
            String confirm=word.nextLine();
            if (confirm.equals("O") || confirm.equals("o") ) Hm.put(check,t);
            else
            {
                List<String> i=Hm.get(check);
                i.forEach((j) -> {
                    t.add(j);
                });
                Hm.put(check,t);
            }
        }
        else
        {
            Hm.put(check,t);
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
        if (!Hm.containsKey(check))
        {
            System.out.println("SLANGWORD KHONG TON TAI");
            pauseScreen();
            menu();
        }
        clearScreen();
        System.out.println("DEFINITION: " );

        List<String> showCase=Hm.get(check);
        List<String> rshowCase=new ArrayList();
        showCase.forEach((i) -> {
            rshowCase.add(i);
        });
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
        System.out.println("2. THEM DEFINITION ");
        System.out.println("TOI CHON:");
        int Chon=word.nextInt();
        String pass=word.nextLine();
        if (Chon==1) 
        {
            rshowCase.remove(index-1);
            System.out.print("DEFINITION MOI: ");
            String temp=word.nextLine();
            rshowCase.add(temp);
            Hm.put(check,rshowCase);
        }
        else if (Chon==2)
        {
            System.out.print("DEFINITION MOI: ");
            String temp=word.nextLine();
            rshowCase.add(temp);
            Hm.put(check,rshowCase);
        }
        menu();
    }

    //Xóa Slang Word
    static void XoaSlangWord()
    {
        clearScreen();
        System.out.println("SLANGWORD MUON XOA: ");
        String check=word.nextLine();
        if (Hm.containsKey(check))
        {
            System.out.println("VUI LONG NHAP 'Y' DE XOA VA NHAN PHIM BAT KY DE THOAT. ");
            String confirm=word.nextLine();
            if (confirm.equals("Y") || confirm.equals("y") ) Hm.remove(check);
            
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
        Hm.clear();
        try
     {
        File f=new File("./data/filegoc.txt");
        BufferedReader br;
            try (FileReader fr = new FileReader(f)) {
                br = new BufferedReader(fr);
                String line;
                while((line=br.readLine())!=null)
                {
                    if (line.contains("`"))
                    {
                        String[] s=line.split("`");
                        String[] tmp=s[1].split("\\|");
                        List<String> temp=Arrays.asList(tmp);
                        Hm.put(s[0],temp);
                    }
                }   }
        br.close();
    }
    catch (IOException ex)
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
        int ngaunhien=rd.nextInt(Hm.size());
        String ans="";
        for (String i: Hm.keySet())
        {
            if (count==ngaunhien)
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
        String SL1=RandomSlangWord();
        String CH=SL1;
        List<String> S1=Hm.get(SL1);
        SL1=S1.get(Rd.nextInt(S1.size()));
        String win=SL1;
        poll.add(SL1);
        String SL2=RandomSlangWord();
        List<String> S2=Hm.get(SL2);
        SL2=S2.get(Rd.nextInt(S2.size()));
        poll.add(SL2);
        String SL3=RandomSlangWord();
        List<String> S3=Hm.get(SL3);
        SL3=S3.get(Rd.nextInt(S3.size()));
        poll.add(SL3);
        String SL4=RandomSlangWord();
        List<String> S4=Hm.get(SL4);
        SL4=S4.get(Rd.nextInt(S4.size()));
        poll.add(SL4);


        System.out.println("CAU HOI: TIM DEFINITION CHO: " + CH);
        SL1=poll.get(Rd.nextInt(poll.size()));
        poll.remove(SL1);
        System.out.println("A.  " + SL1);
        SL2=poll.get(Rd.nextInt(poll.size()));
        poll.remove(SL2);
        System.out.println("B.  " + SL2);
        SL3=poll.get(Rd.nextInt(poll.size()));
        poll.remove(SL3);
        System.out.println("C.  " + SL3);
        SL4=poll.get(Rd.nextInt(poll.size()));
        poll.remove(SL4);
        System.out.println("D.  " + SL4);
        System.out.println("DAP AN CUA BAN LA: ");
        String Chon=word.nextLine();
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^.");

        if ( (Chon.equals("A") || Chon.equals("a")) && SL1==win) 
        System.out.println(">>>>>>CHUC MUNG BAN.");
        else if ((Chon.equals("B") || Chon.equals("b")) && SL2==win) 
        System.out.println(">>>>>>CHUC MUNG BAN.");
        else if ((Chon.equals("C") || Chon.equals("c")) && SL3==win) 
        System.out.println(">>>>>>CHUC MUNG BAN.");
        else if ((Chon.equals("D") || Chon.equals("d")) && SL4==win) 
        System.out.println(">>>>>>CHUC MUNG BAN.");
        else System.out.println("SAI ROI . DAP AN DUNG LA : " + win);
        pauseScreen();
        menu();
    }

    //Đố vui Definition 
    public static void DovuiDefinition()
    {
        clearScreen();
        Random Rd = new Random();
        List<String> poll=new ArrayList();
        String DF1=RandomSlangWord();
        poll.add(DF1);
        String DF2=RandomSlangWord();
        poll.add(DF2);
        String DF3=RandomSlangWord();
        poll.add(DF3);
        String DF4=RandomSlangWord();
        poll.add(DF4);
        List<String> CH=Hm.get(DF1);
        String win=DF1;
        System.out.println("CAU HOI: TIM SLANG WORD CHO: " + CH.get(Rd.nextInt(CH.size())));
        DF1=poll.get(Rd.nextInt(poll.size()));
        poll.remove(DF1);
        System.out.println("A.  " + DF1);
        DF2=poll.get(Rd.nextInt(poll.size()));
        poll.remove(DF2);
        System.out.println("B.  " + DF2);
        DF3=poll.get(Rd.nextInt(poll.size()));
        poll.remove(DF3);
        System.out.println("C.  " + DF3);
        DF4=poll.get(Rd.nextInt(poll.size()));
        poll.remove(DF4);
        System.out.println("D.  " + DF4);
        System.out.println("DAP AN CUA BAN LA : ");
        String Chon=word.nextLine();
        if ( (Chon.equals("A") || Chon.equals("a")) && DF1==win) 
        System.out.println(">>>>>>CHUC MUNG BAN.");
        else if ((Chon.equals("B") || Chon.equals("b")) && DF2==win)
         System.out.println(">>>>>>CHUC MUNG BAN.");
        else if ((Chon.equals("C") || Chon.equals("c")) && DF3==win)
         System.out.println(">>>>>>CHUC MUNG BAN.");
        else if ((Chon.equals("D") || Chon.equals("d")) && DF4==win) 
        System.out.println(">>>>>>CHUC MUNG BAN.");
        else System.out.println(">>>>>>SAI ROI. DAP AN DUNG LA:  " + win);
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
        System.out.println("* 10.DO VUI DEFINITION:              *_* ");
        System.out.println("* 0. NHAP 0 DE KET THUC CHUONG TRINH:*_* ");
        System.out.println("--*--*--*--*--*--*--*--*--*--*--*--*--* ");
        int chon=word.nextInt();
        String pass=word.nextLine();
        switch (chon) {
            case 1:
                TimkiemSlangWord();
                break;
            case 2:
                TimkiemDefinition();
                break;
            case 3:
                Lichsutimkiem();
                break;
            case 4:
                ThemSlangWord();
                break;
            case 5:
                SuaSlangWord();
                break;
            case 6:
                XoaSlangWord();
                break;
            case 7:
                Resetfilegoc();
                break;
            case 8:
                String randomSW=RandomSlangWord();
                System.out.println("SLANG WORD LA : "+ randomSW);
                List<String> t=Hm.get(randomSW);
                System.out.println("DEFINTION LA : " + t);
                pauseScreen();
                menu();
                break;
            case 9:
                DovuiSlangWord();
                break;
            case 10:
                DovuiDefinition();
                break;
            default:
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

