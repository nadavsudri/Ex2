import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SpreadSheet sheet = new SpreadSheet();
        sheet.cells[1][1].setData("=15");
        sheet.cells[1][2].setData("=2*a1+a1-a1");
       // System.out.println(sheet.eValuate("a2"));
       // System.out.println(sheet.eValuate("a3"));
      //  System.out.println(sheet.set_depth(sheet.cells[1][3]));
        System.out.println(sheet.eValuate("a2"));

    }
}
