package entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogueSection {
    private int idSection;
    private String name;
    private Integer parentId;
}
