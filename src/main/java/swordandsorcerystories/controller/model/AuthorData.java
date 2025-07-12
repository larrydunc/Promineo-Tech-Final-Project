package swordandsorcerystories.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import swordandsorcerystories.entity.Author;
import swordandsorcerystories.entity.Character;
import swordandsorcerystories.entity.Story;

@Data
@NoArgsConstructor
public class AuthorData {
	private Long authorId;
	private String authorFirstName;
	private String authorLastName;
	private String dateOfBirth;
	private String dateOfDeath;
	private String biography;
	private Set<StoryData> stories = new HashSet<>();
	
	public AuthorData(Author author) {
		this.authorId = author.getAuthorId();
		this.authorFirstName = author.getAuthorFirstName();
		this.authorLastName = author.getAuthorLastName();
		this.dateOfBirth = author.getDateOfBirth();
		this.dateOfDeath = author.getDateOfDeath();
		this.biography = author.getBiography();
		
		for(Story story : author.getStories()) {
			this.stories.add(new StoryData(story));
		}
	}
	
	public AuthorData(Long authorId, String authorFirstName, String authorLastName,
			String dateOfBirth, String dateOfDeath, String biography) {
		this.authorId = authorId;
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.dateOfBirth = dateOfBirth;
		this.dateOfDeath = dateOfDeath;
		this.biography = biography;
	}
	
	public Author toAuthor() {
		Author author = new Author();
		
		author.setAuthorId(authorId);
		author.setAuthorFirstName(authorFirstName);
		author.setAuthorLastName(authorLastName);
		author.setDateOfBirth(dateOfBirth);
		author.setDateOfDeath(dateOfDeath);
		author.setBiography(biography);
		
	for(StoryData storyResponse : stories) {
		author.getStories().add(storyResponse.toStory());
		}
		
		return author;
	}
	
	@Data
	@NoArgsConstructor
	public static class StoryData{
		private Long storyId;
		private String storyTitle;
		private String datePublished;
		private String publication;
		private String summary;
		private Set<CharacterData> characters = new HashSet<>();
		
		public StoryData(Story story) {
			this.storyId = story.getStoryId();
			this.storyTitle = story.getStoryTitle();
			this.datePublished = story.getDatePublished();
			this.publication = story.getPublication();
			this.summary = story.getSummary();
			
			
			for(Character character : story.getCharacters()) {
				this.characters.add(new CharacterData(character));
			}
		}
		
		public Story toStory() {
			Story story = new Story();
			
			story.setStoryId(storyId);
			story.setStoryTitle(storyTitle);
			story.setDatePublished(datePublished);
			story.setPublication(publication);
			story.setSummary(summary);
			
			for(CharacterData characterData : characters) {
				story.getCharacters().add(characterData.toCharacter());
			}
			
			return story;
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class CharacterData {
		private Long characterId;
		private String characterName;
		private String characterType;
		private String characterDescription;
		
		public CharacterData(Character character) {
			this.characterId = character.getCharacterId();
			this.characterName = character.getCharacterName();
			this.characterType = character.getCharacterType();
			this.characterDescription = character.getCharacterDescription();
		}
		
		public Character toCharacter() {
			Character character = new Character();
			
			character.setCharacterId(characterId);
			character.setCharacterName(characterName);
			character.setCharacterType(characterType);
			character.setCharacterDescription(characterDescription);
			
			return character;
		}
	}

}
