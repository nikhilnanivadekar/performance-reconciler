package nikhil.nani.reconciler.jdk.service.impl;

import nikhil.nani.data.bean.ReconcilerRequest;
import nikhil.nani.data.service.ReconcilerService;
import org.springframework.stereotype.Service;

@Service
public class JdkReconcilerServiceImpl implements ReconcilerService
{
    @Override
    public String reconcile(ReconcilerRequest request)
    {
        return "reconciled using JDK";
    }
}
