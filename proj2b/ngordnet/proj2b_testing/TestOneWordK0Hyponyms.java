package ngordnet.proj2b_testing;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.hyponyms.WordNet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/** Tests the most basic case for Hyponyms where the list of words is one word long, and k = 0.*/
public class TestOneWordK0Hyponyms {
    // this case doesn't use the NGrams dataset at all, so the choice of files is irrelevant
    public static final String WORDS_FILE = "data/ngrams/very_short.csv";
    public static final String LARGE_WORDS_FILE = "data/ngrams/top_14377_words.csv";
    public static final String TOTAL_COUNTS_FILE = "data/ngrams/total_counts.csv";
    public static final String LARGE_TOTAL_COUNTS_FILE = "data/ngrams/total_counts.csv";
    public static final String SMALL_SYNSET_FILE = "data/wordnet/synsets16.txt";
    public static final String SMALL_HYPONYM_FILE = "data/wordnet/hyponyms16.txt";
    public static final String LARGE_SYNSET_FILE = "data/wordnet/synsets.txt";
    public static final String LARGE_HYPONYM_FILE = "data/wordnet/hyponyms.txt";

    @Test
    public void testActK0() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = List.of("act");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[act, action, change, demotion, human_action, human_activity, variation]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void wordNetTestBasic() {
        WordNet wN = new WordNet("data/wordnet/synsets16.txt", "data/wordnet/hyponyms16.txt");
        assertThat(wN).isNotNull();
    }

    @Test
    public void testActLargeFileK3() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                LARGE_WORDS_FILE, LARGE_TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("concept");

        NgordnetQuery nq = new NgordnetQuery(words, 1470, 2019, 3);
        String actual = studentHandler.handle(nq);
        String expected = "[like, one, over]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testMultipleLargeFileK0() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                LARGE_WORDS_FILE, LARGE_TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("pad", "movement", "set", "press", "lead", "effect", "shape", "center", "right");

        NgordnetQuery nq = new NgordnetQuery(words, 1900, 2020, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testCommasLargeFileK8() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                LARGE_WORDS_FILE, LARGE_TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("knowledge", "muscle_spasm");

        NgordnetQuery nq = new NgordnetQuery(words, 1470, 2019, 8);
        String actual = studentHandler.handle(nq);
        String expected = "[fibrillation]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testK8Granular() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                LARGE_WORDS_FILE, LARGE_TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("knowledge");
        NgordnetQuery nq = new NgordnetQuery(words, 1470, 2019, 8);
        String actual = studentHandler.handle(nq);
        String expected = "[like, one, over, part, time, use, will, work]";
        assertThat(actual).isEqualTo(expected);
    }

    public void testCommasLargeFileK9() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                LARGE_WORDS_FILE, LARGE_TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("entity", "matzah_ball");

        NgordnetQuery nq = new NgordnetQuery(words, 1470, 2019, 9);
        String actual = studentHandler.handle(nq);
        String expected = "[]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testCompoundLargeFileK2() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                LARGE_WORDS_FILE, LARGE_TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("compound");

        NgordnetQuery nq = new NgordnetQuery(words, 1470, 2019, 2);
        String actual = studentHandler.handle(nq);
        String expected = "[oil, water]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testPartLargeFileK8() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                LARGE_WORDS_FILE, LARGE_TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("part");

        NgordnetQuery nq = new NgordnetQuery(words, 1470, 2019, 8);
        String actual = studentHandler.handle(nq);
        String expected = "[can, do, must, over, part, so, two, way]";
        assertThat(actual).isEqualTo(expected);
    }
}
