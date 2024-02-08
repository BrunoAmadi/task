package org.uniondev.task.models.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.uniondev.task.models.task.entity.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JPATaskRepositoryInterface extends JpaRepository<TaskEntity, UUID> {

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO TB_TASK(id_task, nm_title, ds_description, st_status, dt_created, dt_updated)
            VALUES (taskentity.id,
                    taskentity.title,
                    taskentity.description,
                    taskentity.status,
                    current_timestamp,
                    current_timestamp)
            """, nativeQuery = true)
    int saveTask(TaskEntity taskEntity);

    @Modifying
    @Transactional
    @Query(value = """
                    UPDATE TB_TASK SET
                    nm_title = :title,
                    ds_description = :description,
                    st_status = :status,
                    dt_updated = current_timestamp
                    WHERE id_task = :id
            """, nativeQuery = true)
    int updateTask(String id, String title, String description, String status);


    @Query(value = """
                SELECT * FROM TB_TASK
                WHERE nm_title LIKE :title%
                ORDER BY nm_title ASC
                LIMIT :size
                OFFSET :offset
                
                
            """, nativeQuery = true)
    List<TaskEntity> findAllByTitle(String title, int size, int offset);

    @Modifying
    @Transactional
    @Query(value = """
                    DELETE FROM TB_TASK
                    WHERE id_task = :id
            """, nativeQuery = true)
    int deleteTaskById(UUID id);
}
