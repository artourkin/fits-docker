package at.ac.tuwien.ifs.requests;


public class FitsRequest {
    private String fileName;
    private String content;


    public FitsRequest() {
    }

    public FitsRequest(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
