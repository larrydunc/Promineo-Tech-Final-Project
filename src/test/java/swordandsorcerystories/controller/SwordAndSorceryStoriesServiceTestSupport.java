package swordandsorcerystories.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import swordandsorcerystories.controller.model.AuthorData;
import swordandsorcerystories.entity.Author;

public class SwordAndSorceryStoriesServiceTestSupport {

	private static final String STORY_TABLE = "story";


	private static final String STORY_CHARACTER_TABLE = "story_character";


	private static final String CHARACTER_MAIN_TABLE = "character_main";


	private static final String AUTHOR_TABLE = "author";


	private static final String INSERT_STORY_1_SQL = """
			INSERT INTO story 
			(author_id, story_title, date_published, publication) 
			VALUES (1, 'The Pheonix on the Sword', 'December 1932', 'Weird Tales');
			""";


	private static final String INSERT_STORY_2_SQL = """
			INSERT INTO story 
			(author_id, story_title, date_published, publication, summary) 
			VALUES (1, 'The Tower of the Elephant', 'March 1933', 'Weird Tales');
			""";

	private static final String INSERT_STORYCHARACTER_1_SQL = """
			INSERT INTO story_character 
			(story_id, character_id) 
			VALUES (1, 1), (1, 2);
			"""; 


	private static final String INSERT_STORYCHARACTER_2_SQL = """
			INSERT INTO story_character 
			(story_id, character_id) 
			VALUES (2, 3), (2,4);
			""";


	// Formatter:off
	private AuthorData insertProfile1 = new AuthorData(
			1L,
			"Robert E.",
			"Howard",
			"January 22, 1906",
			"June 11, 1936",
			"Robert Ervin Howard is widely reguarded as the father of Sword and Sorcery. He also created the genre's most recognizable character, Conan the Barbarian."
			);
	
	private AuthorData insertProfile2 = new AuthorData(
			2L,
			"Michael",
			"Moorcock",
			"December 18, 1939",
			"n/a",
			"Moorcock's Sword and Sorcery tales sought to deconstruct the tropes and archetypes solidfied in the genre by the pulp authors that came before him. As such, his most famous creation, Elric of Melniboné, is often seen as a reverse mirror image of Robert E. Howard's Conan."
			);
	
	private AuthorData updateProfile1 = new AuthorData(
			1L,
			"Larry",
			"Mavromatis Duncan",
			"April 19, 1976",
			"n/a",
			"Larry Mavromatis Duncan never published a single Sword and Sorcery story, but he has read an embarrassing amount of them."
			);
	// Formatter:on
	
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SwordAndSorceryStoriesController swordAndSorceryStoriesController;
	
	protected AuthorData buildInsertAuthor(int which) {
		
		return which ==1 ? insertProfile1 : insertProfile2;
	}
	
	protected int rowsInAuthorTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, AUTHOR_TABLE);
	}

	protected AuthorData insertAuthor(AuthorData authorData) {
		Author author = authorData.toAuthor();
		AuthorData clone = new AuthorData(author);
		
		clone.setAuthorId(null);
		return swordAndSorceryStoriesController.createAuthor(clone);
		
	}
	
	protected AuthorData retrieveAuthor(Long authorId) {
		return swordAndSorceryStoriesController.retrieveAuthor(authorId);
	}
	
	protected List<AuthorData> insertTwoAuthors() {
		AuthorData author1 = insertAuthor(buildInsertAuthor(1));
		AuthorData author2 = insertAuthor(buildInsertAuthor(2));
		
		return List.of(author1, author2);
		
	}
	
	protected List<AuthorData> retrieveAllAuthors() {
		return swordAndSorceryStoriesController.retrieveAllAuthors();
	}
	
	protected List<AuthorData> sorted(List<AuthorData> list) {
		List<AuthorData> data = new LinkedList<>(list);
		
		data.sort((aut1, aut2) -> (int)(aut1.getAuthorId() - aut2.getAuthorId()));
		
		return data;
	}
	
	protected AuthorData updateAuthor(AuthorData authorData) {
		return swordAndSorceryStoriesController.updateAuthor(authorData.getAuthorId(), authorData);
	}

	protected AuthorData buildUpdateAuthor() {
		return updateProfile1;
	}
	
	protected void insertStory(int which) {
		String storySql = which == 1 ? INSERT_STORY_1_SQL : INSERT_STORY_2_SQL;
		String storyCharacterSql = which == 1 ? INSERT_STORYCHARACTER_1_SQL : INSERT_STORYCHARACTER_2_SQL;
		
		jdbcTemplate.update(storySql);
		jdbcTemplate.update(storyCharacterSql);
		
	}
	
	protected int rowsInCharacterTable() {
		
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, CHARACTER_MAIN_TABLE);
	}

	protected int rowsInStoryCharacterTable() {
		
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, STORY_CHARACTER_TABLE);
	}

	protected int rowsInStoryTable() {
		
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, STORY_TABLE);
	}
	
	protected void deleteAuthor(Long authorId) {
		swordAndSorceryStoriesController.deleteAuthor(authorId);
		
	}

	

}
