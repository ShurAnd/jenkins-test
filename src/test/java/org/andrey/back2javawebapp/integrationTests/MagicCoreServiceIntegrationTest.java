package org.andrey.back2javawebapp.integrationTests;

import org.andrey.back2javawebapp.exceptions.MagicCoreEntryNotFoundException;
import org.andrey.back2javawebapp.exceptions.NotEnoughCoreForTransferringException;
import org.andrey.back2javawebapp.model.CoreTransferData;
import org.andrey.back2javawebapp.services.MagicCoreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class MagicCoreServiceIntegrationTest {

    @Autowired
    private MagicCoreService service;

    //@Test
    @DisplayName("test that core is transferred without exceptions")
    void transferCoreToAnother() {
        Long sourceId = 1L;
        Long destId = 2L;
        assertDoesNotThrow(() -> service.
                transferCoreToAnother(new CoreTransferData(sourceId, destId, new BigDecimal(100))));
    }

    //@Test
    @DisplayName("Should fail coz core record with id 1L not found")
    void failCozCoreWithId1NotFound() {
        Long sourceId = 11L;
        Long destId = 2L;

        assertThrows(MagicCoreEntryNotFoundException.class,
                () -> service.
                        transferCoreToAnother(new CoreTransferData(sourceId, destId, new BigDecimal(100))));
    }

    //@Test
    @DisplayName("Should fail coz core record with id 2L not found")
    void failCozCoreWithId2NotFound() {
        Long sourceId = 1L;
        Long destId = 22L;

        assertThrows(MagicCoreEntryNotFoundException.class,
                () -> service.
                        transferCoreToAnother(new CoreTransferData(sourceId, destId, new BigDecimal(100))));
    }

    //@Test
    @DisplayName("test that method throw exception about not enough core")
    void testNotEnoughCoreForTransferring() {
        Long sourceId = 1L;
        Long destId = 2L;

        assertThrows(NotEnoughCoreForTransferringException.class,
                () -> service.
                        transferCoreToAnother(new CoreTransferData(destId, sourceId, new BigDecimal(1000))));
    }
}