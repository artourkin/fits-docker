package at.ac.tuwien.ifs;

import at.ac.tuwien.ifs.Fits.FitsAdaptor;
import at.ac.tuwien.ifs.Fits.FitsProvider;
import at.ac.tuwien.ifs.requests.FitsRequest;
import org.apache.commons.io.FileUtils;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.nio.file.Files;

@Path("/rest")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Rest {

    @Inject
    FitsProvider fitsProvider;


    @POST
    @Path("/process")
    public String process(FitsRequest request) throws Exception {
        byte[] content = request.getContent().getBytes();
        String fileName = request.getFileName();
        File file = new File(fileName);
        FileUtils.writeByteArrayToFile(file, content);

        FitsAdaptor fitsAdaptor = fitsProvider.getFits();
        String process = fitsAdaptor.process(file.getAbsolutePath());

        Files.delete(file.toPath());

        fitsAdaptor.close();

        return process;
    }
}