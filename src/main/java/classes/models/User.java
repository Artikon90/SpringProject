package classes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Comparable<User> {
    private int id;

    @NotEmpty(message = "Name should be not empty")
    @Size(min = 2, max = 40, message = "Name length should be at least 5 and no longer than 40 character")
    private String name;

    @NotEmpty(message = "Email should be not empty!")
    @Email(message = "Check correct input email!")
    private String email;

    @Min(value = 0, message = "Age should be positive")
    private int age;

    @Override
    public int compareTo(User o) {
        return this.getId() - o.getId();
    }
}
