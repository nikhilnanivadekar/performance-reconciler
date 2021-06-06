package nikhil.nani.reconciler.jdk.controller;

import nikhil.nani.data.bean.ReconcilerRequest;
import nikhil.nani.data.service.ReconcilerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jdk-reconcile")
public class JdkReconcilerController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(JdkReconcilerController.class);

    private ReconcilerService reconcilerService;

    public JdkReconcilerController(ReconcilerService reconcilerService)
    {
        this.reconcilerService = reconcilerService;
    }

    @PostMapping("/predefined-objects")
    public ResponseEntity<String> reconcile(@RequestBody ReconcilerRequest request)
    {
        long startTime = System.currentTimeMillis();
        LOGGER.info("Start time:{} for jdkReconcilerService", startTime);
        String reconcile = this.reconcilerService.reconcile(request);
        long endTime = System.currentTimeMillis();
        LOGGER.info("End time:{} for jdkReconcilerService", endTime);
        LOGGER.info("Total time:{} | Reconciler Request:{}", endTime - startTime, request);

        return ResponseEntity.ok(reconcile);
    }
}
