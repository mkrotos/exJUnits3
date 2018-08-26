package com.krotos;

import java.util.Collection;

public class NoteServiceImpl implements INoteService {
    private final INoteStorage iNoteStorage;

    public static INoteService createWith(INoteStorage iNoteStorage) {
        return new NoteServiceImpl(iNoteStorage);
    }

    public NoteServiceImpl(INoteStorage iNoteStorage) {
        this.iNoteStorage = iNoteStorage;
    }

    @Override
    public void add(Note note) {
        iNoteStorage.add(note);
    }

    @Override
    public double avgOf(String name) {
        double sum = 0.0;
        final Collection<Note> notes = iNoteStorage.getAllNotesOf(name);
        for (Note note : notes) {
            sum += note.getGrade();
        }
        return sum / notes.size();
    }

    @Override
    public void clear() {
        iNoteStorage.clear();
    }
}
