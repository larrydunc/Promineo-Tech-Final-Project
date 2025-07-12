package swordandsorcerystories.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import swordandsorcerystories.controller.model.AuthorData;
import swordandsorcerystories.controller.model.AuthorData.StoryData;
import swordandsorcerystories.dao.AuthorDao;
import swordandsorcerystories.dao.CharacterDao;
import swordandsorcerystories.dao.StoryDao;
import swordandsorcerystories.entity.Author;
import swordandsorcerystories.entity.Character;
import swordandsorcerystories.entity.Story;


@Service
public class SwordAndSorceryStoriesService {

	@Autowired
	private AuthorDao authorDao;
	@Autowired
	private StoryDao storyDao;
	@Autowired
	private CharacterDao characterDao;
	
	
	
	@Transactional(readOnly = false)
	public AuthorData saveAuthor(AuthorData authorData) {
		Author author = authorData.toAuthor();
		Author dbAuthor = authorDao.save(author);
		
		return new AuthorData(dbAuthor);
	}
	
	@Transactional(readOnly = true)
	public AuthorData retrieveAuthorById(Long authorId) {
		Author author = findAuthorById(authorId);
		return new AuthorData(author);
	}

	private Author findAuthorById(Long authorId) {
		return authorDao.findById(authorId).orElseThrow(() -> new NoSuchElementException("Author with ID=" + authorId + " was not found."));
	}

	@Transactional(readOnly = true)
	public List<AuthorData> retrieveAllAuthors() {
		// @formatter:off
		return authorDao.findAll()
				.stream()
				.sorted((aut1, aut2) -> aut1.getAuthorLastName().compareTo(aut2.getAuthorLastName()))
				.map(AuthorData::new)
				.toList();
		// @formatter:on
	}

	@Transactional(readOnly = false)
	public void deleteAuthor(Long authorId) {
		Author author = findAuthorById(authorId);
		authorDao.delete(author);
	}
	
	@Transactional(readOnly = false)
	public void joinCharacterStory(Long characterId, Long storyId) {
		Story story = findStoryById(storyId);
		Character character = findCharacterById(characterId);
		story.getCharacters().add(character);
		character.getStories().add(story);
		
	}

	private Character findCharacterById(Long characterId) {
		return characterDao.findById(characterId).orElseThrow(() -> new NoSuchElementException("Character with ID=" + characterId + " was not found."));
	}

	private Story findStoryById(Long storyId) {
		return storyDao.findById(storyId).orElseThrow(() -> new NoSuchElementException("Story with ID=" + storyId + " was not found."));

	}
	
	@Transactional(readOnly = true)
	public StoryData retrieveStoryById(Long storyId) {
		Story story = findStoryById(storyId);
		return new StoryData(story);
		
	}
	

}
