package nikhil.nani.reconciler.jdk.service.impl;

import nikhil.nani.data.bean.ReconcilerRequest;
import nikhil.nani.reconciler.jdk.service.JdkReconcilerService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JdkReconcilerServiceImplTest
{
    private JdkReconcilerService testObj = new JdkReconcilerServiceImpl();

    @Test
    public void reconcile()
    {
        assertEquals("reconciled", this.testObj.reconcile(new ReconcilerRequest()));
    }
}
