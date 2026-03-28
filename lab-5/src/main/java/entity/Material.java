package entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Material {
    private int idMaterial;
    private String title;
    private String annotation;
    private String bodyText;
    private double cost;
    private int idSection;
}
