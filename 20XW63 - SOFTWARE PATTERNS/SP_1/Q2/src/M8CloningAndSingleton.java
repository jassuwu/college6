public class M8CloningAndSingleton implements Cloneable {
    private M8CloningAndSingleton() {
    }

    private static class SingletonHelper {
        private static final M8CloningAndSingleton INSTANCE = new M8CloningAndSingleton();
    }

    public static M8CloningAndSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // return super.clone();
        // This was added prevent Singleton destruction.
        throw new CloneNotSupportedException("AIN'T NO WAY YOU TRIED TO CLONE A SINGLETON INSTANCE BRO.");
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        M8CloningAndSingleton instanceOne = M8CloningAndSingleton.getInstance();
        M8CloningAndSingleton instanceTwo = ((M8CloningAndSingleton) instanceOne.clone());

        System.out.println(instanceOne.hashCode());
        System.out.println(instanceTwo.hashCode());

        // The above will result in two different hashCodes destroying the Singleton
        // Pattern unless we throw an exception when overriding the clone method.
    }
}
