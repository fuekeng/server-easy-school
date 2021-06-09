
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import com.wechange.easyschool.esmodel.entity.AbstractEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;



@Data
@Document(collection="course")
public class Course extends AbstractEntity{
	@Id
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Length(min=2, max=20,message="l'intitulé doit avoir au plus 20 caractÃ¨res")
	String intitule;
	@Min(value = 1)
	@Max(value = 7, message="le coeficient doit être inférieur à  7")
	private int coef;
	
	
	public Course() {
		super();
	}

	public Course(@Length(min = 2, max = 7) String intitule, @Min(1) @Max(7) int coef) {
		super();
		this.intitule = intitule;
		this.coef = coef;
	}

	public Course(Long id, @Length(min = 2, max = 7) String intitule, @Min(1) @Max(7) int coef) {
		super();
		this.id = id;
		this.intitule = intitule;
		this.coef = coef;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	public int getCoef() {
		return coef;
	}
	public void setCoef(int coef) {
		this.coef = coef;
	}
	
	

}
