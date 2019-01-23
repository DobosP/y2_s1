package Model.DataStructure;

public class HeapAddressIncrementor {
    private static int fileId = 0;

    public static int getNewUniqueId() {
        fileId += 1;
        return fileId;
    }
}
