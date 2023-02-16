import { Injectable, ForbiddenException } from '@nestjs/common';
import { PrismaService } from 'src/prisma/prisma.service';
import { CreateNoteDto } from './dto/create-note.dto';
import { EditNoteDto } from './dto/edit-note.dto';

@Injectable()
export class NoteService {
    constructor(private prisma: PrismaService) {}

    getNotes() {
        return this.prisma.note.findMany();
    }

    getNoteById(noteId: number) {
        const note = this.prisma.note.findFirst({
            where: {
                id: noteId,
            },
        });
        return note;
    }

    async createNote(dto: CreateNoteDto ) {
        const note = await this.prisma.note.create({
            data: {
                ...dto,
            }
        });
        return note;
    }

    async editNote(noteId: number, dto: EditNoteDto) {
        const note = await this.prisma.note.findUnique({
            where: {
                id: noteId,
            }
        });
        if (!note) {
            throw new ForbiddenException(
                'Access to resource denied.',
            );
        }

        return this.prisma.note.update({
            where: {
                id: noteId,
            },
            data: {
                ...dto,
            }
        });
    }

    async deleteNote(noteId: number) {
        const note = await this.prisma.note.findUnique({
            where: {
                id: noteId,
            }
        });
        if (!note) {
            throw new ForbiddenException(
                'Access to resource denied.',
            );
        }

        await this.prisma.note.delete({
            where: {
                id:noteId,
            }
        });
    }
}
