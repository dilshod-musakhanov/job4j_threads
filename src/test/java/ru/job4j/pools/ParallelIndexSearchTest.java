package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParallelIndexSearchTest {

    @Test
    void whenIndexFoundInTenElementArrayInteger() {
        Integer[] array = new Integer[] {
                10, 9, 8, 7, 6, 5, 4, 3, 2, 1
        };
        int res = 1;
        assertThat(ParallelIndexSearch.find(array, 9)).isEqualTo(res);
    }

    @Test
    void whenIndexFoundInEighteenElementArrayInteger() {
        Integer[] array = new Integer[] {
                10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 19, 18, 17, 16, 15, 14, 13, 12, 11
        };
        int res = 16;
        assertThat(ParallelIndexSearch.find(array, 13)).isEqualTo(res);
    }

    @Test
    void whenIndexFoundForLastElementInTenElementArrayInteger() {
        Integer[] array = new Integer[] {
                10, 9, 8, 7, 6, 5, 4, 3, 2, 1
        };
        int res = 9;
        assertThat(ParallelIndexSearch.find(array, 1)).isEqualTo(res);
    }

    @Test
    void whenIndexFoundForLastElementInEighteenElementArrayInteger() {
        Integer[] array = new Integer[] {
                10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 19, 18, 17, 16, 15, 14, 13, 12, 11
        };
        int res = 18;
        assertThat(ParallelIndexSearch.find(array, 11)).isEqualTo(res);
    }

    @Test
    void whenIndexNotFoundInTenElementArrayString() {
        String[] array = new String[] {
                "10", "9", "8", "7", "6", "5", "4", "3", "2", "1"
        };
        int res = -1;
        assertThat(ParallelIndexSearch.find(array, "15")).isEqualTo(res);
    }

    @Test
    void whenIndexNotFoundInEighteenElementArrayString() {
        String[] array = new String[] {
                "10", "9", "8", "7", "6", "5", "4", "3", "2", "1", "11", "12", "13", "14", "15", "16", "17", "18"
        };
        int res = -1;
        assertThat(ParallelIndexSearch.find(array, "20")).isEqualTo(res);
    }

}