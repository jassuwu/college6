public class M2StaticBlockInitialization {
    private static M2StaticBlockInitialization instance;

    private M2StaticBlockInitialization() {
    }

    static {
        try {
            instance = new M2StaticBlockInitialization();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static M2StaticBlockInitialization getInstance() {
        return instance;
    }
}
