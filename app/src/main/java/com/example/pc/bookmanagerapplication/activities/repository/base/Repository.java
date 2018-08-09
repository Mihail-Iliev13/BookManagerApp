package com.example.pc.bookmanagerapplication.activities.repository.base;

import java.util.List;
import java.util.function.Consumer;

public interface Repository<T> {

    void getAll(Consumer<List<T>> action);

    void add (T book);

}
