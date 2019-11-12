package yan.sqliteDemo.JMX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;
import yan.sqliteDemo.sqlite.Datebase;

@ManagedResource(objectName = "bean:name=sqlbean", description = "test sqlite")
@Component
public class SQLJmxBean {
    @Autowired
    Datebase datebase = null;
    private String description = null;

    @ManagedAttribute
    public void setDescription(String des) {
        description = des;
    }

    @ManagedAttribute
    public String getDescription() {
        return description;
    }

    @ManagedOperation
    String query() {
        return "dummy";
    }
}
