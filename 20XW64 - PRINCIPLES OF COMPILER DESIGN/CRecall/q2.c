#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Program to attach line numbers to set of lines separated by a semicolon.
int main(int argc, char *argv[]) {
    // get a set of lines separated by a semicolon
    char str[100];
    printf("Enter a set of lines separated by a semicolon: ");
    gets(str);

    // attach line numbers to each line
    int i, line = 1;
    for (i = 0; str[i] != '\0'; i++) {
        if (str[i] == ';') {
            line++;
        }
        printf("%d: %c \n", line, str[i]);
    }

    // print the result
    printf("Result: %s \n", str);

    return 0;
}