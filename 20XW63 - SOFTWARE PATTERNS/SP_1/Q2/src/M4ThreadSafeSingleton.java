public class M4ThreadSafeSingleton {
    private static M4ThreadSafeSingleton instance;

    private M4ThreadSafeSingleton() {
    }

    // Performance taxing
    public synchronized static M4ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new M4ThreadSafeSingleton();
        }
        return instance;
    }

    // DoubleCheckingLock
    public static M4ThreadSafeSingleton getInstanceUsingDoubleLocking() {
        if (instance == null) {
            synchronized (M4ThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new M4ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}
