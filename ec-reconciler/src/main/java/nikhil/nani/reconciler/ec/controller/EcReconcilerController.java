package nikhil.nani.reconciler.ec.controller;

import nikhil.nani.data.bean.ReconcilerRequest;
import nikhil.nani.reconciler.ec.service.impl.EcForEachInBothReconcilerServiceImpl;
import nikhil.nani.reconciler.ec.service.impl.EcReconcilerServiceCustomHashingStrategyImpl;
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
    private final EcReconcilerServiceCustomHashingStrategyImpl ecReconcilerServiceCustomHashingStrategyImpl;

    @Autowired
    public EcReconcilerController(
            EcForEachInBothReconcilerServiceImpl ecForEachInBothReconcilerServiceImpl,
            EcReconcilerServiceImpl ecReconcilerServiceImpl,
            EcZipReconcilerServiceImpl ecZipReconcilerServiceImpl,
            EcReconcilerServiceCustomHashingStrategyImpl ecReconcilerServiceCustomHashingStrategyImpl)
    {
        this.ecForEachInBothReconcilerServiceImpl = ecForEachInBothReconcilerServiceImpl;
        this.ecReconcilerServiceImpl = ecReconcilerServiceImpl;
        this.ecZipReconcilerServiceImpl = ecZipReconcilerServiceImpl;
        this.ecReconcilerServiceCustomHashingStrategyImpl = ecReconcilerServiceCustomHashingStrategyImpl;
    }

    @PostMapping("/ecForEachInBothReconcilerServiceImpl")
    public ResponseEntity<String> ecForEachInBothReconcilerServiceImpl(@RequestBody ReconcilerRequest request) throws InterruptedException
    {
        String reconcile = null;
        for (int i = 0; i < request.getCount(); i++)
        {
            long startTime = System.currentTimeMillis();
            LOGGER.info("Start time:{} for ecForEachInBothReconcilerServiceImpl | Iteration:{}", startTime, i);
            reconcile = this.ecForEachInBothReconcilerServiceImpl.reconcile(request);
            long endTime = System.currentTimeMillis();
            LOGGER.info("End time:{} for ecForEachInBothReconcilerServiceImpl | Iteration:{}", endTime, i);
            LOGGER.info("Total time:{} | Reconciler Request:{} | Iteration:{}", endTime - startTime, request, i);
            Thread.sleep(1000);
        }

        return ResponseEntity.ok(reconcile);
    }

    @PostMapping("/ecReconcilerServiceImpl")
    public ResponseEntity<String> ecReconcilerServiceImpl(@RequestBody ReconcilerRequest request) throws InterruptedException
    {
        String reconcile = null;
        for (int i = 0; i < request.getCount(); i++)
        {
            long startTime = System.currentTimeMillis();
            LOGGER.info("Start time:{} for ecReconcilerServiceImpl | Iteration:{}", startTime, i);
            reconcile = this.ecReconcilerServiceImpl.reconcile(request);
            long endTime = System.currentTimeMillis();
            LOGGER.info("End time:{} for ecReconcilerServiceImpl | Iteration:{}", endTime, i);
            LOGGER.info("Total time:{} | Reconciler Request:{} | Iteration:{}", endTime - startTime, request, i);
            Thread.sleep(1000);
        }

        return ResponseEntity.ok(reconcile);
    }

    @PostMapping("/ecZipReconcilerServiceImpl")
    public ResponseEntity<String> ecZipReconcilerServiceImpl(@RequestBody ReconcilerRequest request) throws InterruptedException
    {
        String reconcile = null;
        for (int i = 0; i < request.getCount(); i++)
        {
            long startTime = System.currentTimeMillis();
            LOGGER.info("Start time:{} for ecZipReconcilerServiceImpl | Iteration:{}", startTime, i);
            reconcile = this.ecZipReconcilerServiceImpl.reconcile(request);
            long endTime = System.currentTimeMillis();
            LOGGER.info("End time:{} for ecZipReconcilerServiceImpl | Iteration:{}", endTime, i);
            LOGGER.info("Total time:{} | Reconciler Request:{} | Iteration:{}", endTime - startTime, request, i);
            Thread.sleep(1000);
        }

        return ResponseEntity.ok(reconcile);
    }

    @PostMapping("/ecReconcilerServiceCustomHashingStrategyImpl")
    public ResponseEntity<String> ecReconcilerServiceCustomHashingStrategyImpl(@RequestBody ReconcilerRequest request) throws InterruptedException
    {
        String reconcile = null;
        for (int i = 0; i < request.getCount(); i++)
        {
            System.gc();
            System.gc();
            System.gc();

            long startTime = System.currentTimeMillis();
            LOGGER.info("Start time:{} for ecZipReconcilerServiceImpl | Iteration:{}", startTime, i);
            reconcile = this.ecReconcilerServiceCustomHashingStrategyImpl.reconcile(request);

            System.gc();
            System.gc();
            System.gc();
            long endTime = System.currentTimeMillis();
            LOGGER.info("End time:{} for ecZipReconcilerServiceImpl | Iteration:{}", endTime, i);
            LOGGER.info("Total time:{} | Reconciler Request:{} | Iteration:{}", endTime - startTime, request, i);

            Thread.sleep(1000);
        }

        return ResponseEntity.ok(reconcile);
    }
}
