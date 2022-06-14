package com.andre.test.api.easynotes.controller;

import com.andre.test.api.easynotes.exception.ResourceNotFoundException;
import com.andre.test.api.easynotes.model.Note;
import com.andre.test.api.easynotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @GetMapping("/notes")
    public List<Note> getAllNotes(@RequestHeader(value="Authorization") String authorizationHeader) {
        return noteRepository.findAll();
    }

    @PostMapping("/notes")
    public Note createNote(@RequestHeader(value="Authorization")String authorizationHeader, @Valid @RequestBody Note note) {
        return noteRepository.save(note);
    }

    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value = "id") Long noteId, @RequestHeader(value="Authorization")String authorizationHeader) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable(value = "id") Long noteId, @RequestHeader(value="Authorization")String authorizationHeader,
                                           @Valid @RequestBody Note noteDetails) {

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updatedNote = noteRepository.save(note);
        return updatedNote;
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId, @RequestHeader(value="Authorization")String authorizationHeader) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}
