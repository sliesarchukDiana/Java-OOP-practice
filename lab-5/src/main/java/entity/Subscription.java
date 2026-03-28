package entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    private int idSubscription;
    private int formatType;
    private int idClient;
    private int idSection;
    private int idKeyword;
}
