#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Program to extract the C keywords from a set of words.
int main(int argc, char *argv[]) {
    // get a set of words
    char str[100];
    printf("Enter a set of words: ");
    gets(str);

    // extract the C keywords from the set of words
    int i, j, k, isKeyword;
    char keywords[32][10] = {"auto", "break", "case", "char", "const", "continue", "default", "do", "double", "else", "enum", "extern", "float", "for", "goto", "if", "int", "long", "register", "return", "short", "signed", "sizeof", "static", "struct", "switch", "typedef", "union", "unsigned", "void", "volatile", "while"};
    char result[100] = "";
    for (i = 0; str[i] != '\0'; i++) {
        if (str[i] == ' ') {
            char word[100] = "";
            for (j = i - 1; j >= 0; j--) {
                if (str[j] == ' ') {
                    break;
                }
                word[strlen(word)] = str[j];
            }
            for (j = 0; j < strlen(word) / 2; j++) {
                char temp = word[j];
                word[j] = word[strlen(word) - j - 1];
                word[strlen(word) - j - 1] = temp;
            }
            isKeyword = 0;
            for (j = 0; j < 32; j++) {
                if (strcmp(word, keywords[j]) == 0) {
                    isKeyword = 1;
                    break;
                }
            }
            if (isKeyword) {
                strcat(result, word);
                strcat(result, " ");
            }
        }
    }

    // print the result
    printf("Result: %s \n", result);

    return 0;
}