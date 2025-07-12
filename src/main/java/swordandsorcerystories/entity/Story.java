package swordandsorcerystories.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Story {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long storyId;
	
	@EqualsAndHashCode.Exclude
	private String storyTitle;
	
	@EqualsAndHashCode.Exclude
	private String datePublished;
	
	@EqualsAndHashCode.Exclude
	private String publication;
	
	@EqualsAndHashCode.Exclude
	private String summary;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private Author author;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "story_character", 
			joinColumns = @JoinColumn(name = "story_id"),
			inverseJoinColumns = @JoinColumn(name = "character_id")
			)
	private Set<Character> characters = new HashSet<>();
	

}
