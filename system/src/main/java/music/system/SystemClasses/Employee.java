package music.system.SystemClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee {

    private int employeeID;
    private String name;
    private String position;

    // Constructor
    public Employee(int employeeID, String name, String position) {

        this.employeeID = employeeID;
        this.name = name;
        this.position = position;
    }

    // Getters and setters
    public int getID() {
        return employeeID;
    }

    public void setID(int id) {
        this.employeeID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String pos) {
        this.position = pos;
    }

}