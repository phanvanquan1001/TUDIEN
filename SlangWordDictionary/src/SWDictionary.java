
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

public class SWDictionary
{
    public final static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pauseScreen(){
        System.out.println("Nhấn phím bất kỳ để tiếp tục...");
        new java.util.Scanner(System.in).nextLine();
    }

    public static HashMap<String,List<String>> m=new HashMap<String,List<String>>();
    public static List<String> LichSuSlLangWord=new ArrayList();
    public static Scanner word= new Scanner(System.in);

    public static void GetHistory()
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

    public static void GetData(){
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

    //1.tìm kiếm Slang Word
    public static void TimkiemSlangWord()
    {
        clearScreen();
        System.out.print("Nhập từ bạn muốn tìm: ");
        String check=word.nextLine();
        check=check.toUpperCase();
        List<String> test=m.get(check);
        LichSuSlLangWord.add(check);
        System.out.println(test);
        pauseScreen();
        menu();
    }

    //2.tìm kiếm Definition
    public static void TimkiemDefinition()
    {
        clearScreen();
        System.out.println("Nhập Definition bạn muốn tìm: ");
        String check=word.nextLine();
        LichSuSlLangWord.add(check);
        List<String> answer=new ArrayList();
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

    //3.Tìm kiếm lich sử
    static void Timkiemlichsu()
    {
        clearScreen();
        System.out.println("Lịch sử tìm kiếm: ");
        for (String temp: LichSuSlLangWord)
        {
            System.out.println(temp);
        }
        pauseScreen();
        menu();
    }

    //4.Thêm SlangWord
    public static void ThemSlangWord()
    {
        clearScreen();
        System.out.println("Nhập Slang Word mới để thêm: ");
        String check=word.nextLine();
        check=check.toUpperCase();
        System.out.println("definition: ");
        String check1=word.nextLine();
        List<String> t=new ArrayList();
        t.add(check1);
        if (m.containsKey(check))
        {
            System.out.println("Nhập 'O' hoặc 'o' để OVERWRITE. Nhấn phím bất kỳ để DUPLICATE: ");
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
            System.out.println("Thêm mới Slang Word thành công");
        }
        menu();
    }


    //5.Sửa SlangWord
    static void SuaSlangWord(){
        clearScreen();
        System.out.print("Slangword bạn muốn sửa: ");
        String check=word.nextLine();
        check=check.toUpperCase();
        if (!m.containsKey(check))
        {
            System.out.println("Slangword không tồn tại");
            pauseScreen();
            menu();
        }
        clearScreen();
        System.out.println("definition: " );

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
        System.out.println("Chọn từ bạn muốn thay đổi: ");
        int index=word.nextInt();
        clearScreen();

        System.out.println("Vui lòng chọn: ");
        System.out.println("1. ghi đè Definition ");
        System.out.println("2. Xóa Definition ");
        System.out.println("3. Thêm Definition ");
        System.out.println("Tôi chọn:");
        int Chon=word.nextInt();
        String pass=word.nextLine();
        if (Chon==1) 
        {
            rshowCase.remove(index-1);
            System.out.print("Definition mới: ");
            String temp=word.nextLine();
            rshowCase.add(temp);
            m.put(check,rshowCase);
        }
        else if (Chon==2)
        {
            if (rshowCase.size()==1) 
            {
                System.out.println("Không thể xóa ");
                pauseScreen();
                menu();
            }
            rshowCase.remove(index-1);
            m.put(check,rshowCase);
        }
        else if (Chon==3)
        {
            System.out.print("Definition mới: ");
            String temp=word.nextLine();
            rshowCase.add(temp);
            m.put(check,rshowCase);
        }
        menu();
    }

    //6.Xóa Slang Word
    static void XoaSlangWord()
    {
        clearScreen();
        System.out.println("Slangword muốn xóa: ");
        String check=word.nextLine();
        if (m.containsKey(check))
        {
            System.out.println("Bạn muốn xóa không: (Y/N) ");
            String confirm=word.nextLine();
            if (confirm.equals("Y") || confirm.equals("y") ) m.remove(check);
        }
        pauseScreen();
        menu();
    }

    //7.Reset về file gốc
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
        System.out.println("Reset về file gốc !!!");
        pauseScreen();
        menu();
    }

    //8.SlangWord Ngẫu nhiên
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

    //9.Đố vui Slang Word 
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


        System.out.println("Câu hỏi: tìm Definition cho " + qword);

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

        System.out.println("Đáp án của bạn là: ");
        String Chon=word.nextLine();
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^.");

        if ( (Chon.equals("A") || Chon.equals("a")) && word1==win) 
        System.out.println("Chúc mừng bạn.");
        else if ((Chon.equals("B") || Chon.equals("b")) && word2==win) 
        System.out.println("Chúc mừng bạn.");
        else if ((Chon.equals("C") || Chon.equals("c")) && word3==win) 
        System.out.println("Chúc mừng bạn.");
        else if ((Chon.equals("D") || Chon.equals("d")) && word4==win) 
        System.out.println("Chúc mừng bạn.");
        else System.out.println("Sai rồi . Đáp án đúng là: " + win);
        pauseScreen();
        menu();
    }

