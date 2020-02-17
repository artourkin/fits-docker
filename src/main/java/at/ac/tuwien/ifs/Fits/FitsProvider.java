package at.ac.tuwien.ifs.Fits;


import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;

@ApplicationScoped
public class FitsProvider {

    public String ping() {
        return "amazing";
    }


    public FitsAdaptor getFits() throws IOException {
        FitsAdaptor adaptor = new FitsAdaptor();
        adaptor.setFitsHome("/Users/artur/rnd/software/fits-1.5.0/");
        adaptor.init();
        return adaptor;
    }

}
