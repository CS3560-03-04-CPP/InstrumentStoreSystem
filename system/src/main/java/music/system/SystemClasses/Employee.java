package music.system.SystemClasses;

/**
 * System Class, Employee: This class handles the sign in process for premade accounts in the database.
 * 
 * Database table employee, links to users table with a users_employeeID attribute:
 * 
 *           users: username
 *                  password
 * 
 * The System supports a two layer access, Employee and manager.
 * 
 *           Employees: can access everything except analytics
 *           Managers: can access everything.
 */
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
    public int getID() {return employeeID;}

    public void setID(int id) {this.employeeID = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getPosition() {return position;}

    public void setPosition(String pos) {this.position = pos;}

}