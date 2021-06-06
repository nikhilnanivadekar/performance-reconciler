package nikhil.nani.reconciler.ec.controller;

import nikhil.nani.data.bean.ReconcilerRequest;
import nikhil.nani.reconciler.ec.service.impl.EcForEachInBothReconcilerServiceImpl;
import nikhil.nani.reconciler.ec.service.impl.EcReconcilerServiceImpl;
import nikhil.nani.reconciler.ec.service.impl.EcZipReconcilerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EcReconcilerControllerTest
{
    @Mock
    private EcForEachInBothReconcilerServiceImpl ecForEachInBothReconcilerServiceImpl;
    @Mock
    private EcReconcilerServiceImpl ecReconcilerServiceImpl;
    @Mock
    private EcZipReconcilerServiceImpl ecZipReconcilerServiceImpl;

    @InjectMocks
    private EcReconcilerController testObj;

    @Test
    public void ecForEachInBothReconcilerServiceImpl() throws InterruptedException
    {
        Mockito.when(this.ecForEachInBothReconcilerServiceImpl.reconcile(new ReconcilerRequest()))
                .thenReturn("reconciled");

        ResponseEntity<String> response = this.testObj.ecForEachInBothReconcilerServiceImpl(new ReconcilerRequest());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("reconciled", response.getBody());
    }

    @Test
    public void ecZipReconcilerServiceImpl() throws InterruptedException
    {
        Mockito.when(this.ecZipReconcilerServiceImpl.reconcile(new ReconcilerRequest()))
                .thenReturn("reconciled");

        ResponseEntity<String> response = this.testObj.ecZipReconcilerServiceImpl(new ReconcilerRequest());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("reconciled", response.getBody());
    }

    @Test
    public void ecReconcilerServiceImpl() throws InterruptedException
    {
        Mockito.when(this.ecReconcilerServiceImpl.reconcile(new ReconcilerRequest()))
                .thenReturn("reconciled");

        ResponseEntity<String> response = this.testObj.ecReconcilerServiceImpl(new ReconcilerRequest());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("reconciled", response.getBody());
    }
}
