package Model.DataStructure;

import java.io.BufferedReader;

public class FileTableValue {

    private String filename;
    private BufferedReader fileDescriptor;

    public FileTableValue(final String filename, final BufferedReader fileDescriptor) {
        this.filename = filename;
        this.fileDescriptor = fileDescriptor;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(final String filename) {
        this.filename = filename;
    }

    public BufferedReader getFileDescriptor() {
        return fileDescriptor;
    }

    public void setFileDescriptor(final BufferedReader fileDescriptor) {
        this.fileDescriptor = fileDescriptor;
    }

    public String toString() {
        return "Filename: " + filename;
    }

}
