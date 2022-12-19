public class M3LazyInitialization {
    private static M3LazyInitialization instance;

    private M3LazyInitialization() {
    }

    public static M3LazyInitialization getInstance() {
        if (instance == null) {
            instance = new M3LazyInitialization();
        }
        return instance;
    }
}
