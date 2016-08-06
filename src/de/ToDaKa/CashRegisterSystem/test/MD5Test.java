package de.ToDaKa.CashRegisterSystem.test;

import de.ToDaKa.CashRegisterSystem.MD5;
import org.junit.Test;

import static de.ToDaKa.CashRegisterSystem.MD5.getMD5;
import static org.junit.Assert.*;

/**
 * Created by Tobias on 05.08.2016.
 */
public class MD5Test {

    @Test
    public void testGetMD5() throws Exception {
        //Test String = Test
        //MD5 String = 0cbc6611f5540bd0809a388dc95a615b

        String input = "Test";
        String result = "0cbc6611f5540bd0809a388dc95a615b";


        assertEquals(result, getMD5(input));
    }
}