package Model.DataStructure;

import java.io.File;

public class FileTableEntry {
    private Integer id;
    private String filename;

    public FileTableEntry(final Integer id, final String filename) {
        this.id = id;
        this.filename = filename;
    }

    public Integer getId() {
        return this.id;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public void setFilename(final String filename) {
        this.filename = filename;
    }
}
