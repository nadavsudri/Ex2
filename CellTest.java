import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CellTest {
    @Test
    void isform()
    {
        String f1 = "=12+a3+34+(13-12)";
        String f2 = "=12+a3+34+(13-12";
        String f3 = "=12++a3+34+(13-12)";
        String f4 = "=12//a3+34#+(((13-1))2)";
        String[] strings = {f1, f2, f3, f4};
        int right = 0;
        int wrong =0;
        for (int i = 0; i < 4; i++)
        {
            if (Cell.is_form(strings[i]))right++;
            else wrong++;
            System.out.println(Cell.is_form(strings[i]));

        }
        assertEquals(right+2,wrong);
    }
  
}