package nikhil.nani.data.creator.service.impl;

import nikhil.nani.data.creator.bean.CreatorRequest;
import nikhil.nani.data.creator.service.CreatorService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreatorServiceImplTest
{
    private CreatorService testObj = new CreatorServiceImpl();

    @Test
    public void create()
    {
        assertEquals("created", this.testObj.create(new CreatorRequest()));
    }
}
