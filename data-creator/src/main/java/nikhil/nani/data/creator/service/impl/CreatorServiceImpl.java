package nikhil.nani.data.creator.service.impl;

import nikhil.nani.data.creator.bean.CreatorRequest;
import nikhil.nani.data.creator.service.CreatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreatorServiceImpl implements CreatorService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CreatorServiceImpl.class);

    public String create(CreatorRequest request)
    {
        LOGGER.info("Creating Data");

        return "created";
    }
}
