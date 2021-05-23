package nikhil.nani.reconciler.jdk.service;

import nikhil.nani.data.bean.ReconcilerRequest;

public interface JdkReconcilerService
{
    String reconcile(ReconcilerRequest request);
}
