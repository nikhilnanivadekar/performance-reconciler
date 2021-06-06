package nikhil.nani.reconciler.ec.controller;

import nikhil.nani.data.bean.ReconcilerRequest;
import nikhil.nani.reconciler.ec.service.impl.EcForEachInBothReconcilerServiceImpl;
import nikhil.nani.reconciler.ec.service.impl.EcReconcilerServiceImpl;
import nikhil.nani.reconciler.ec.service.impl.EcZipReconcilerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(EcReconcilerController.class);

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
        long startTime = System.currentTimeMillis();
        LOGGER.info("Start time:{} for ecForEachInBothReconcilerServiceImpl", startTime);
        String reconcile = this.ecForEachInBothReconcilerServiceImpl.reconcile(request);
        long endTime = System.currentTimeMillis();
        LOGGER.info("End time:{} for ecForEachInBothReconcilerServiceImpl", endTime);
        LOGGER.info("Total time:{} | Reconciler Request:{}", endTime - startTime, request);

        return ResponseEntity.ok(reconcile);
    }

    @PostMapping("/ecReconcilerServiceImpl")
    public ResponseEntity<String> ecReconcilerServiceImpl(@RequestBody ReconcilerRequest request)
    {
        long startTime = System.currentTimeMillis();
        LOGGER.info("Start time:{} for ecReconcilerServiceImpl", startTime);
        String reconcile = this.ecReconcilerServiceImpl.reconcile(request);
        long endTime = System.currentTimeMillis();
        LOGGER.info("End time:{} for ecReconcilerServiceImpl", endTime);
        LOGGER.info("Total time:{} | Reconciler Request:{}", endTime - startTime, request);

        return ResponseEntity.ok(reconcile);
    }

    @PostMapping("/ecZipReconcilerServiceImpl")
    public ResponseEntity<String> ecZipReconcilerServiceImpl(@RequestBody ReconcilerRequest request)
    {
        long startTime = System.currentTimeMillis();
        LOGGER.info("Start time:{} for ecZipReconcilerServiceImpl", startTime);
        String reconcile = this.ecZipReconcilerServiceImpl.reconcile(request);
        long endTime = System.currentTimeMillis();
        LOGGER.info("End time:{} for ecZipReconcilerServiceImpl", endTime);
        LOGGER.info("Total time:{} | Reconciler Request:{}", endTime - startTime, request);

        return ResponseEntity.ok(reconcile);
    }
}
