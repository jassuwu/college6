#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Program to count the number of vowels, consonants, digits and white spaces and special characters in a string.
int main(int argc, char *argv[])
{
    char str[100];
    int i, vowels, consonants, digits, spaces, special;
    vowels = consonants = digits = spaces = special = 0;

    printf("Enter a string: ");
    gets(str);

    for(i = 0; str[i] != '\0'; i++)
    {
        if(str[i] == 'a' || str[i] == 'e' || str[i] == 'i' || str[i] == 'o' || str[i] == 'u' || str[i] == 'A' || str[i] == 'E' || str[i] == 'I' || str[i] == 'O' || str[i] == 'U')
        {
            vowels++;
        }
        else if((str[i] >= 'a' && str[i] <= 'z') || (str[i] >= 'A' && str[i] <= 'Z'))
        {
            consonants++;
        }
        else if(str[i] >= '0' && str[i] <= '9')
        {
            digits++;
        }
        else if(str[i] == ' ')
        {
            spaces++;
        }
        else
        {
            special++;
        }
    }

    printf("Vowels: %d \n", vowels);
    printf("Consonants: %d \n", consonants);
    printf("Digits: %d \n", digits);
    printf("White spaces: %d \n", spaces);
    printf("Special characters: %d \n", special);
    return 0;   
}