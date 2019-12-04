package yan.sqliteDemo.JMX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;
import yan.sqliteDemo.sqlite.Datebase;

@Component
@ManagedResource(objectName = "yan.sqliteDemo.JMX:name=sqlbean", description = "test sqlite")
public class SQLJmxBean {
    @Autowired
    Datebase datebase;
    public String description = null;

    @ManagedAttribute
    public void setDescription(String des) {
        description = des;
    }

    @ManagedAttribute
    public String getDescription() {
        return description;
    }

    @ManagedOperation
    public String query() {
        return "dummy";
    }
}
