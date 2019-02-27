package ayc.reactive.hello;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	
	private String firstName;
    private String lastName;
    
    public String name() {
    	return firstName + " " + lastName;
    }

}
