import { Module } from '@nestjs/common';
import { ConfigModule } from '@nestjs/config';
import { NoteModule } from './note/note.module';
import { PrismaModule } from './prisma/prisma.module';

@Module({
  imports: [ConfigModule.forRoot({ isGlobal: true, }), NoteModule, PrismaModule],
})
export class AppModule {}
