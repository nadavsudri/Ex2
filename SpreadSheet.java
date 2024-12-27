import javax.swing.*;
import java.awt.*;

public class SpreadSheet
{
    Cell [][] cells  = new Cell[26][99];

public SpreadSheet ()
{
    for (int i = 0; i < 26; i++)
    {
        for (int j = 0; j < 99; j++)
        {
            cells[i][j] = new Cell(i,j);
            cells[i][j].setName("" + Extras.int2_char(i)+j);
        }
    }
}

    public  int set_depth (Cell a)
    {   int depth = 0;
        String str = a.getData();
        for (int i = 0; i < str.length(); i++)
        {
            if (Character.isLetter(str.charAt(i)))
            {
                if(Cell.isCellRef(str.substring(i,i+1))||Cell.isCellRef(str.substring(i,i+2)))
                {   String b_name = Cell.isCellRef(str.substring(i,i+1)) ? str.substring(i,i+1) : str.substring(i,i+2);
                    depth++;
                    return depth+ set_depth(cells[Extras.char2num(b_name.charAt(0))][Integer.parseInt(b_name.substring(1))]);
                }
            }
        }
        return depth;

    }

}
