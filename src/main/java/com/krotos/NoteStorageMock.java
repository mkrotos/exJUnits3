package com.krotos;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.List;

public class NoteStorageMock implements INoteStorage {
    private final Multimap<String, Note> grades=ArrayListMultimap.create();

    @Override
    public void add(Note note) {
        grades.put(note.getName(),note);
    }

    @Override
    public List<Note> getAllNotesOf(String name) {
        return (List<Note>) grades.get(name);
    }

    @Override
    public void clear() {
        grades.clear();
    }
}
