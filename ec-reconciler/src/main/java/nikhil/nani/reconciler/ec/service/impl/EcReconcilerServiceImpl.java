package nikhil.nani.reconciler.ec.service.impl;

import nikhil.nani.data.bean.ReconcilerRequest;
import nikhil.nani.data.service.ReconcilerService;
import org.springframework.stereotype.Service;

@Service
public class EcReconcilerServiceImpl implements ReconcilerService
{
    @Override
    public String reconcile(ReconcilerRequest request)
    {
        return "reconciled using EC";
    }
}
