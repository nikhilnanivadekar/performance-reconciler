package nikhil.nani.reconciler.jdk.controller;

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
public class JdkReconcilerControllerTest
{
    @Mock
    private ReconcilerService service;

    @InjectMocks
    private JdkReconcilerController testObj;

    @Test
    public void reconcile() throws InterruptedException
    {
        Mockito.when(this.service.reconcile(new ReconcilerRequest())).thenReturn("reconciled");

        ResponseEntity<String> response = this.testObj.reconcile(new ReconcilerRequest());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("reconciled", response.getBody());
    }
}
