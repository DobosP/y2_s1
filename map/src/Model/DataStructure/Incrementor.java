package Model.DataStructure;

public class Incrementor {
    private static int fileId = -1;

    public static int getNewFileId() {
        fileId += 1;
        return fileId;
    }
}
