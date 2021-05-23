package nikhil.nani.reconciler.ec.service.impl;

import nikhil.nani.data.bean.ReconcilerRequest;
import nikhil.nani.data.service.ReconcilerService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EcReconcilerServiceImplTest
{
    private ReconcilerService testObj = new EcReconcilerServiceImpl();

    @Test
    public void reconcile()
    {
        assertEquals("reconciled using EC", this.testObj.reconcile(new ReconcilerRequest()));
    }
}
