package nikhil.nani.data.creator.controller;

import nikhil.nani.data.creator.bean.CreatorRequest;
import nikhil.nani.data.creator.service.CreatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-data")
public class DataCreatorController
{
    private CreatorService creatorService;

    public DataCreatorController(CreatorService creatorService)
    {
        this.creatorService = creatorService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CreatorRequest request)
    {
        return ResponseEntity.ok(this.creatorService.create(request));
    }
}
