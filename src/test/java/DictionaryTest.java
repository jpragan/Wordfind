import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {

    @Test
    public void isWORKINGEnglishWord() throws Exception {

        assertTrue(Dictionary.isEnglishWord("WORKING"));

    }

    @Test
    public void isAnHDGGGedEnglishWord() throws Exception {

        assertFalse(Dictionary.isEnglishWord("AnHDGGGed"));

    }

    @Test
    public void iswwkjuyed8EnglishWord() throws Exception {

        assertFalse(Dictionary.isEnglishWord("wwkjuyed8"));

    }
};