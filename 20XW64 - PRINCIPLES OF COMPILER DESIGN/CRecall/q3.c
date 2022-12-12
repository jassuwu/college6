#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Program to delete lines commented out with // from a set of lines.
int main(int argc, char *argv[]) {
    // get a set of lines
    char str[100];
    printf("Enter a set of lines: ");
    gets(str);

    // delete lines commented out with //
    int i, j;
    for (i = 0; str[i] != '\0'; i++) {
        if (str[i] == '/' && str[i + 1] == '/') {
            for (j = i; str[j] != '\0'; j++) {
                str[j] = str[j + 2];
            }
        }
    }

    // print the result
    printf("Result: %s \n", str);

    return 0;
}
