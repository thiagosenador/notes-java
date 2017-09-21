package com.tss.notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tss.notes.model.Note;
import com.tss.notes.service.NoteService;

@Controller
@RequestMapping("/thiago")
public class NotesController {

	@Autowired
	private NoteService noteService;

	@RequestMapping(method = RequestMethod.GET)
	public String printHello(ModelMap model) {

		this.noteService.createNote(Note.builder().content("test").build());

		return "hello";
	}

	public void setNoteService(NoteService noteService) {
		this.noteService = noteService;
	}
}