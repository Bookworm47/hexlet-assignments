package exercise;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

// BEGIN
class Validator {

    public static List<String> validate(Address address) {
        List<String> result = new ArrayList<>();
        Field[] fields = address.getClass().getDeclaredFields();

        for (Field f : fields) {
            NotNull notNull = f.getAnnotation(NotNull.class);
            if (notNull != null) {
                f.setAccessible(true);
                try {
                    if (f.get(address) == null) {
                        result.add(f.getName());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> result = new HashMap<>();

        Field[] fields = address.getClass().getDeclaredFields();

        for (Field f : fields) {
            List<String> error = new ArrayList<>();
            NotNull notNull = f.getAnnotation(NotNull.class);
            MinLength minLength = f.getAnnotation(MinLength.class);
            f.setAccessible(true);
            if (notNull != null) {
                error.add("can not be null");
            }
            if (minLength != null) {
                int min = minLength.minLength();
                try {
                    if (min > f.get(address).toString().length()) {
                        error.add(String.format("length less than %d", min));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            result.put(f.getName(), error);
        }
        return result;
    }
}
// END
