package com.krotos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class NoteServiceImplTest {
    private INoteService noteService;
    double delta=0.005;

    @BeforeEach
    public void before() {
        noteService = NoteServiceImpl.createWith(new NoteStorageMock());
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