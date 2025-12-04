package mk.ukim.finki.wp.lab.service.specification;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class FieldFilterSpecification {

    // Филтер за текст пребарување со LIKE '%value%'
    public static <T> Specification<T> filterContainsText(String field, String value) {
        if (value == null || value.isEmpty()) {
            return null;  // Прескокни ако е празно
        }

        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get(field)),
                "%" + value.toLowerCase() + "%"
        );
    }

    // Филтер за точно совпаѓање (за ID, енуми)
    public static <T> Specification<T> filterEquals(String field, Long value) {
        if (value == null) {
            return null;
        }

        return (root, query, criteriaBuilder) -> {
            Path<T> path = getPath(field, root);
            return criteriaBuilder.equal(path, value);
        };
    }

    // Филтер за броеви (rating, price, итн.)
    public static <T> Specification<T> filterGreaterThanOrEqual(String field, Double value) {
        if (value == null) {
            return null;
        }

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get(field), value);
    }


    private static <T> Path<T> getPath(String field, Root<T> root) {
        String[] parts = field.split("\\.");
        Path<T> result = root;
        for (String part : parts) {
            result = result.get(part);
        }
        return result;
    }
}