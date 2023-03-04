interface Subscriber {
    name: string;
    update(news: string): void;
}

class NewsAgency {
    private subscribers: Subscriber[] = [];

    subscribe(subscriber: Subscriber) {
        console.log('New subscriber added.');
        this.subscribers.push(subscriber);
    }

    unsubscribe(subscriber: Subscriber) {
        if (this.subscribers.indexOf(subscriber) !== -1) {
            console.log('Subscriber removed.');
            this.subscribers.splice(this.subscribers.indexOf(subscriber));
        } else {
            console.log('No such subscription for unsubsription.');
        }
    }

    notify(news: string) {
        console.log('Notifying subscribers...');
        this.subscribers.forEach((subscriber) => subscriber.update(news));
    }

    // The business logic of the news agency happens here.
    publishNews(news: string) {
        console.log(`Publishing news.`)
        this.notify(news);
    }

}

class SMSSubscriber implements Subscriber {
    name: string;
    private phonenumber: string;

    constructor(name: string, phonenumber: string) {
        this.name = name;
        this.phonenumber = phonenumber;
    }

    update(news: string) {
        console.log(`Sending SMS to ${this.name} [${this.phonenumber}]: ${news}`);
    }
}

class EmailSubscriber implements Subscriber {
    name: string;
    private email: string;

    constructor(name: string, email : string) {
        this.name = name;
        this.email = email;
    }

    update(news: string) {
        console.log(`Sending email to ${this.name} [${this.email}]: ${news}`);
    }
}

// Client code

const newsAgency = new NewsAgency();

const jojo = new SMSSubscriber('Jojo','123-456-7890');
const dio = new EmailSubscriber('Dio','dio@brando.jojo');

newsAgency.subscribe(jojo);
newsAgency.subscribe(dio);

newsAgency.publishNews('New season of JoJo\'s Bizzare Adventure is coming out!');

newsAgency.unsubscribe(dio);

newsAgency.publishNews('Dio might not be making an appearance this season :o ');
