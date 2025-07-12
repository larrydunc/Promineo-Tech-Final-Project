package swordandsorcerystories.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Table(name = "character_main")
public class Character {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long characterId;
	private String characterName;
	private String characterType;
	private String characterDescription;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "characters", cascade= CascadeType.PERSIST)
	private Set<Story> stories = new HashSet<>();

	
	
	

}
