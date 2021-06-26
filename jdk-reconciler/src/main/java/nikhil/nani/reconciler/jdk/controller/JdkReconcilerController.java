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
    public ResponseEntity<String> reconcile(@RequestBody ReconcilerRequest request) throws InterruptedException
    {
        String reconcile = null;
        for (int i = 0; i < request.getCount(); i++)
        {
            long startTime = System.currentTimeMillis();
            LOGGER.info("Start time:{} for reconcilerService | Iteration:{}", startTime, i);
            reconcile = this.reconcilerService.reconcile(request);
            System.gc();
            System.gc();
            System.gc();
            long endTime = System.currentTimeMillis();
            LOGGER.info("End time:{} for reconcilerService | Iteration:{}", endTime, i);
            LOGGER.info("Total time:{} | Reconciler Request:{} | Iteration:{}", endTime - startTime, request, i);

            Thread.sleep(1000);
        }

        return ResponseEntity.ok(reconcile);
    }
}
