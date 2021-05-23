package nikhil.nani.reconciler.jdk.controller;

import nikhil.nani.data.bean.ReconcilerRequest;
import nikhil.nani.reconciler.jdk.service.JdkReconcilerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reconcile")
public class JdkReconcilerController
{
    private JdkReconcilerService reconcilerService;

    public JdkReconcilerController(JdkReconcilerService reconcilerService)
    {
        this.reconcilerService = reconcilerService;
    }

    @PostMapping("/predefined-objects")
    public ResponseEntity<String> reconcile(@RequestBody ReconcilerRequest request)
    {
        return ResponseEntity.ok(this.reconcilerService.reconcile(request));
    }
}
