package dev.matheuslf.desafio.inscritos.specification;

import dev.matheuslf.desafio.inscritos.domain.model.Priority;
import dev.matheuslf.desafio.inscritos.domain.model.Status;
import dev.matheuslf.desafio.inscritos.domain.model.Task;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.util.Date;

public class TaskFilter {

    public static Specification<Task> taskFilterTitle(String title){
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(title)) {
                return null;
            }
            return builder.like(root.get("title"), "%" + title + "%");
        };
    }

    public static Specification<Task> taskFilterDescription(String description) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(description)) return null;
            return builder.like(root.get("description"), "%" + description + "%");
        };
    }

    public static Specification<Task> taskFilterProjectId(Long id){
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(id)) return null;
            return root.join("project").get("id").in(id);
        };
    }

    public static Specification<Task> taskFilterStatus(Status status){
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(status)) return null;
            return builder.equal(root.get("status"), status);
        };
    }

    public static Specification<Task> taskFilterPriority(Priority priority){
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(priority)) return null;
            return builder.equal(root.get("priority"), priority);
        };
    }
}