    //10.Đố vui Definition 
    public static void DovuiDefinition()
    {
        clearScreen();
        Random rand = new Random();
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
        System.out.println("Câu hỏi: Tìm Slang Word cho " + qword.get(rand.nextInt(qword.size())));
        word1=poll.get(rand.nextInt(poll.size()));
        poll.remove(word1);
        System.out.println("A.  " + word1);
        word2=poll.get(rand.nextInt(poll.size()));
        poll.remove(word2);
        System.out.println("B.  " + word2);
        word3=poll.get(rand.nextInt(poll.size()));
        poll.remove(word3);
        System.out.println("C.  " + word3);
        word4=poll.get(rand.nextInt(poll.size()));
        poll.remove(word4);
        System.out.println("D.  " + word4);
        System.out.println("Đáp án của bạn là: ");
        String Chon=word.nextLine();
        if ( (Chon.equals("A") || Chon.equals("a")) && word1==win) 
        System.out.println("Chúc mừng bạn.");
        else if ((Chon.equals("B") || Chon.equals("b")) && word2==win)
         System.out.println("Chúc mừng bạn.");
        else if ((Chon.equals("C") || Chon.equals("c")) && word3==win)
         System.out.println("Chúc mừng bạn.");
        else if ((Chon.equals("D") || Chon.equals("d")) && word4==win) 
        System.out.println("Chúc mừng bạn.");
        else System.out.println("Sai rồi . Đáp án đúng là:  " + win);
        pauseScreen();
        menu();
    }

    //Update Lịch Sử
    public static void updateLLichSu(){
        try {
            File f = new File("./data/lichsu.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String temp : LichSuSlLangWord) {
                fw.write(temp + "\n");
            }
            fw.close();
            bw.close();
        }
        catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
    }

    //Update File
    public static void updateFile()
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
                    if (t+1<temp.size()) fw.write("|");
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


    


    //menu
    public static void menu()
    {
        clearScreen();
        System.out.println("_________________MENU___________________ ");
        System.out.println("* 1. Tìm kiếm theo SlangWord:        *_* ");
        System.out.println("* 2. Tìm kiếm theo Definition:       *_* ");
        System.out.println("* 3. Lịch sử tìm kiếm:               *_* ");
        System.out.println("* 4. Thêm SlangWord:                 *_* ");
        System.out.println("* 5. Sửa SlangWord:                  *_* ");
        System.out.println("* 6. Xóa SlangWord:                  *_* ");
        System.out.println("* 7. Reset SlangWord gốc:            *_* ");
        System.out.println("* 8. Random SlangWord:               *_* ");
        System.out.println("* 9. Đố vui SlangWord:               *_* ");
        System.out.println("* 10. Đố vui Definition:             *_* ");
        System.out.println("* 11. Xóa lịch sử:                   *_*");
        System.out.println("* 0. Nhấn 0 để kết thúc chương trình:*_* ");
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
            System.out.println("SlangWord là: ");
            System.out.println(randomSW);
            System.out.println("Deefinition là: ");
            List<String> t=m.get(randomSW);
            System.out.println(t);
            pauseScreen();
            menu();
        }
        else if(chon==9) DovuiSlangWord();
        else if (chon==10) DovuiDefinition();
        else if (chon==11)
        {
            clearScreen();
            System.out.println("Xóa lịch sử !");
            LichSuSlLangWord.clear();
            pauseScreen();
            menu();
        }
        else
        {
            clearScreen();
            updateLLichSu();
            updateFile();
            System.exit(0);
        }
    }
    public static void main(String[] args)  {
        GetData();
        GetHistory();
        menu();
    }
}
