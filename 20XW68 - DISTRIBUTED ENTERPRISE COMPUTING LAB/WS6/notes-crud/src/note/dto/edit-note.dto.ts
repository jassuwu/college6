import { IsInt, IsNotEmpty, IsOptional, IsString } from "class-validator";

export class EditNoteDto {
    @IsInt()
    @IsNotEmpty()
    id: number;

    @IsString()
    @IsOptional()
    name?: string;

    @IsString()
    @IsOptional()
    content?: string;
}
