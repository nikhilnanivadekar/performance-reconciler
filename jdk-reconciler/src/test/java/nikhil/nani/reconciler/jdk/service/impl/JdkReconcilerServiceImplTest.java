package nikhil.nani.reconciler.jdk.service.impl;

import nikhil.nani.data.bean.ReconcilerRequest;
import nikhil.nani.data.service.ReconcilerService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JdkReconcilerServiceImplTest
{
    private ReconcilerService testObj = new JdkReconcilerServiceImpl();

    @Test
    public void reconcile()
    {
        assertEquals("reconciled using JDK", this.testObj.reconcile(new ReconcilerRequest()));
    }
}
