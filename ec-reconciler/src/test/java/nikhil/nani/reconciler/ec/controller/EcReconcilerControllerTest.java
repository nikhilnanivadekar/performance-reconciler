package nikhil.nani.reconciler.ec.controller;

import nikhil.nani.data.bean.ReconcilerRequest;
import nikhil.nani.data.service.ReconcilerService;
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
    private ReconcilerService service;

    @InjectMocks
    private EcReconcilerController testObj;

    @Test
    public void reconcile()
    {
        Mockito.when(this.service.reconcile(new ReconcilerRequest())).thenReturn("reconciled");

        ResponseEntity<String> response = this.testObj.reconcile(new ReconcilerRequest());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("reconciled", response.getBody());
    }
}
