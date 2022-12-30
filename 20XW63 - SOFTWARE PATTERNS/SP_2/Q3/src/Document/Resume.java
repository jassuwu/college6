package Document;

import Page.*;

public class Resume extends Document {

    @Override
    public Page createInstance() {
        return new ExperiencePage();
    }
}
