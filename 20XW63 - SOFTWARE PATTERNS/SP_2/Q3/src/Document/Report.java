package Document;

import Page.Page;
import Page.SkillsPage;
public class Report extends Document{

    @Override
    public Page createInstance() {
        return new SkillsPage();
    }
}
