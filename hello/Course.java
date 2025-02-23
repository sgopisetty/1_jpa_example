package hello; 

import javax.persistence.Entity; 
import javax.persistence.Id; 

@Entity 
public class Course
{
	@Id 
	private int id; 
    private boolean active;
	private String number;
	private String description;
	

    // Constructor
    public Course() 
    {
    }

    // Parameterized Constructor
    public Course(int id, boolean active, String number, String description) {
        this.id = id;
        this.active = active;
        this.number = number;
        this.description = description;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}