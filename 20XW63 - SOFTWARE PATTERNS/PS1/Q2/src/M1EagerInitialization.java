public class M1EagerInitialization {
    private static final M1EagerInitialization instance = new M1EagerInitialization();

    private M1EagerInitialization() {
    }

    public static M1EagerInitialization getInstance() {
        return instance;
    }
}
