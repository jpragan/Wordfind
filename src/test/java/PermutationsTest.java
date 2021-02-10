import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PermutationsTest {

    @Test
    void permutationOfOne() {
        Permutations.hash_Set.clear();
        Permutations.Permutation("a", "");
        assertEquals(1, Permutations.hash_Set.size());

    }

    @Test
    void permutationOfTwo() {
        Permutations.hash_Set.clear();
        Permutations.Permutation("ab", "");
        assertEquals(4, Permutations.hash_Set.size());

    }

    @Test
    void permutationOfThree() {
        Permutations.hash_Set.clear();
        Permutations.Permutation("abc", "");
        assertEquals(15, Permutations.hash_Set.size());

    }

    @Test
    void permutationOfThreeWithDuplicates() {
        Permutations.hash_Set.clear();
        Permutations.Permutation("abb", "");
        assertEquals(8, Permutations.hash_Set.size());

    }

    @Test
    void permutationOfFour() {
        Permutations.hash_Set.clear();
        Permutations.Permutation("abcd", "");
        assertEquals(64, Permutations.hash_Set.size());

    }

    @Test
    void permutationOfFourWithDuplicates() {
        Permutations.hash_Set.clear();
        Permutations.Permutation("abca", "");
        assertEquals(34, Permutations.hash_Set.size());

    }

    @Test
    void permutationOfFive() {
        Permutations.hash_Set.clear();
        Permutations.Permutation("abcde", "");
        assertEquals(325, Permutations.hash_Set.size());
    }

    @Test
    void permutationOfFiveWithDuplicates() {
        Permutations.hash_Set.clear();
        Permutations.Permutation("abcab", "");
        assertEquals(89, Permutations.hash_Set.size());
    }

    @Test
    void permutationOfSix() {
        Permutations.hash_Set.clear();
        Permutations.Permutation("abcdef", "");
        assertEquals(1956, Permutations.hash_Set.size());
    }

    @Test
    void permutationOfSixWithDuplicates() {
        Permutations.hash_Set.clear();
        Permutations.Permutation("abcdec", "");
        assertEquals(1010, Permutations.hash_Set.size());
    }

    @Test
    void permutationOfSeven() {
        Permutations.hash_Set.clear();
        Permutations.Permutation("abcdefg", "");
        assertEquals(13699, Permutations.hash_Set.size());
    }
}