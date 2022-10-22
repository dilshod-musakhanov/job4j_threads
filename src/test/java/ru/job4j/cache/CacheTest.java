package ru.job4j.cache;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CacheTest {

    @Test
    public void whenAddAndAddTheSameAgain() {
        Base model = new Base(1, 0);
        Cache cache = new Cache();
        assertThat(cache.add(model)).isEqualTo(true);
        assertThat(cache.add(model)).isEqualTo(false);
    }

    @Test
    public void whenUpdate() {
        Base model = new Base(1, 0);
        model.setName("Petr");
        Base newModel = new Base(1, 0);
        newModel.setName("Stas");
        Cache cache = new Cache();
        cache.add(model);
        assertThat(cache.update(newModel)).isEqualTo(true);
        assertThat(cache.getBase(1).getName()).isEqualTo("Stas");
        assertThat(cache.getBase(1).getVersion()).isEqualTo(1);
    }

    @Test
    public void whenDelete() {
        Base model = new Base(1, 0);
        Cache cache = new Cache();
        cache.delete(model);
        assertThat(cache.getBase(1)).isEqualTo(null);
    }

}