package scrabble;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import scrabble.model.*;

public class EffectsTest {
	
	@Test
	void get_unicode_NONE() {
		Cell cell_NONE = new Cell(Effects.NONE);
		String effect_unicode;
		
		effect_unicode = cell_NONE.getEffect().getUnicode();
		assertThat(effect_unicode).isEqualTo(" ");
	}
	
	@Test
	void get_unicode_STARS() {
		Cell cell_STARS = new Cell(Effects.STARS);
		String effect_unicode;
		
		effect_unicode = cell_STARS.getEffect().getUnicode();
		assertThat(effect_unicode).isEqualTo("\u2605");
	}
	
	@Test
	void get_unicode_DOUBLE_LETTER() {
		Cell cell_DOUBLE_LETTER = new Cell(Effects.DOUBLE_LETTER);
		String effect_unicode;
		
		effect_unicode = cell_DOUBLE_LETTER.getEffect().getUnicode();
		assertThat(effect_unicode).isEqualTo("\u25B3");
	}
	
	@Test
	void get_unicode_TRIPLE_LETTER() {
		Cell cell_TRIPLE_LETTER = new Cell(Effects.TRIPLE_LETTER);
		String effect_unicode;
		
		effect_unicode = cell_TRIPLE_LETTER.getEffect().getUnicode();
		assertThat(effect_unicode).isEqualTo("\u25B2");
	}
	
	@Test
	void get_unicode_DOUBLE_WORD() {
		Cell cell_DOUBLE_WORD = new Cell(Effects.DOUBLE_WORD);
		String effect_unicode;
		
		effect_unicode = cell_DOUBLE_WORD.getEffect().getUnicode();
		assertThat(effect_unicode).isEqualTo("\u25A1");
	}
	
	@Test
	void get_unicode_TRIPLE_WORD() {
		Cell cell_TRIPLE_WORD = new Cell(Effects.TRIPLE_WORD);
		String effect_unicode;
		
		effect_unicode = cell_TRIPLE_WORD.getEffect().getUnicode();
		assertThat(effect_unicode).isEqualTo("\u25A0");
	}
}
