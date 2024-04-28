package com.accounts.entity;

import org.junit.jupiter.api.Test;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BaseEntityTest {

    @Test
    void testBaseEntity() {
        LocalDateTime now = LocalDateTime.now();
        String createdBy = "admin";
        String updatedBy = "user";

        BaseEntity baseEntity = new BaseEntity();
        baseEntity.setCreatedAt(now);
        baseEntity.setCreatedBy(createdBy);
        baseEntity.setUpdatedAt(now);
        baseEntity.setUpdatedBy(updatedBy);

        assertEquals(now, baseEntity.getCreatedAt());
        assertEquals(createdBy, baseEntity.getCreatedBy());
        assertEquals(now, baseEntity.getUpdatedAt());
        assertEquals(updatedBy, baseEntity.getUpdatedBy());
    }

    @Test
    void testAnnotations() throws NoSuchFieldException {
        assertNotNull(BaseEntity.class.getDeclaredField("createdAt").getAnnotation(CreatedDate.class));
        assertNotNull(BaseEntity.class.getDeclaredField("createdBy").getAnnotation(CreatedBy.class));
        assertNotNull(BaseEntity.class.getDeclaredField("updatedAt").getAnnotation(LastModifiedDate.class));
        assertNotNull(BaseEntity.class.getDeclaredField("updatedBy").getAnnotation(LastModifiedBy.class));
    }
}
