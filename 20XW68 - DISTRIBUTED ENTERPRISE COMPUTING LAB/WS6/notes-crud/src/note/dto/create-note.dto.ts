import { IsInt, IsNotEmpty, IsOptional, IsString } from "class-validator";

export class CreateNoteDto {
    @IsInt()
    @IsNotEmpty()
    id: number;

    @IsString()
    @IsNotEmpty()
    name: string;

    @IsString()
    @IsOptional()
    content: string;
}
