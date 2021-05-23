package nikhil.nani.reconciler.jdk.service.impl;

import nikhil.nani.data.bean.ReconcilerRequest;
import nikhil.nani.reconciler.jdk.service.JdkReconcilerService;
import org.springframework.stereotype.Service;

@Service
public class JdkReconcilerServiceImpl implements JdkReconcilerService
{
    @Override
    public String reconcile(ReconcilerRequest request)
    {
        return "reconciled";
    }
}
