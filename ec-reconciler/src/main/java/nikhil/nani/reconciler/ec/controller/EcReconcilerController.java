package nikhil.nani.reconciler.ec.controller;

import nikhil.nani.data.bean.ReconcilerRequest;
import nikhil.nani.data.service.ReconcilerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ec-reconcile")
public class EcReconcilerController
{
    private ReconcilerService reconcilerService;

    public EcReconcilerController(ReconcilerService reconcilerService)
    {
        this.reconcilerService = reconcilerService;
    }

    @PostMapping("/predefined-objects")
    public ResponseEntity<String> reconcile(@RequestBody ReconcilerRequest request)
    {
        return ResponseEntity.ok(this.reconcilerService.reconcile(request));
    }
}
