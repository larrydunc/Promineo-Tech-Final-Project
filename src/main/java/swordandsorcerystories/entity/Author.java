package swordandsorcerystories.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long authorId;
	
	private String authorFirstName;
	private String authorLastName;
	private String dateOfBirth;
	private String dateOfDeath;
	private String biography;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Story> stories = new HashSet<>();
	

}
