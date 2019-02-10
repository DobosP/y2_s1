package domain.adt;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class FX2arg<T1,T2> {
    private final SimpleObjectProperty<T1> name=new SimpleObjectProperty<T1>();
    private final SimpleObjectProperty<T2> value=new SimpleObjectProperty<T2>();

    public FX2arg(T1 arg1, T2 arg2)
    {
        setName(arg1);
        setValue(arg2);
    }

    public void setName (T1 arg) {name.set(arg);}
    public void setValue (T2 arg) {value.set(arg);}
    public T1 getName() {return name.get();}
    public T2 getValue() {return value.get();}
}
