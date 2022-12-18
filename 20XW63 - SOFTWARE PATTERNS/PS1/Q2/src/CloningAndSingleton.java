public class CloningAndSingleton implements Cloneable {
    private CloningAndSingleton() {
    }

    private static class SingletonHelper {
        private static final CloningAndSingleton INSTANCE = new CloningAndSingleton();
    }

    public static CloningAndSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // return super.clone();
        // This was added prevent Singleton destruction.
        throw new CloneNotSupportedException("AIN'T NO WAY YOU TRIED TO CLONE A SINGLETON INSTANCE BRO.");
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        CloningAndSingleton instanceOne = CloningAndSingleton.getInstance();
        CloningAndSingleton instanceTwo = ((CloningAndSingleton) instanceOne.clone());

        System.out.println(instanceOne.hashCode());
        System.out.println(instanceTwo.hashCode());

        // The above will result in two different hashCodes destroying the Singleton
        // Pattern unless we throw an exception when overriding the clone method.
    }
}
