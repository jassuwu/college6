public class M5BillPughSingleton {
    private M5BillPughSingleton() {
    }

    private static class SingletonHelper {
        private static final M5BillPughSingleton INSTANCE = new M5BillPughSingleton();
    }

    public static M5BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
