package nikhil.nani.reconciler.ec.controller;

import nikhil.nani.data.bean.ReconcilerRequest;
import nikhil.nani.reconciler.ec.service.impl.EcForEachInBothReconcilerServiceImpl;
import nikhil.nani.reconciler.ec.service.impl.EcReconcilerServiceImpl;
import nikhil.nani.reconciler.ec.service.impl.EcZipReconcilerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ec-reconcile")
public class EcReconcilerController
{
    private final EcForEachInBothReconcilerServiceImpl ecForEachInBothReconcilerServiceImpl;
    private final EcReconcilerServiceImpl ecReconcilerServiceImpl;
    private final EcZipReconcilerServiceImpl ecZipReconcilerServiceImpl;

    @Autowired
    public EcReconcilerController(
            EcForEachInBothReconcilerServiceImpl ecForEachInBothReconcilerServiceImpl,
            EcReconcilerServiceImpl ecReconcilerServiceImpl,
            EcZipReconcilerServiceImpl ecZipReconcilerServiceImpl)
    {
        this.ecForEachInBothReconcilerServiceImpl = ecForEachInBothReconcilerServiceImpl;
        this.ecReconcilerServiceImpl = ecReconcilerServiceImpl;
        this.ecZipReconcilerServiceImpl = ecZipReconcilerServiceImpl;
    }

    @PostMapping("/ecForEachInBothReconcilerServiceImpl")
    public ResponseEntity<String> ecForEachInBothReconcilerServiceImpl(@RequestBody ReconcilerRequest request)
    {
        return ResponseEntity.ok(this.ecForEachInBothReconcilerServiceImpl.reconcile(request));
    }

    @PostMapping("/ecReconcilerServiceImpl")
    public ResponseEntity<String> ecReconcilerServiceImpl(@RequestBody ReconcilerRequest request)
    {
        return ResponseEntity.ok(this.ecReconcilerServiceImpl.reconcile(request));
    }

    @PostMapping("/ecZipReconcilerServiceImpl")
    public ResponseEntity<String> ecZipReconcilerServiceImpl(@RequestBody ReconcilerRequest request)
    {
        return ResponseEntity.ok(this.ecZipReconcilerServiceImpl.reconcile(request));
    }
}
