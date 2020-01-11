/**
 * 双重检测机制，线程安全
 */
public class ThreadSafeDoubleCheckLocking {

    private static volatile ThreadSafeDoubleCheckLocking instance;

    private static boolean flag = true;

    private ThreadSafeDoubleCheckLocking() {
        // 防止通过反射进行实例化
        if (flag) {
            flag = false;
        } else {
            throw new IllegalStateException("Already initialized.");
        }
    }

    public static ThreadSafeDoubleCheckLocking getInstance() {
        ThreadSafeDoubleCheckLocking result = instance;

        if (result == null) {
            // 它尚未被初始化，但是并发下我们不能确定，因为其他相同的线程可能有他的实例
            // 于此同时，我们需要在这里进行初始化
            // 所以我们要互斥地锁定一个对象
            synchronized (ThreadSafeDoubleCheckLocking.class) {
                // 再次将实例分配给局部变量，用来检测其是否被其他线程初始化
                result = instance;

                // 如果有，直接返回
                if (result == null) {
                    instance = result = new ThreadSafeDoubleCheckLocking();
                }
            }
        }

        return result;
    }
}
