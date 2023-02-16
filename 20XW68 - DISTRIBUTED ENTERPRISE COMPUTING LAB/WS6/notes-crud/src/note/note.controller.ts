import { Controller, Get, Post, Patch, Delete, Param, Body } from '@nestjs/common';
import { ParseIntPipe } from '@nestjs/common/pipes';
import { CreateNoteDto } from './dto/create-note.dto';
import { EditNoteDto } from './dto/edit-note.dto';
import { NoteService } from './note.service';

@Controller('notes')
export class NoteController {
    constructor(private noteService: NoteService) {}

    @Get()
    getNotes() {
        return this.noteService.getNotes();
    }

    @Get(':id')
    getNoteById(@Param('id', ParseIntPipe) noteId: number) {
        return this.noteService.getNoteById(noteId);
    }

    @Post()
    createNote(@Body() dto: CreateNoteDto) {
        return this.noteService.createNote(dto);
    }

    @Patch(':id')
    editNote(@Param('id', ParseIntPipe) noteId: number, @Body() dto: EditNoteDto) {
        return this.noteService.editNote(noteId, dto);
    }

    @Delete(':id')
    deleteNote(@Param('id', ParseIntPipe) noteId: number) {
        return this.noteService.deleteNote(noteId);
    }
}
