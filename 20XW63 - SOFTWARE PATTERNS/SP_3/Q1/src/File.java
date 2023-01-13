public class File implements Item {
    private String name;

    public File(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public void delete() {
        System.out.println(this.getName() + " deleted from the universe.");
    }
}
