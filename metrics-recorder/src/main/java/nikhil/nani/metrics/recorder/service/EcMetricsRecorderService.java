package nikhil.nani.metrics.recorder.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EcMetricsRecorderService implements MetricsRecorderService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(EcMetricsRecorderService.class);

    @Override
    public Logger getLogger()
    {
        return LOGGER;
    }

    @Override
    public String getBaseUrl()
    {
        return ecReconcilerBaseUrl;
    }
}
