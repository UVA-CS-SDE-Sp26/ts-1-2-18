import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;




class ProgramControlTest {
    private ProgramControl pc;
    private FileHandler fh;

    /*
    @BeforeEach
    void setUp(){
    fh = new FileHandler();
    pc = new ProgramControl(fh);

    }

    @Test
    void testNoArguments(){
        String[] args = {};
        String result = pc.handleRequest(args);
        assertNotNull(result);
        assertTrue(result.contains("01"));

    }



    @Test
    void testOneArgument(){
        String[] args = {"01"};
        String result = pc.handleRequest(args);
        assertNotNull(result);

    }

    @Test
    void testTwoArguments(){
        String[] args = {"01","key.txt"};
        String result = pc.handleRequest(args);
        assertNotNull(result);

    }
    @Test
    void invalidArgument(){
        String[] args = {"abc"};
        String result = pc.handleRequest(args);
        assertEquals("Please enter a valid file number", result);

    }

    @Test
    void indexOutOfRange(){
        String[] args = {"60"};
        String result = pc.handleRequest(args);
        assertEquals("File 60 is out of range.", result);

    }
    */
}