package com.booking.DAO;

import com.booking.interfaces.Identifiable;

import java.util.Collection;

public interface DAO<S, T extends Identifiable<S>> {
    void save(T item);

    T getById(S id);

    Collection<T> getAll();

    void remove(S id);

    default void remove(T obj) {
        if (obj != null) {
            this.remove(obj.id());
        }
    }
}