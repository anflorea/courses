package Interpretor;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by flo on 15/01/2017.
 */
public class TableDelegate {
    public String getKey() {
        return key.get();
    }

    public SimpleStringProperty keyProperty() {
        return key;
    }

    public void setKey(String key) {
        this.key.set(key);
    }

    public String getValue() {
        return value.get();
    }

    public SimpleStringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    public final SimpleStringProperty key;
    public final SimpleStringProperty value;

    public TableDelegate(String key, String value) {
        this.key = new SimpleStringProperty(key);
        this.value = new SimpleStringProperty(value);
    }

}
