package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    private Map<Long, T> map = new HashMap();

    Set<T> findAll() {
        return new HashSet(map.values());
    }

    T findById(Long id){
        return map.get(id);
    }

    T save(T object) {
        if (object.getId() == null) {
            Long nextId = getNextId();
            object.setId(nextId);
        }
        map.put(object.getId(), object);
        return object;
    }

    void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getKey().equals(object));
    }

    void deleteById(Long id) {
        map.remove(id);
    }

    private Long getNextId() {
        Set<Long> entries = map.keySet();
        if (entries.isEmpty()) {
            return 1L;
        }
        return Collections.max(entries) + 1;
    }
}
