import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SpreadSheet sheet = new SpreadSheet();
        sheet.cells[1][1].setData("a2+a5");
        sheet.cells[1][2].setData("a3+4");
        sheet.cells[1][3].setData("a4+4");
        System.out.println(sheet.cells[1][2].getName());
        System.out.println(sheet.set_depth(sheet.cells[1][1]));

    }
}
