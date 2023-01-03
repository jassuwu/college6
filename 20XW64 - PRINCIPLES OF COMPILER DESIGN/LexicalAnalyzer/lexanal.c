// @jassuwu - 20pw14

// Sample run: 
// INPUT: 
// Enter a string with tokens separated by spaces: if a > b c = a else c = b
// OUTPUT:
// if: keyword
// a: identifier
// >: relational operator
// b: identifier
// c: identifier
// =: relational operator
// a: identifier
// else: keyword
// c: identifier
// =: relational operator
// b: identifier
// The entered stream has been verified.


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <unistd.h>

// Lexical Analyzer simulating a state transition diagram

// Keywords : if, else, int, char, 
// Identifiers : [a-zA-Z][a-zA-Z0-9]*
// Integers : [0-9]+
// Operators : +, -, *, /
// Relational Operators, Assignment operator, and NOT operator:  =, ==, !, !=, <, <=, >, >=

// Function declarations

int failToNextTokenType(int state) {
    if (state >= 0 && state <= 17) 
        return 18; // state 18 is initial state for identifiers
     else if (state >= 18 && state <= 20) 
        return 21; // state 21 is initial state for integers
     else if (state >= 21 && state <= 23) 
        return 24; // state 24 is initial state for operators
     else if (state >= 24 && state <= 25) 
        return 26; // state 26 is initial state for relational operators
     else 
        return -1; // error state
}


