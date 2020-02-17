package at.ac.tuwien.ifs;

import at.ac.tuwien.ifs.requests.FitsRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class RestTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/rest/hello")
                .then()
                .statusCode(200)
                .body(is("hello"));
    }


    @Test
    public void testFitsEndpoint() throws IOException {

        String readmePath = getClass().getClassLoader().getResource("README.md").getPath();

        File file = new File(readmePath);
        String fileName = file.getName();

        FitsRequest request = new FitsRequest();
        request.setFileName(fileName);
        FileInputStream fileInputStream = new FileInputStream(file);

        byte[] payload = IOUtils.toByteArray(fileInputStream);

        request.setContent(new String(payload, StandardCharsets.UTF_8));


        Response extract = given()
                .body(request)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when()
                .post("/rest/process")
                .then()
                .statusCode(200).extract().response();

        System.out.println(extract.asString());
    }

    @Test
    void testEcho() throws JsonProcessingException {

        MultipartBody body = new MultipartBody();
        body.fileName = "greeting.txt";
        body.file = new ByteArrayInputStream("HELLO WORLD".getBytes(StandardCharsets.UTF_8));

        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(body);

        given().body(body)
                .when().post("/rest/echo")
                .then()
                .statusCode(200)
                .body(is("hello"));

    }

}