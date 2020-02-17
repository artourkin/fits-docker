package at.ac.tuwien.ifs.Fits;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class FitsAdaptorTest {

    @Test
    void process() throws IOException {
        FitsAdaptor fitsAdaptor = new FitsAdaptor();
        fitsAdaptor.setFitsHome("/Users/artur/rnd/software/fits-1.5.0/");
        fitsAdaptor.init();
        String path = getClass().getClassLoader().getResource("README.md").getPath();
        String process = fitsAdaptor.process(path);
        System.out.println(process);
        assertTrue(process.contains("Tika"));
    }
}