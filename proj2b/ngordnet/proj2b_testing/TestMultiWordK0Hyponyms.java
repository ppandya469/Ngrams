package ngordnet.proj2b_testing;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/** Tests the case where the list of words is length greater than 1, but k is still zero. */
public class TestMultiWordK0Hyponyms {
    public static final String WORDS_FILE = "data/ngrams/very_short.csv";
    public static final String LARGE_WORDS_FILE = "data/ngrams/top_14377_words.csv";
    public static final String TOTAL_COUNTS_FILE = "data/ngrams/total_counts.csv";
    public static final String SMALL_SYNSET_FILE = "data/wordnet/synsets16.txt";
    public static final String SMALL_HYPONYM_FILE = "data/wordnet/hyponyms16.txt";
    public static final String LARGE_SYNSET_FILE = "data/wordnet/synsets.txt";
    public static final String LARGE_HYPONYM_FILE = "data/wordnet/hyponyms.txt";

    /** This is an example from the spec.*/
    @Test
    public void testOccurrenceAndChangeK0() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = List.of("occurrence", "change");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[alteration, change, increase, jump, leap, modification, saltation, transition]";
        assertThat(actual).isEqualTo(expected);
        NgordnetQueryHandler studentHandler2 = AutograderBuddy.getHyponymHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words2 = List.of("occurrence", "change");

        NgordnetQuery nq2 = new NgordnetQuery(words2, 0, 0, 0);
        String actual2 = studentHandler.handle(nq2);
        String expected2 = "[alteration, change, increase, jump, leap, modification, saltation, transition]";
        assertThat(actual2).isEqualTo(expected2);
    }

    @Test
    public void testMultipleLargeFileK0() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                LARGE_WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("pad", "movement", "set", "press", "lead", "effect", "shape", "center", "right");

        NgordnetQuery nq = new NgordnetQuery(words, 1900, 2020, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test31Attempt3() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                LARGE_WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("matter", "bearskin");

        NgordnetQuery nq = new NgordnetQuery(words, 1470, 2019, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[bearskin]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test32Attempt3() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                LARGE_WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("order", "suborder_Anisoptera");

        NgordnetQuery nq = new NgordnetQuery(words, 1470, 2019, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[Anisoptera, suborder_Anisoptera]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test31Attempt7() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                LARGE_WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("agent", "impresario");

        NgordnetQuery nq = new NgordnetQuery(words, 1470, 2019, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[Barnum, Buffalo_Bill, Buffalo_Bill_Cody, Charles_Ringling, Cody, D'Oyly_Carte, Diaghilev, Hurok, P._T._Barnum, Phineas_Taylor_Barnum, Richard_D'Oyly_Carte, Ringling, Sergei_Diaghilev, Sergei_Pavlovich_Diaghilev, Sol_Hurok, Solomon_Hurok, William_F._Cody, William_Frederick_Cody, exhibitioner, exhibitor, impresario, organ-grinder, porn_merchant, pornographer, promoter, shower, showman]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test32Attempt7() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                LARGE_WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("being", "Black_Hawk");

        NgordnetQuery nq = new NgordnetQuery(words, 1470, 2019, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[Black_Hawk, Makataimeshekiakiak]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test32Attempt9() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                LARGE_WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("communication", "Assamese");

        NgordnetQuery nq = new NgordnetQuery(words, 1470, 2019, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[Asamiya, Assamese]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test31Attempt11() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                LARGE_WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("content", "golden_handshake");

        NgordnetQuery nq = new NgordnetQuery(words, 1470, 2019, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[golden_handshake]";
        assertThat(actual).isEqualTo(expected);
    }
}
