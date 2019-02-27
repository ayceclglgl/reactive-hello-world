package ayc.reactive.hello;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCommand {
	
	private String firstName;
    private String lastName;
    
    public StudentCommand(Student student) {
    	this.firstName = student.getFirstName();
    	this.lastName = student.getLastName();
    }
    
    public String name() {
    	return firstName + " " + lastName;
    }
}
