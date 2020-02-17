package at.ac.tuwien.ifs.Fits;

import edu.harvard.hul.ois.fits.Fits;
import edu.harvard.hul.ois.fits.FitsOutput;
import edu.harvard.hul.ois.fits.exceptions.FitsConfigurationException;
import edu.harvard.hul.ois.fits.exceptions.FitsException;

import javax.xml.stream.XMLStreamException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class FitsAdaptor implements AutoCloseable {

    private Fits fits;

    private String fitsHome;

    public void init() throws IOException {
        try {
            if (fitsHome != null) {
                fitsHome = fitsHome.endsWith(File.separator) ? fitsHome : fitsHome + File.separator;
                fits = new Fits(fitsHome);
            }
        } catch (FitsConfigurationException e) {
            throw new IOException(e);
        }
    }

    public String process(String path) throws IOException {
        try {
            FitsOutput examine = fits.examine(new File(path));

            examine.addStandardCombinedFormat();
            OutputStream outStream = new ByteArrayOutputStream();
            examine.output(outStream);
            String outputString = outStream.toString();
            return outputString;
        } catch (FitsException | XMLStreamException e) {
            throw new IOException(e);
        }
    }

    public String getFitsHome() {
        return fitsHome;
    }

    public void setFitsHome(String fitsHome) {
        this.fitsHome = fitsHome;
    }

    @Override
    public void close() throws Exception {
        fits = null;
    }
}
