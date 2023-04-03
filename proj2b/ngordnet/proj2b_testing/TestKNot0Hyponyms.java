package ngordnet.proj2b_testing;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/** Tests the most basic case for Hyponyms where the list of words is one word long, and k = 0.*/
public class TestKNot0Hyponyms {
    public static final String LARGE_WORDS_FILE = "data/ngrams/top_14377_words.csv";
    public static final String LARGE_TOTAL_COUNTS_FILE = "data/ngrams/total_counts.csv";
    public static final String LARGE_SYNSET_FILE = "data/wordnet/synsets.txt";
    public static final String LARGE_HYPONYM_FILE = "data/wordnet/hyponyms.txt";

    //single words
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
    public void testEntityLargeFileK4() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                LARGE_WORDS_FILE, LARGE_TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("entity");

        NgordnetQuery nq = new NgordnetQuery(words, 1470, 2019, 4);
        String actual = studentHandler.handle(nq);
        String expected = "[are, at, have, in]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGroupingLargeFileK6() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                LARGE_WORDS_FILE, LARGE_TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("grouping");

        NgordnetQuery nq = new NgordnetQuery(words, 1470, 2019, 6);
        String actual = studentHandler.handle(nq);
        String expected = "[case, man, number, people, system, year]";
        assertThat(actual).isEqualTo(expected);
    }

    //multiple words
    @Test
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
    public void testMultipleLargeFileK9() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                LARGE_WORDS_FILE, LARGE_TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("line", "transporter");

        NgordnetQuery nq = new NgordnetQuery(words, 1920, 1980, 9);
        String actual = studentHandler.handle(nq);
        String expected = "[conveyor, transporter]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testMultipleLargeFileK4() {// 2:25pm auto-grader test
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                LARGE_WORDS_FILE, LARGE_TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("message");

        NgordnetQuery nq = new NgordnetQuery(words, 1470, 2019, 4);
        String actual = studentHandler.handle(nq);
        String expected = "[case, information, order, point]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testMultipleLargeFileK6() {// 2:25pm auto-grader test
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                LARGE_WORDS_FILE, LARGE_TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("substance");

        NgordnetQuery nq = new NgordnetQuery(words, 1470, 2019, 6);
        String actual = studentHandler.handle(nq);
        String expected = "[case, must, order, three, two, water]";
        assertThat(actual).isEqualTo(expected);
    }
}
