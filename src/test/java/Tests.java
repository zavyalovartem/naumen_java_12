import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    void testDecimal(){ assertEquals(Integer.valueOf(50), Integer.decode("50"));}

    @Test
    void testSignedDecimal() { assertEquals(Integer.valueOf(-50), Integer.decode("-50"));}

    @Test
    void testFormatExceptionOnEmptyString() { assertThrows(NumberFormatException.class, () -> Integer.decode(""));}

    @Test
    void testFormatExceptionOnWrongSignCharPosition() {
        assertThrows(NumberFormatException.class, () -> Integer.decode("1-234"));
    }

    @Test
    void testOctalNumber() { assertEquals(Integer.valueOf(81), Integer.decode("0121")); }

    @Test
    void testSignedOctalNumber() { assertEquals(Integer.valueOf(-81), Integer.decode("-0121"));}

    @Test
    void testHexadecimalNumber() { assertEquals(Integer.valueOf(276), Integer.decode("0x114")); }

    @Test
    void testSignedHexadecimalNumber() { assertEquals(Integer.valueOf(-276), Integer.decode("-0x114")); }

    @Test
    void testHexadecimalNumberDifferentSymbol() { assertEquals(Integer.valueOf(1024), Integer.decode("#400"));}

    @Test
    void testFormatExceptionOnWrongInput() { assertThrows(NumberFormatException.class, () -> Integer.decode("ABCD"));}

    @Test
    void testWrongOctalNumber() { assertThrows(NumberFormatException.class, () -> Integer.decode("09121"));}

    @Test
    void testWrongHexadecimalNumber() {assertThrows(NumberFormatException.class, () -> Integer.decode("x0114"));}

    @Test
    void testNumbersWithPositiveSign(){
        assertAll(
                () -> assertEquals(Integer.valueOf(50), Integer.decode("+50")),
                () -> assertEquals(Integer.valueOf(276), Integer.decode("+0x114")),
                () -> assertEquals(Integer.valueOf(1024), Integer.decode("+#400")),
                () -> assertEquals(Integer.valueOf(81), Integer.decode("+0121"))
        );
    }

    @Test
    void testIntegerOverflow() {
        assertAll(
                () -> assertThrows(NumberFormatException.class, () -> Integer.decode("21474836478")),
                () -> assertThrows(NumberFormatException.class, () -> Integer.decode("-21474836478"))
        );
    }
}