void lex(char* string) {
    int n = strlen(string);
    int state = 0;
    bool verifiedFlag = false;
    int i = 0;
    while(true) {
        switch(state) {
            case 0:
                if (verifiedFlag) {
                    return;
                }
                if (string[i] == 'c') {state=1; i++;}
                else if (string[i] == 'e') {state=6; i++;}
                else if (string[i] == 'i') {state=11; i++;}
                else {state = failToNextTokenType(state); i=0;}
                break;
            case 1:
                if (string[i] == 'h') {state=2; i++;}
                else {state = failToNextTokenType(state); i=0;}
                break;
            case 2:
                if (string[i] == 'a') {state=3; i++;}
                else {state = failToNextTokenType(state); i=0;}
                break;
            case 3:
                if (string[i] == 'r') {state=4; i++;}
                else {state = failToNextTokenType(state); i=0;}
                break;
            case 4:
                if (string[i] == '\0') {state=5;}
                else {state = failToNextTokenType(state); i=0;}
                break;
            case 5:
                printf("keyword");
                verifiedFlag = true;
                state=0;
                return;
            case 6:
                if (string[i] == 'l') {state=7; i++;}
                else {state = failToNextTokenType(state); i=0;}
                break;
            case 7: 
                if (string[i] == 's') {state=8; i++;}
                else {state = failToNextTokenType(state); i=0;}
                break;
            case 8:
                if (string[i] == 'e') {state=9; i++;}
                else {state = failToNextTokenType(state); i=0;}
                break;
            case 9:
                if (string[i] == '\0') {state=10;}
                else {state = failToNextTokenType(state); i=0;}
                break;
            case 10:
                printf("keyword");
                verifiedFlag = true;
                state=0;
                break;
            case 11:
                if (string[i] == 'n') {state=12; i++;}
                else if (string[i] == 'f') {state=16; i++;}
                else {state = failToNextTokenType(state); i=0;}
                break;
            case 12:
                if (string[i] == 't') {state=13; i++;}
                else {state = failToNextTokenType(state); i=0;}
                break;
            case 13:
                if (string[i] == '\0') {state=14;}
                else {state = failToNextTokenType(state); i=0;}
                break;
            case 14:
                printf("keyword");
                verifiedFlag = true;
                state=0;
                break;
            case 15:
                if (string[i] == 'f') {state=16; i++;}
                else {state = failToNextTokenType(state); i=0;}
                break;
            case 16:
                if (string[i] == '\0') {state=17;}
                else {state = failToNextTokenType(state); i=0;}
                break;
            case 17:
                printf("keyword");
                verifiedFlag = true;
                state=0;
                break;
            
            // Identifiers
            case 18:
                if ((string[i] >= 'a' && string[i] <= 'z') || (string[i] >= 'A' && string[i] <= 'Z')) {state=19; i++;}
                else {state = failToNextTokenType(state); i=0;}
                break;

            case 19:
                if ((string[i] >= 'a' && string[i] <= 'z') || (string[i] >= 'A' && string[i] <= 'Z') || (string[i] >= '0' && string[i] <= '9')) i++;
                else if (string[i] == '\0') {state = 20;}
                else {state = failToNextTokenType(state); i=0;}
                break;
            
            case 20:
                printf("identifier");
                verifiedFlag = true;
                state=0;
                break;
            
            // Integers
            case 21:
                if (string[i] >= '0' && string[i] <= '9') {state=22; i++;}
                else {state = failToNextTokenType(state); i=0;}
                break;

            case 22:
                if (string[i] >= '0' && string[i] <= '9') i++;
                else if (string[i] == '\0') {state = 23;}
                else {state = failToNextTokenType(state); i=0;}
                break;

            case 23:
                printf("integer");
                verifiedFlag = true;
                state=0;
                break;

            // Operators
            case 24:
                if (string[i] == '+') {state=25; i++;}
                else if (string[i] == '-') {state=25; i++;}
                else if (string[i] == '*') {state=25; i++;}
                else if (string[i] == '/') {state=25; i++;}
                else {state = failToNextTokenType(state); i=0;}
                break;
            
            case 25:
                if (string[i] == '\0') { verifiedFlag = true; state=0; printf("operator"); return;}
                else {state = failToNextTokenType(state); i=0;}
                break;
            // Relational Operators
            case 26:
                if (string[i] == '<') {state=27; i++;}
                else if (string[i] == '>') {state=30; i++;}
                else if (string[i] == '=') {state=33; i++;}
                else if (string[i] == '!') {state=36; i++;}
                else {state = failToNextTokenType(state); i=0;}
                break;
            
            case 27:
                if (string[i] == '=') {state=28; i++;}
                else if (string[i] == '\0') {state=29;}
                else {state = failToNextTokenType(state); i=0;}
                break;

            case 28:
                printf("relational operator");
                verifiedFlag = true;
                state=0;
                break;
            
            case 29:
                printf("relational operator");
                verifiedFlag = true;
                state=0;
                break;

            case 30:
                if (string[i] == '=') {state=31; i++;}
                else if (string[i] == '\0') {state=32;}
                else {state = failToNextTokenType(state); i=0;}
                break;

            case 31:
                printf("relational operator");
                verifiedFlag = true;
                state=0;
                break;

            case 32:
                printf("relational operator");
                verifiedFlag = true;
                state=0;
                break;

            case 33:
                if (string[i] == '=') {state=34; i++;}
                else if (string[i] == '\0') {state=35;}
                else {state = failToNextTokenType(state); i=0;}
                break;

            case 34:
                printf("relational operator");
                verifiedFlag = true;
                state=0;
                break;

            case 35:
                printf("relational operator");
                verifiedFlag = true;
                state=0;
                break;

            case 36:
                if (string[i] == '=') {state=37; i++;}
                else if (string[i] == '\0') {state=38;}
                else {state = failToNextTokenType(state); i=0;}
                break;

            case 37:
                if (string[i] == '\0') {state=38;}
                else {state = failToNextTokenType(state); i=0;}
                
            case 38:
                printf("relational operator");
                verifiedFlag = true;
                state=0;
                break;

            default:
                printf("InvalidStateError: %d : This state shouldn't be possible.", state);
                printf("The entered string is not a valid stream of tokens.");
                return;          
        }
    }
}

int main() {
    char string[100];
    
    printf("Enter a string with tokens separated by spaces: ");
    scanf("%[^\n]s", string);

    char *token = strtok(string, " ");
    while(token != NULL) {
        printf("%s: ", token);
        lex(token);
        printf("\n");
        token = strtok(NULL, " ");
    }
    printf("The entered stream has been verified.");
    return 0;
}