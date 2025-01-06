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
    {   try{
        int depth = 0;
        String str = a.getData();
        for (int i = 0; i < str.length(); i++)
        {
            if (Character.isLetter(str.charAt(i)))
            {
                if(Cell.isCellRef(str.substring(i,i+1))||Cell.isCellRef(str.substring(i,i+2)))
                {   String b_name = Cell.isCellRef(str.substring(i,i+1)) ? str.substring(i,i+1) : str.substring(i,i+2);
                    depth++;
                    int sub_depth = set_depth(cells[Extras.char2num(b_name.charAt(0))][Integer.parseInt(b_name.substring(1))]);
                    if (sub_depth ==-1) {return -1;}
                    return depth+ sub_depth;
                }
            }
        }
        return depth;}
    catch (StackOverflowError e){return -1;}

    }
    /**
     * this method recives a cell and returns the cell referenced in its data (only the first) when used, will be called recursively to return every sub cell.
     * @param cell is used.
     * **/
    public Cell getSubCells(Cell cell)
    {
        for (int i = 0; i < cell.getData().length(); i++)
        {
            if (Character.isLetter(cell.getData().charAt(i))&&(Cell.isCellRef(cell.getData().substring(i,i+1))||Cell.isCellRef(cell.getData().substring(i,i+2))))
                return cells[Extras.char2num(cell.getData().charAt(i))][Integer.parseInt(cell.getData().substring(i+1,i+2))];
        }
        return null;
    }
    public double eValuate(Cell a)
    { double value = 0;
        String str2eval;
        int a_depth = set_depth(a);
        if (Cell.is_form(a.getData())&&a_depth==0)
        {   str2eval = a.getData();
            return Cell.computeFrom(str2eval);
        }
        if (Cell.is_form(a.getData())&&a_depth!=0)
        {   str2eval = a.getData().replace(getSubCells(a).getName(),String.valueOf(eValuate(getSubCells(a))));
            value = Cell.computeFrom(str2eval);
        }
        return value;
    }
    /**
     * this public method evaluate a cell using string refrence (calls the eValuate with the data of the given string cell)
     * **/
    public double eValuate(String cellRef)
    {
        int a = Extras.char2num(cellRef.charAt(0));
        int b =  Integer.parseInt(cellRef.substring(1));
        Cell ab = cells[a][b];
        return eValuate(ab);
    }
    public void eValAll(){
        for (int i = 0; i < 26; i++)
        {
            for (int j = 0; j < 99; j++)
            {
                 cells[i][j].setValue(eValuate(cells[i][j]));
            }
        }
    }

}
