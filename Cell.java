public class Cell {
    private String data="";
    private int row;
    private char col;
    private String ref="";
    private final int depth = 0;
    private Cell previousCell;
    public Cell(String data, int row, char col) {
        this.data = data;
        this.row = row;
        this.col = col;
    }
    public Cell(int row, char col) {
        this.row = row;
        this.col = col;
    }
    public Cell(Cell c) {
        this.data=c.data;
        this.row=c.row;
        this.col=c.col;
    }
    public String toString() {
        return data+" "+col+row;
    }
    private static int inst_counter(String str, char s)
    {
        int counter=0;
        for (char ch : str.toCharArray())
        {if (ch==s)counter++;}
        return counter;
    }
    private static boolean is_opt(char c)
    {
        return c=='+' || c=='-' || c=='*' || c=='/';
    }
    private static boolean cont_invalid(String str)
    {
        String vaild = "=.QWERTYUIOPASDFGHJKLZXCVBNMasdfghjklqwertyuiopzxcvbnm1234567890+/-*()";
        for (char ch : str.toCharArray())
        {
            if (vaild.indexOf(ch)==-1)return false;
        }
        return true;
    }

    public static boolean is_form(String str) // fails when a1.1 is enterd (cell ref with dec point)
    {
        boolean ans = true;
        if (str == null){return false;}
        if (!cont_invalid(str)) return false;
        if (!str.startsWith("="))return false;
        if (str.substring(1).contains("="))return false;
        if(Character.isLetter(str.charAt(str.length()-1))){return false;}
        if(inst_counter(str,'(')!=inst_counter(str,')')){return false;}
        for (int i =0;i<str.length()-1;i++)
        {
            boolean notdigit = !Character.isDigit(str.charAt(i + 1));
            if (str.charAt(i)=='.'&str.charAt(i+1)=='.'){return false;}
            if (str.charAt(i+1)=='.'&!Character.isDigit(str.charAt(i))){return false;}
            if (Character.isLetter(str.charAt(i))&& notdigit)return false;
            if ((is_opt(str.charAt(i))&&!Character.isLetter(str.charAt(i+1)))&&(is_opt(str.charAt(i))&& notdigit)&&str.charAt(i+1)!='(') return false;
        }
        return ans;
    }


}
