package swordandsorcerystories.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import swordandsorcerystories.SwordAndSorceryStoriesApplication;
import swordandsorcerystories.controller.model.AuthorData;

@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = SwordAndSorceryStoriesApplication.class)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"})
@SqlConfig(encoding = "utf-8")
class SwordAndSorceryStoriesControllerTest extends SwordAndSorceryStoriesServiceTestSupport {

	@Test
	void testInsertAuthor() {
		//Given: An Author request
		AuthorData request = buildInsertAuthor(1);
		AuthorData expected = buildInsertAuthor(1);
		
		//When: The Author is added to the Author table
		AuthorData actual = insertAuthor(request);
		
		//Then: The Author returned is what is expected
		assertThat(actual).isEqualTo(expected);
		
		//And: There is one row in the Author table.
		assertThat(rowsInAuthorTable()).isOne();
	}
	
	@Test
	void testRetrieveAuthor() {
		// Given an Author
		AuthorData author = insertAuthor(buildInsertAuthor(1));
		AuthorData expected = buildInsertAuthor(1);
		
		//When: An Author is retrieved by author ID
		AuthorData actual = retrieveAuthor(author.getAuthorId());
		
		//Then: The actual author is equal to the expected author.
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void testRetrieveAllAuthors() {
		//Given: two Authors
		List<AuthorData> expected = insertTwoAuthors();
		
		//When: All Authors are retrieved
		List<AuthorData> actual = retrieveAllAuthors();
		
		//Then: The Retrieved Authors are the same as expected
		assertThat (sorted(actual)).isEqualTo(sorted(expected));
		
	}
	
	@Test
	void testUpdateAuthor() {
		//Given: An Author and an update request
		insertAuthor(buildInsertAuthor(1));
		AuthorData expected = buildUpdateAuthor();
		
		//When: the Author is updated.
		AuthorData actual = updateAuthor(expected);
		
		//Then: The Author is returned as expected.
		assertThat(actual).isEqualTo(expected);
		
		//And: There is one row in the author table.
		assertThat(rowsInAuthorTable()).isOne();
	}
	
	@Test
	void testDeleteAuthorWithStories() {
		//Given: An Author and two Stories with Characters
		AuthorData author = insertAuthor(buildInsertAuthor(1));
		Long authorId = author.getAuthorId();
		
		insertStory(1);
		insertStory(2);
		
		assertThat(rowsInAuthorTable()).isOne();
		assertThat(rowsInStoryTable()).isEqualTo(2);
		assertThat(rowsInStoryCharacterTable()).isEqualTo(4);
		int characterRows = rowsInCharacterTable();
		
		//When: the Author is Deleted
		deleteAuthor(authorId);
		
		//Then : There are no Author, Story, or Story_Character rows
		assertThat(rowsInAuthorTable()).isZero();
		assertThat(rowsInStoryTable()).isZero();
		assertThat(rowsInStoryCharacterTable()).isZero();
		
		//And: The number of Character rows has not changed.
		assertThat(rowsInCharacterTable()).isEqualTo(characterRows);
		
	}

	

	
	
	
	
}
