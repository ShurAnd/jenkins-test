package org.andrey.back2javawebapp.unitTests.services;

import org.andrey.back2javawebapp.exceptions.MagicCoreEntryNotFoundException;
import org.andrey.back2javawebapp.exceptions.NotEnoughCoreForTransferringException;
import org.andrey.back2javawebapp.model.CoreTransferData;
import org.andrey.back2javawebapp.model.MagicCore;
import org.andrey.back2javawebapp.repositories.DataRepository;
import org.andrey.back2javawebapp.repositories.MagicCoreRepository;
import org.andrey.back2javawebapp.services.MagicCoreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class MagicCoreServiceTest {

    @Mock
    private MagicCoreRepository repository;

    @Mock
    private DataRepository dataRepository;

    @InjectMocks
    private MagicCoreService service;

    @Test
    @DisplayName("test that core is transferred without exceptions")
    void transferCoreToAnother() {
        Long sourceId = 1L;
        Long destId = 2L;

        MagicCore magicCoreSource = new MagicCore(sourceId, new BigDecimal(100), "Man 1");
        MagicCore magicCoreDest = new MagicCore(destId, new BigDecimal(200), "Man 2");
        Mockito.when(repository.findById(sourceId)).thenReturn(Optional.of(magicCoreSource));
        Mockito.when(repository.findById(destId)).thenReturn(Optional.of(magicCoreDest));
        service.transferCoreToAnother(new CoreTransferData(sourceId, destId, new BigDecimal(100)));

        Mockito.verify(repository).updateCoreAmount(sourceId, new BigDecimal(0));
        Mockito.verify(repository).updateCoreAmount(destId, new BigDecimal(300));
    }

    @Test
    @DisplayName("Should fail coz core record with id 1L not found")
    void failCozCoreWithId1NotFound() {
        Long sourceId = 1L;
        Long destId = 2L;

        Mockito.when(repository.findById(sourceId)).thenReturn(Optional.empty());
        assertThrows(MagicCoreEntryNotFoundException.class,
                () -> service.
                        transferCoreToAnother(new CoreTransferData(sourceId, destId, new BigDecimal(100))));
        Mockito.verify(repository, Mockito.never()).updateCoreAmount(sourceId, new BigDecimal(0));
        Mockito.verify(repository, Mockito.never()).updateCoreAmount(destId, new BigDecimal(300));
    }

    @Test
    @DisplayName("Should fail coz core record with id 2L not found")
    void failCozCoreWithId2NotFound() {
        Long sourceId = 1L;
        Long destId = 2L;

        MagicCore magicCoreSource = new MagicCore(sourceId, new BigDecimal(100), "Man 1");
        Mockito.when(repository.findById(sourceId)).thenReturn(Optional.of(magicCoreSource));
        Mockito.when(repository.findById(destId)).thenReturn(Optional.empty());
        assertThrows(MagicCoreEntryNotFoundException.class,
                () -> service.
                        transferCoreToAnother(new CoreTransferData(sourceId, destId, new BigDecimal(100))));
        Mockito.verify(repository, Mockito.never()).updateCoreAmount(sourceId, new BigDecimal(0));
        Mockito.verify(repository, Mockito.never()).updateCoreAmount(destId, new BigDecimal(300));
    }

    @Test
    @DisplayName("test that method throw exception about not enough core")
    void testNotEnoughCoreForTransferring() {
        Long sourceId = 1L;
        Long destId = 2L;

        MagicCore magicCoreSource = new MagicCore(sourceId, new BigDecimal(100), "Man 1");
        MagicCore magicCoreDest = new MagicCore(destId, new BigDecimal(200), "Man 2");
        Mockito.when(repository.findById(sourceId)).thenReturn(Optional.of(magicCoreSource));
        Mockito.when(repository.findById(destId)).thenReturn(Optional.of(magicCoreDest));
        assertThrows(NotEnoughCoreForTransferringException.class,
                () -> service.
                        transferCoreToAnother(new CoreTransferData(sourceId, destId, new BigDecimal(200))));

        Mockito.verify(repository, Mockito.never()).updateCoreAmount(sourceId, new BigDecimal(-100));
        Mockito.verify(repository, Mockito.never()).updateCoreAmount(destId, new BigDecimal(300));
    }
}