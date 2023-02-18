// The Validator interface that has the validate fn declaration
class CreditCard {
    private accountNumber: string;
    private holderName: string;

    constructor(accountNumber: string, holderName: string) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
    }

    public getAccountNumber() : string {
        return this.accountNumber;
    }

    public setAccountNumber(accountNumber: string) {
        this.accountNumber = accountNumber;
    }

    public getHolderName() : string {
        return this.holderName;
    }

    public setHolderName(holderName: string) {
        this.holderName = holderName;
    }
}

interface Validator {
    validateCC(creditCard: CreditCard): boolean;
}

class Validation {
    private validator: Validator;

    constructor(validator: Validator) {
        this.validator = validator;
    }

    public setValidator(validator: Validator) {
        this.validator = validator;
    }

    public validate(creditCard: CreditCard) : boolean {
        return this.validator.validateCC(creditCard);
    }
}

class LuhnValidator implements Validator {
    
    public validateCC(creditCard: CreditCard): boolean {
        const digits = creditCard.getAccountNumber().split('').map(Number);
        console.log(`The digits of the ${creditCard.getHolderName()} : ${digits}`);
        const sum = digits.reduce((acc, digit, index) => {
            if (index % 2 === digits.length % 2) {
                const doubled = digit * 2;
                return acc + (doubled > 9 ? doubled - 9 : doubled);
            }
            return acc + digit;
        }, 0);
        return sum % 10 === 0;
    }
}

class RegexValidator implements Validator {

    public validateCC(creditCard: CreditCard): boolean {
        const regex = /^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\d{3})\d{11})$/;
        return regex.test(creditCard.getAccountNumber());
    }
}


// Create a creditCard objects
const validCreditCard = new CreditCard('371449635398431', 'ValidDude');
const invalidCreditCard = new CreditCard('1234567890123456', 'InvalidDude');

// Create a Valdation with a Validator
const validation = new Validation(new LuhnValidator());

console.log(`CC #1 is valid: ${validation.validate(validCreditCard)}`);
console.log(`CC #2 is valid: ${validation.validate(invalidCreditCard)}`);

