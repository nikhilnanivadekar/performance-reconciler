package nikhil.nani.data.creator.controller;

import nikhil.nani.data.creator.bean.CreatorRequest;
import nikhil.nani.data.creator.service.CreatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DataCreatorControllerTest
{
    @Mock
    private CreatorService service;

    @InjectMocks
    private DataCreatorController testObj;

    @Test
    public void create()
    {
        Mockito.when(this.service.create(new CreatorRequest())).thenReturn("Created");

        ResponseEntity<String> response = this.testObj.create(new CreatorRequest());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Created", response.getBody());
    }
}
