package com.krotos;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Answers;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

class NoteServiceImplTest {
    private INoteService noteService;
    double delta=0.005;
    private INoteStorage storage;
    Multimap<String,Note> notes;

    //to samo co w NoteStorageMock.class (drugi mock)
    private INoteStorage createMockStorage(){
        INoteStorage noteStorage=mock(INoteStorage.class);

        doAnswer(invocation -> {
            Note note=(Note) invocation.getArgument(0);
            notes.put(note.getName(),note);
            return null;
        }).when(noteStorage).add(any(Note.class));

        doAnswer((Answer<Void>) i->{
            notes.clear();
            return null;
        }).when(noteStorage).clear();

        doAnswer(i->{
            String name=i.getArgument(0);
            return notes.get(name);
        }).when(noteStorage).getAllNotesOf(any(String.class));

        return noteStorage;
    }

    @BeforeEach
    public void before() {
       // noteService = NoteServiceImpl.createWith(new NoteStorageMock());
        storage=createMockStorage();
        noteService=NoteServiceImpl.createWith(storage);
        notes=ArrayListMultimap.create();
    }

    @Test
    public void addNull() {
        assertThrows(IllegalArgumentException.class, () -> noteService.add(null));
    }

    @Test
    public void add() {
        noteService.add(new Note("Jan Mohamed", 5.0));
    }

    @Test
    public void avgOfNull() {
        assertEquals(0,noteService.avgOf(null),delta);
    }
    @Test
    public void avgOfNonExisting(){
        assertEquals(0.0,noteService.avgOf("cos tam"),delta);
    }
    @Test
    public void avgOf(){
        noteService.add(new Note("Pawel",3));
        noteService.add(new Note("Pawel",5));
        noteService.add(new Note("Pawel",4));
        noteService.add(new Note("Pawel",4));
        noteService.add(new Note("Kamil",2));
        assertEquals(4,noteService.avgOf("Pawel"),delta);
        assertEquals(2,noteService.avgOf("Kamil"),delta);
    }
    @Test
    public void clear(){
        noteService.add(new Note("Albert Kw",4));
        noteService.add(new Note("Albert Kw",2.0));
        assertEquals(3,noteService.avgOf("Albert Kw"),delta);
        noteService.clear();
        assertEquals(0,noteService.avgOf("Albert Kw"),delta);

    }

}