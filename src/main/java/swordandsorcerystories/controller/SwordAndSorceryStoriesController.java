package swordandsorcerystories.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import swordandsorcerystories.controller.model.AuthorData;
import swordandsorcerystories.controller.model.AuthorData.StoryData;
import swordandsorcerystories.service.SwordAndSorceryStoriesService;

@RestController
@RequestMapping("/sword_and_sorcery_stories")
@Slf4j
public class SwordAndSorceryStoriesController {
	@Autowired
	private SwordAndSorceryStoriesService swordAndSorceryStoriesService;
	
	@PostMapping("/author")
	@ResponseStatus(code = HttpStatus.CREATED)
	public AuthorData createAuthor(@RequestBody AuthorData authorData) {
		log.info("Creating author profile {}", authorData);
		return swordAndSorceryStoriesService.saveAuthor(authorData);
	}
	
	@PutMapping("/author/{authorId}")
	public AuthorData updateAuthor(@PathVariable Long authorId, @RequestBody AuthorData authorData) {
		authorData.setAuthorId(authorId);
		log.info("Updating author {}", authorData);
		return swordAndSorceryStoriesService.saveAuthor(authorData);
		
	}
	
	@GetMapping("/author/{authorId}")
	public AuthorData retrieveAuthor(@PathVariable Long authorId) {
		log.info("Retrieving author with ID={}", authorId);
		return swordAndSorceryStoriesService.retrieveAuthorById(authorId);
	}
	
	@GetMapping("/author")
	public List<AuthorData> retrieveAllAuthors() {
		log.info("Retrieving all authors");
		return swordAndSorceryStoriesService.retrieveAllAuthors();
	}
	
	@DeleteMapping("/author/{authorId}")
	public Map<String, String> deleteAuthor(@PathVariable Long authorId) {
		log.info("Deleting author with ID=" + authorId + ".");
		swordAndSorceryStoriesService.deleteAuthor(authorId);
		
		return Map.of("message", "Author with ID=" + authorId + " was deleted successfully.");
	}
	@PostMapping("/{characterId}/{storyId}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Map<String, String> joinCharacterAndStory(@PathVariable Long characterId, @PathVariable Long storyId) {
		log.info("Adding character with ID={} to story with ID={}.", characterId, storyId);
		swordAndSorceryStoriesService.joinCharacterStory(characterId, storyId);
		
		return Map.of("message", "Character with ID=" + characterId + " has been added to story with the ID=" + storyId + ".");
	}
	
	@GetMapping("/story/{storyId}")
	public StoryData retrieveStory(@PathVariable Long storyId) {
		log.info("Retrieving story with ID={}", storyId);
		return swordAndSorceryStoriesService.retrieveStoryById(storyId);
	}
	
}
