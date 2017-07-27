package base;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by antonandreev on 08/04/2017.
 */
public class Context {
    private Map<Class <?>, Object> context = new HashMap<>();

    public void add(Class<?> clazz, Object object) {
            context.put(clazz, object);
    }

    public <T> T get(Class<T> key) {
        return (T) context.get(key);
    }
}
