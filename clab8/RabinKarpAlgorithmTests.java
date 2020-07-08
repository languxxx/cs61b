import org.junit.Test;
import static org.junit.Assert.*;

public class RabinKarpAlgorithmTests {
    @Test
    public void basic() {
        String input = "hello";
        String pattern = "ll";
        assertEquals(2, RabinKarpAlgorithm.rabinKarp(input, pattern));
    }
}
