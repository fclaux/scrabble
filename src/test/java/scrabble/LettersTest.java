package scrabble;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import scrabble.model.*;

public class LettersTest {
	
	@Test
	void get_value_A() {
		Tile tile_A = new Tile(Letters.A);
		Integer letters_value;
		
		letters_value = tile_A.getLetter().getValue();
		assertThat(letters_value).isEqualTo(1);
	}
	
	@Test
	void get_value_B() {
	    Tile tile_B = new Tile(Letters.B);
	    Integer letters_value;

	    letters_value = tile_B.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(3);
	}

	@Test
	void get_value_C() {
	    Tile tile_C = new Tile(Letters.C);
	    Integer letters_value;

	    letters_value = tile_C.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(3);
	}
	
	@Test
	void get_value_D() {
	    Tile tile_D = new Tile(Letters.D);
	    Integer letters_value;

	    letters_value = tile_D.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(2);
	}

	@Test
	void get_value_E() {
	    Tile tile_E = new Tile(Letters.E);
	    Integer letters_value;

	    letters_value = tile_E.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(1);
	}
	
	@Test
	void get_value_F() {
	    Tile tile_F = new Tile(Letters.F);
	    Integer letters_value;

	    letters_value = tile_F.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(4);
	}

	@Test
	void get_value_G() {
	    Tile tile_G = new Tile(Letters.G);
	    Integer letters_value;

	    letters_value = tile_G.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(2);
	}

	@Test
	void get_value_H() {
	    Tile tile_H = new Tile(Letters.H);
	    Integer letters_value;

	    letters_value = tile_H.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(4);
	}

	@Test
	void get_value_I() {
	    Tile tile_I = new Tile(Letters.I);
	    Integer letters_value;

	    letters_value = tile_I.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(1);
	}

	@Test
	void get_value_J() {
	    Tile tile_J = new Tile(Letters.J);
	    Integer letters_value;

	    letters_value = tile_J.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(8);
	}
	
	@Test
	void get_value_K() {
	    Tile tile_K = new Tile(Letters.K);
	    Integer letters_value;

	    letters_value = tile_K.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(10);
	}

	@Test
	void get_value_L() {
	    Tile tile_L = new Tile(Letters.L);
	    Integer letters_value;

	    letters_value = tile_L.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(1);
	}

	@Test
	void get_value_M() {
	    Tile tile_M = new Tile(Letters.M);
	    Integer letters_value;

	    letters_value = tile_M.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(2);
	}

	@Test
	void get_value_N() {
	    Tile tile_N = new Tile(Letters.N);
	    Integer letters_value;

	    letters_value = tile_N.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(1);
	}

	@Test
	void get_value_O() {
	    Tile tile_O = new Tile(Letters.O);
	    Integer letters_value;

	    letters_value = tile_O.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(1);
	}
	
	@Test
	void get_value_P() {
	    Tile tile_P = new Tile(Letters.P);
	    Integer letters_value;

	    letters_value = tile_P.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(3);
	}

	@Test
	void get_value_Q() {
	    Tile tile_Q = new Tile(Letters.Q);
	    Integer letters_value;

	    letters_value = tile_Q.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(8);
	}

	@Test
	void get_value_R() {
	    Tile tile_R = new Tile(Letters.R);
	    Integer letters_value;

	    letters_value = tile_R.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(1);
	}

	@Test
	void get_value_S() {
	    Tile tile_S = new Tile(Letters.S);
	    Integer letters_value;

	    letters_value = tile_S.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(1);
	}

	@Test
	void get_value_T() {
	    Tile tile_T = new Tile(Letters.T);
	    Integer letters_value;

	    letters_value = tile_T.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(1);
	}
	
	@Test
	void get_value_U() {
	    Tile tile_U = new Tile(Letters.U);
	    Integer letters_value;

	    letters_value = tile_U.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(1);
	}

	@Test
	void get_value_V() {
	    Tile tile_V = new Tile(Letters.V);
	    Integer letters_value;

	    letters_value = tile_V.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(4);
	}

	@Test
	void get_value_W() {
	    Tile tile_W = new Tile(Letters.W);
	    Integer letters_value;

	    letters_value = tile_W.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(10);
	}

	@Test
	void get_value_X() {
	    Tile tile_X = new Tile(Letters.X);
	    Integer letters_value;

	    letters_value = tile_X.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(10);
	}

	@Test
	void get_value_Y() {
	    Tile tile_Y = new Tile(Letters.Y);
	    Integer letters_value;

	    letters_value = tile_Y.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(10);
	}

	@Test
	void get_value_Z() {
	    Tile tile_Z = new Tile(Letters.Z);
	    Integer letters_value;

	    letters_value = tile_Z.getLetter().getValue();
	    assertThat(letters_value).isEqualTo(10);
	}
	
	@Test
	void get_value_JOKER() {
		Tile tile_JOKER = new Tile(Letters.JOKER);
		Integer letters_value;
		
		letters_value = tile_JOKER.getLetter().getValue();
		assertThat(letters_value).isEqualTo(0);
	}
}
