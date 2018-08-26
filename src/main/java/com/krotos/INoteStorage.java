package com.krotos;

import java.util.List;

public interface INoteStorage {
    // tylko operacje na bazie danych
    void add(Note note);
    List<Note> getAllNotesOf(String name);
    void clear();
}
