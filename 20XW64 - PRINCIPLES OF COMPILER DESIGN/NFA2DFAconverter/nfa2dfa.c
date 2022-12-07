#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <stdbool.h>

bool isPresentInUniqueStates(int *arr, int size, int element)
{
    for (int i = 0; i < size; i++)
    {
        if (arr[i] == element)
            return true;
    }
    return false;
}

int decimalToBinary(int num)
{
    if (num == 0)
    {
        return 0;
    }

    // Stores binary representation of number.
    int binaryNum[32]; // Assuming 32 bit integer.
    int i = 0;

    for (; num > 0;)
    {
        binaryNum[i++] = num % 2;
        num /= 2;
    }

    int binarynum = 0;
    for (int j = i - 1; j >= 0; j--)
        binarynum = binarynum * 10 + binaryNum[j];
    return binarynum;
}

int findIndexInStatesBin(int **states_bin, int size, int element)
{
    for (int i = 0; i < size; i++)
    {
        if (states_bin[i][0] == element)
            return i;
    }
    return -1;
}
void addStatesToDFA(int **dfa_table, int *unique_states, int unique_states_count, int **states_bin)
{
    int i = 0;
    while (true)
    {
        int allPresentFlag = 1;
        for (int j = 1; j < 3; j++)
        {
            if (!isPresentInUniqueStates(unique_states, unique_states_count, dfa_table[i][j]))
            {
                allPresentFlag = 0;
                unique_states[unique_states_count] = dfa_table[i][j];
                dfa_table[unique_states_count][0] = dfa_table[i][j];
                int binNum = decimalToBinary(dfa_table[i][j]);

                int zeroSum = 0, oneSum = 0, atDigit = 0;
                while (binNum > 0)
                {
                    if (binNum % 10)
                    {
                        int index = findIndexInStatesBin(states_bin, 1000, pow(2, atDigit));
                        zeroSum += states_bin[index][1];
                        oneSum += states_bin[index][2];
                    }
                    binNum /= 10;
                    atDigit++;
                }

                dfa_table[unique_states_count][1] = zeroSum;
                dfa_table[unique_states_count][2] = oneSum;
            }
        }
        if (allPresentFlag)
        {
            break;
        }
        unique_states_count++;
        i++;
    }
}

void printDFATableAsStates(int **dfa_table, int NO_OF_STATES)
{
    for (int dfaCounter = 0; dfaCounter < NO_OF_STATES; dfaCounter++)
    {
        printf("|");
        for (int innerCounter = 0; innerCounter < 3; innerCounter++)
        {
            int binNum = decimalToBinary(dfa_table[dfaCounter][innerCounter]);
            int atDigit = 0;
            while (binNum > 0)
            {
                if (binNum % 10)
                {
                    printf("%d", atDigit++);
                }
                binNum /= 10;
            }
            printf("|");
        }
        printf("\n");
    }
}

int main()
{
    // TAKE IN THE INPUT FROM THE FILE AND STORE IT IN A STATES ARRAY
    int MAX_NUMBER_OF_STATES = 1000;
    FILE *fp = fopen("input.txt", "r");
    char **states = (char **)malloc(MAX_NUMBER_OF_STATES * sizeof(char *));
    for (int i = 0; i < MAX_NUMBER_OF_STATES; i++)
        states[i] = (char *)malloc(1000 * sizeof(char));
    size_t NO_OF_STATES = 0;
    while (fgets(states[NO_OF_STATES++], 1000, fp) != NULL)
        ;
    NO_OF_STATES--; // To correct the error that occurs due to the last line of the file
    // PRINT THE STATES
    printf("States taken in as input: \n");
    for (int i = 0; i < NO_OF_STATES; i++)
    {
        printf("%s", states[i]);
    }
    printf("\n");
    fclose(fp);

    // CONVERT THE STATES INTO BINARY NUMBERS AND STORE THEM AS POWER OF 2
    int **states_bin = (int **)malloc(MAX_NUMBER_OF_STATES * sizeof(int *));
    for (int i = 0; i < MAX_NUMBER_OF_STATES; i++)
        states_bin[i] = (int *)malloc(1000 * sizeof(int));
    for (int i = 0; i < NO_OF_STATES; i++)
    {
        char *token = strtok(states[i], "|");
        int j = 0;
        while (token != NULL)
        {
            // if (strcmp(token, "-1") != 0)
            // {
            if (strlen(token) == 1)
                states_bin[i][j++] = pow(2, atoi(token));
            else
            {
                states_bin[i][j] = 0;
                for (int ch = 0; ch < strlen(token) - 1; ch++)
                {
                    states_bin[i][j] += pow(2, token[ch] - '0');
                }
                j++;
            }
            // }
            // else
            //     states_bin[i][j++] = -1;
            token = strtok(NULL, "|");
        }
    }
    // PRINT THE STATES AS NUMBERS
    printf("\nThe states as numbers: \n");
    for (int i = 0; i < NO_OF_STATES; i++)
    {
        printf("|");
        for (int j = 0; j < 3; j++)
        {
            printf("%d|", states_bin[i][j]);
        }
        printf("\n");
    }
    printf("\n");

    // DFA TABLE CREATION
    int **dfa_table = (int **)malloc(MAX_NUMBER_OF_STATES * sizeof(int *));
    for (int i = 0; i < MAX_NUMBER_OF_STATES; i++)
        dfa_table[i] = (int *)malloc(1000 * sizeof(int));

    int *unique_states = (int *)malloc(MAX_NUMBER_OF_STATES * sizeof(int));
    int unique_states_count = 0;

    // Add the first state to the DFA table
    dfa_table[0][0] = states_bin[0][0];
    dfa_table[0][1] = states_bin[0][1];
    dfa_table[0][2] = states_bin[0][2];
    unique_states[unique_states_count++] = states_bin[0][0];

    // Add all the other states to the DFA table
    addStatesToDFA(dfa_table, unique_states, unique_states_count, states_bin);

    // Print the DFA table
    printf("DFATable after filling with states: \n");
    for (int dfaCounter = 0; dfaCounter < NO_OF_STATES; dfaCounter++)
    {
        printf("|");
        for (int innerCounter = 0; innerCounter < 3; innerCounter++)
        {
            printf("%d|", dfa_table[dfaCounter][innerCounter]);
        }
        printf("\n");
    }

    // Print as states
    printf("\nDFATable after filling with states actually represented as states: \n");
    printDFATableAsStates(dfa_table, NO_OF_STATES);
    printf("\n");
    return 0;
}