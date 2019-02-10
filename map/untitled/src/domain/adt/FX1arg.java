package domain.adt;

import javafx.beans.property.SimpleObjectProperty;

public class FX1arg<T1> {
    private final SimpleObjectProperty<T1> name=new SimpleObjectProperty<T1>();

    public FX1arg(T1 arg1)
    {
        setName(arg1);
    }

    public void setName (T1 arg) {name.set(arg);}
    public T1 getName() {return name.get();}
}
