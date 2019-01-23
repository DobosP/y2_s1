package Model.DataStructure;

public class HeapTableEntry {
    private Integer address;
    private Integer value;

    public HeapTableEntry(final int address, final int value) {
        this.address = address;
        this.value = value;
    }

    public Integer getAddress() {
        return this.address;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setAddress(final Integer address) {
        this.address = address;
    }

    public void setValue(final Integer value) {
        this.value = value;
    }
}
