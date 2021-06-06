package nikhil.nani.metrics.recorder.service;

import nikhil.nani.data.bean.Counter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JdkMetricsRecorderService implements MetricsRecorderService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(JdkMetricsRecorderService.class);

    private final Counter jvmBufferCountCounter;
    private final Counter jvmBufferMemoryUsedCounter;
    private final Counter jvmBufferTotalCapacityCounter;
    private final Counter jvmGcLiveDataSizeCounter;
    private final Counter jvmGcMaxDataSizeCounter;
    private final Counter jvmGcMemoryAllocatedCounter;
    private final Counter jvmGcMemoryPromotedCounter;
    private final Counter jvmGcPauseCounter;
    private final Counter jvmMemoryCommittedCounter;
    private final Counter jvmMemoryMaxCounter;
    private final Counter jvmMemoryUsedCounter;
    private final Counter cpuUsageCounter;

    public JdkMetricsRecorderService()
    {
        this.jvmBufferCountCounter = new Counter(0);
        this.jvmBufferMemoryUsedCounter = new Counter(0);
        this.jvmBufferTotalCapacityCounter = new Counter(0);
        this.jvmGcLiveDataSizeCounter = new Counter(0);
        this.jvmGcMaxDataSizeCounter = new Counter(0);
        this.jvmGcMemoryAllocatedCounter = new Counter(0);
        this.jvmGcMemoryPromotedCounter = new Counter(0);
        this.jvmGcPauseCounter = new Counter(0);
        this.jvmMemoryCommittedCounter = new Counter(0);
        this.jvmMemoryMaxCounter = new Counter(0);
        this.jvmMemoryUsedCounter = new Counter(0);
        this.cpuUsageCounter = new Counter(0);
    }

    @Override
    public Counter getJvmBufferCountCounter()
    {
        return this.jvmBufferCountCounter;
    }

    @Override
    public Counter getJvmBufferMemoryUsedCounter()
    {
        return this.jvmBufferMemoryUsedCounter;
    }

    @Override
    public Counter getJvmBufferTotalCapacityCounter()
    {
        return this.jvmBufferTotalCapacityCounter;
    }

    @Override
    public Counter getJvmGcLiveDataSizeCounter()
    {
        return this.jvmGcLiveDataSizeCounter;
    }

    @Override
    public Counter getJvmGcMaxDataSizeCounter()
    {
        return this.jvmGcMaxDataSizeCounter;
    }

    @Override
    public Counter getJvmGcMemoryAllocatedCounter()
    {
        return this.jvmGcMemoryAllocatedCounter;
    }

    @Override
    public Counter getJvmGcMemoryPromotedCounter()
    {
        return this.jvmGcMemoryPromotedCounter;
    }

    @Override
    public Counter getJvmGcPauseCounter()
    {
        return this.jvmGcPauseCounter;
    }

    @Override
    public Counter getJvmMemoryCommittedCounter()
    {
        return this.jvmMemoryCommittedCounter;
    }

    @Override
    public Counter getJvmMemoryMaxCounter()
    {
        return this.jvmMemoryMaxCounter;
    }

    @Override
    public Counter getJvmMemoryUsedCounter()
    {
        return this.jvmMemoryUsedCounter;
    }

    @Override
    public Counter getCpuUsageCounter()
    {
        return this.cpuUsageCounter;
    }

    @Override
    public Logger getLogger()
    {
        return LOGGER;
    }

    @Override
    public String getBaseUrl()
    {
        return jdkReconcilerBaseUrl;
    }
}
