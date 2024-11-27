package br.dev.drufontael.our_recipes_api.adapters.web.dto;

import java.util.*;

public record Filters(Map<String,Object> filters) {

    public Map<String, List<String>> getFilters() {
        Map<String, List<String>> resultMap = new HashMap<>();

        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            if(entry.getValue() == null)  continue;
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof List<?>) {
                List<?> valueList = (List<?>) value;
                List<String> stringList = new ArrayList<>();

                for (Object item : valueList) {
                    if (item instanceof String) {
                        stringList.add((String) item);
                    } else {
                        throw new IllegalArgumentException("Item is not String: " + item);
                    }
                }
                resultMap.put(key, stringList);

            } else if (value instanceof String) {
                resultMap.put(key, Collections.singletonList((String) value));
            }

            else {
                throw new IllegalArgumentException("Incompatible value for key: " + key);
            }
        }

        return resultMap;
    }
}
