import java.util.ArrayList;

public class Cell {
    private String data = "";
    private String name = "";
    private double value;
    private int row;
    private int col;
    private final int depth = 0;

    public Cell() {
    }

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int name2row() {
        return Integer.parseInt(name.substring(1, name.length() - 1));
    }

    public int name2col() {
        return Extras.char2num(name.charAt(0));
    }

    private static int inst_counter(String str, char s) {
        int counter = 0;
        for (char ch : str.toCharArray()) {
            if (ch == s) counter++;
        }
        return counter;
    }

    private static boolean is_opt(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean cont_invalid(String str) {
        String vaild = "=.QWERTYUIOPASDFGHJKLZXCVBNMasdfghjklqwertyuiopzxcvbnm1234567890+/-*()";
        for (char ch : str.toCharArray()) {
            if (vaild.indexOf(ch) == -1) return false;
        }
        return true;
    }

    public static boolean isNumber(String str) {
        try {
            double s = Double.parseDouble(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isText(String str) {
        return !isNumber(str) && !str.startsWith("=");
    }

    public static boolean is_form(String str) // fails when a1.1 is enterd (cell ref with dec point)
    {
        boolean ans = true;
        if (str == null) {
            return false;
        }
        if (!cont_invalid(str)) return false;
        if (!str.startsWith("=")) return false;
        if (str.substring(1).contains("=")) return false;
        if (Character.isLetter(str.charAt(str.length() - 1))) {
            return false;
        }
        if (inst_counter(str, '(') != inst_counter(str, ')')) {
            return false;
        }
        for (int i = 0; i < str.length() - 1; i++) {
            boolean notdigit = !Character.isDigit(str.charAt(i + 1));
            if (str.charAt(i) == '.' & str.charAt(i + 1) == '.') {
                return false;
            }
            if (str.charAt(i + 1) == '.' & !Character.isDigit(str.charAt(i))) {
                return false;
            }
            if (Character.isLetter(str.charAt(i)) && notdigit) return false;
            if ((is_opt(str.charAt(i)) && !Character.isLetter(str.charAt(i + 1))) && (is_opt(str.charAt(i)) && notdigit) && str.charAt(i + 1) != '(')
                return false;
        }
        return ans;
    }

    public static boolean isCellRef(String str) {
        if (str == null) {
            return false;
        }
        if (str.length() > 3 || str.length() < 2) {
            return false;
        }
        if (Character.isLetter(str.charAt(0))) {
            if (Character.isDigit(str.charAt(1)) && str.length() == 2) {
                return true;
            }
            if (str.length() == 3 && Character.isDigit(str.charAt(1)) && Character.isDigit(str.charAt(2))) {
                return true;
            }

        }
        return false;

    }

    /**
     * this function inputs a String @param str, and uses recursion to calculate each part of the string based on basic math rules.
     * first, parentheses are looked for, if exists, remove them and evaluating the phrase inside them.
     * later checks for addition and subtraction as well as multipication and division.
     * finnaly returning the final String as Double.
     **/
    public static double computeFrom(String str) {
        str = str.replaceAll("\\s", ""); // remove all spaces
        str = str.replaceAll("=", ""); // remove all spaces


        if (str.contains("(")) {
            int open = str.lastIndexOf('(');
            int close = str.indexOf(')', open);
            String inside = str.substring(open + 1, close);
            double value = computeFrom(inside);
            return computeFrom(str.substring(0, open) + value + str.substring(close + 1));
        }

        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (c == '+' || c == '-') {
                double left = computeFrom(str.substring(0, i));
                double right = computeFrom(str.substring(i + 1));
                if (c == '+') return left + right;
                else return left - right;
            }
        }

        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (c == '*' || c == '/') {
                double left = computeFrom(str.substring(0, i));
                double right = computeFrom(str.substring(i + 1));
                if (c == '*') return left * right;
                else return left / right;
            }
        }
        return Double.parseDouble(str);
    }

    public void eval() {
        this.value = computeFrom(data);


    }
}






