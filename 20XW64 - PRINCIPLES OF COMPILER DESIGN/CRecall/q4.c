#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Program to classify a word as a C keyword or not.
int main(int argc, char *argv[]) {
    // get a word
    char word[100];
    printf("Enter a word: ");
    gets(word);

    // check if the word is a C keyword
    int i, isKeyword = 0;
    char keywords[32][10] = {"auto", "break", "case", "char", "const", "continue", "default", "do", "double", "else", "enum", "extern", "float", "for", "goto", "if", "int", "long", "register", "return", "short", "signed", "sizeof", "static", "struct", "switch", "typedef", "union", "unsigned", "void", "volatile", "while"};
    for (i = 0; i < 32; i++) {
        if (strcmp(word, keywords[i]) == 0) {
            isKeyword = 1;
            break;
        }
    }

    // print the result
    if (isKeyword) {
        printf("The word is a C keyword. \n");
    } else {
        printf("The word is not a C keyword. \n");
    }

    return 0;
}