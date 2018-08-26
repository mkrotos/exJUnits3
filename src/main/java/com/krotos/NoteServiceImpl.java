package com.krotos;

import java.util.Collection;

public class NoteServiceImpl implements INoteService {
    //nowa klasa na wzorcu fasady do testowania
    private final INoteStorage iNoteStorage;

    public static INoteService createWith(INoteStorage iNoteStorage) {
        return new NoteServiceImpl(iNoteStorage);
    }

    public NoteServiceImpl(INoteStorage iNoteStorage) {
        this.iNoteStorage = iNoteStorage;
    }

    @Override
    public void add(Note note) {
        if (note == null) {
            throw new IllegalArgumentException();
        }
        iNoteStorage.add(note);
    }

    @Override
    public double avgOf(String name) {
        double sum = 0.0;
        final Collection<Note> notes = iNoteStorage.getAllNotesOf(name);
        if (notes != null && !notes.isEmpty()) {
            for (Note note : notes) {
                sum += note.getGrade();
            }
            return sum / notes.size();
        } else {
            return 0;
        }

    }

    @Override
    public void clear() {
        iNoteStorage.clear();
    }
}
