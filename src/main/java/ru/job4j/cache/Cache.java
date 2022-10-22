package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        BiFunction<Integer, Base, Base> func = (integer, storedModel) -> {
            if (storedModel.getVersion() != model.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            Base temp = new Base(integer, storedModel.getVersion() + 1);
            temp.setName(model.getName());
            return temp;
        };
        return memory.computeIfPresent(model.getId(), func) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }

    public Base getBase(Integer id) {
        return memory.get(id);
    }

}
