package com.accounts.audit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class AuditAwareImplTest {

    @MockBean
    private AuditorAware<String> auditorAware;

    @Test
    void testGetCurrentAuditor() {
        AuditAwareImpl auditAwareImpl = new AuditAwareImpl();
        when(auditorAware.getCurrentAuditor()).thenReturn(Optional.of("Ajtai_Alex"));

        Optional<String> result = auditAwareImpl.getCurrentAuditor();

        assertEquals(Optional.of("Ajtai_Alex"), result);
    }
}
