package Model.DataStructure;

public class SymTableEntry {
    private String name;
    private Integer value;

    public SymTableEntry(final String name, final Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setValue(final Integer value) {
        this.value = value;
    }
}
