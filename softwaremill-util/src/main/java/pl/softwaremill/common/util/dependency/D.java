package pl.softwaremill.common.util.dependency;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Adam Warski (adam at warski dot org)
 */
public class D {
    private final static Logger log = LoggerFactory.getLogger(D.class);

    private static ThreadLocalDependencyProvider threadLocalDependencyProvider = new ThreadLocalDependencyProvider();

    private static LinkedList<DependencyProvider> providers = new LinkedList<DependencyProvider>() {{ add(threadLocalDependencyProvider); }};

    /**
     * Try to find the given dependency in the registered providers.
     * @throws RuntimeException If the dependency is not found in any provider.
     */
    public static <T> T inject(Class<T> cls, Annotation... qualifiers) {
        for (DependencyProvider provider : providers) {
            T result = provider.inject(cls, qualifiers);
            if (result != null) {
                return result;
            }
        }

        throw new RuntimeException("No dependencies of class " + cls + " (" + Arrays.toString(qualifiers) + ") found using providers: " + providers);
    }

    /**
     * @param provider A new dependency provider to add. The provider will be checked first when looking for
     * dependencies.
     */
    public static void register(DependencyProvider provider) {
        log.debug("Registering " + provider);
        providers.addFirst(provider);
    }

    public static void unregister(DependencyProvider provider) {
        log.debug("Unregistering " + provider);
        providers.remove(provider);
    }

    public static <T> T withDependencies(Object dep1, Callable<T> what) throws Exception {
        return withDependencies(ImmutableList.of(dep1), what);
    }

    public static <T> T withDependencies(Object dep1, Object dep2,
                                         Callable<T> what) throws Exception {
        return withDependencies(ImmutableList.of(dep1, dep2), what);
    }

    public static <T> T withDependencies(Object dep1, Object dep2, Object dep3,
                                         Callable<T> what) throws Exception {
        return withDependencies(ImmutableList.of(dep1, dep2, dep3), what);
    }

    public static <T> T withDependencies(List<Object> deps, Callable<T> what) throws Exception {
        for (Object dep : deps) {
            threadLocalDependencyProvider.register(dep);
        }

        try {
            return what.call();
        } finally {
            for (Object dep : deps) {
                threadLocalDependencyProvider.unregister(dep);
            }
        }
    }
}
