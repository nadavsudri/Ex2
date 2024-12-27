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
    @Test
    void isnumber()
    {
        String[] nums = {"-9873.43","34543.34543","-000000000001","0.010101010101010101"};
        for (String i : nums)
        {
            assertTrue(Cell.isNumber(i));
        }
    }
    @Test
    void istext()
    {
        String[] txt = {"-987@@@3.43","A23","___!","11=22"};
        for (String i : txt) assertTrue(Cell.isText(i));
    }
    @Test
    void isCellref()
    {
        String ref1 = "a2";
        String ref2 = "P33";
        String ref3 = "a22";
        String ref4= "ab2";
        String ref5 = "a222";
        assertTrue(Cell.isCellRef(ref1));
        assertTrue(Cell.isCellRef(ref2));
        assertTrue(Cell.isCellRef(ref3));
        assertFalse(Cell.isCellRef(ref4));
        assertFalse(Cell.isCellRef(ref5));
    }
  
}