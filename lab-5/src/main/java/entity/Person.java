package entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private int idPerson;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
}
