package nikhil.nani.metrics.recorder.service;

import java.util.Map;

import nikhil.nani.data.bean.Counter;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

public interface MetricsRecorderService
{
    String ecReconcilerBaseUrl = "http://localhost:9060";
    String jdkReconcilerBaseUrl = "http://localhost:8040";

    Counter getJvmBufferCountCounter();

    Counter getJvmBufferMemoryUsedCounter();

    Counter getJvmBufferTotalCapacityCounter();

    Counter getJvmGcLiveDataSizeCounter();

    Counter getJvmGcMaxDataSizeCounter();

    Counter getJvmGcMemoryAllocatedCounter();

    Counter getJvmGcMemoryPromotedCounter();

    Counter getJvmGcPauseCounter();

    Counter getJvmMemoryCommittedCounter();

    Counter getJvmMemoryMaxCounter();

    Counter getJvmMemoryUsedCounter();

    Counter getCpuUsageCounter();

    Logger getLogger();

    default String getJvmBufferCountUrl()
    {
        return String.format("%s/actuator/metrics/jvm.buffer.count", this.getBaseUrl());
    }

    default String getJvmBufferMemoryUsedUrl()
    {
        return String.format("%s/actuator/metrics/jvm.buffer.memory.used", this.getBaseUrl());
    }

    default String getJvmBufferTotalCapacityUrl()
    {
        return String.format("%s/actuator/metrics/jvm.buffer.total.capacity", this.getBaseUrl());
    }

    default String getJvmGcLiveDataSizeUrl()
    {
        return String.format("%s/actuator/metrics/jvm.gc.live.data.size", this.getBaseUrl());
    }

    default String getJvmGcMaxDataSizeUrl()
    {
        return String.format("%s/actuator/metrics/jvm.gc.max.data.size", this.getBaseUrl());
    }

    default String getJvmGcMemoryAllocatedUrl()
    {
        return String.format("%s/actuator/metrics/jvm.gc.memory.allocated", this.getBaseUrl());
    }

    default String getJvmGcMemoryPromotedUrl()
    {
        return String.format("%s/actuator/metrics/jvm.gc.memory.promoted", this.getBaseUrl());
    }

    default String getJvmGcPauseUrl()
    {
        return String.format("%s/actuator/metrics/jvm.gc.pause", this.getBaseUrl());
    }

    default String getJvmMemoryCommittedUrl()
    {
        return String.format("%s/actuator/metrics/jvm.memory.committed", this.getBaseUrl());
    }

    default String getJvmMemoryMaxUrl()
    {
        return String.format("%s/actuator/metrics/jvm.memory.max", this.getBaseUrl());
    }

    default String getJvmMemoryUsedUrl()
    {
        return String.format("%s/actuator/metrics/jvm.memory.used", this.getBaseUrl());
    }

    default String getProcessCpuUsageUrl()
    {
        return String.format("%s/actuator/metrics/process.cpu.usage", this.getBaseUrl());
    }

    String getBaseUrl();

    default RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }

    @Scheduled(fixedDelay = 1000)
    default void recordJvmBufferCountUrl()
    {
        this.getJvmBufferCountCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmBufferCountUrl(), Map.class).getBody();
        this.getLogger().info("{} | {}", this.getJvmBufferCountCounter(), response);
    }

    @Scheduled(fixedDelay = 1000)
    default void recordJvmBufferMemoryUsedUrl()
    {
        this.getJvmBufferMemoryUsedCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmBufferMemoryUsedUrl(), Map.class).getBody();
        this.getLogger().info("{} | {}", this.getJvmBufferMemoryUsedCounter(), response);
    }

    @Scheduled(fixedDelay = 1000)
    default void recordJvmBufferTotalCapacityUrl()
    {
        this.getJvmBufferTotalCapacityCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmBufferTotalCapacityUrl(), Map.class).getBody();
        this.getLogger().info("{} | {}", this.getJvmBufferTotalCapacityCounter(), response);
    }

    @Scheduled(fixedDelay = 1000)
    default void recordJvmGcLiveDataSizeUrl()
    {
        this.getJvmGcLiveDataSizeCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmGcLiveDataSizeUrl(), Map.class).getBody();
        this.getLogger().info("{} | {}", this.getJvmGcLiveDataSizeCounter(), response);
    }

    @Scheduled(fixedDelay = 1000)
    default void recordJvmGcMaxDataSizeUrl()
    {
        this.getJvmGcMaxDataSizeCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmGcMaxDataSizeUrl(), Map.class).getBody();
        this.getLogger().info("{} | {}", this.getJvmGcMaxDataSizeCounter(), response);
    }

    @Scheduled(fixedDelay = 1000)
    default void recordJvmGcMemoryAllocatedUrl()
    {
        this.getJvmGcMemoryAllocatedCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmGcMemoryAllocatedUrl(), Map.class).getBody();
        this.getLogger().info("{} | {}", this.getJvmGcMemoryAllocatedCounter(), response);
    }

    @Scheduled(fixedDelay = 1000)
    default void recordJvmGcMemoryPromotedUrl()
    {
        this.getJvmGcMemoryPromotedCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmGcMemoryPromotedUrl(), Map.class).getBody();
        this.getLogger().info("{} | {}", this.getJvmGcMemoryPromotedCounter(), response);
    }

    @Scheduled(fixedDelay = 1000)
    default void recordJvmGcPauseUrl()
    {
        this.getJvmGcPauseCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmGcPauseUrl(), Map.class).getBody();
        this.getLogger().info("{} | {}", this.getJvmGcPauseCounter(), response);
    }

    @Scheduled(fixedDelay = 1000)
    default void recordJvmMemoryCommittedUrl()
    {
        this.getJvmMemoryCommittedCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmMemoryCommittedUrl(), Map.class).getBody();
        this.getLogger().info("{} | {}", this.getJvmMemoryCommittedCounter(), response);
    }

    @Scheduled(fixedDelay = 1000)
    default void recordJvmMemoryMaxUrl()
    {
        this.getJvmMemoryMaxCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmMemoryMaxUrl(), Map.class).getBody();
        this.getLogger().info("{} | {}", this.getJvmMemoryMaxCounter(), response);
    }

    @Scheduled(fixedDelay = 1000)
    default void recordJvmMemoryUsedUrl()
    {
        this.getJvmMemoryUsedCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmMemoryUsedUrl(), Map.class).getBody();
        this.getLogger().info("{} | {}", this.getJvmMemoryUsedCounter(), response);
    }

    @Scheduled(fixedDelay = 1000)
    default void recordProcessCpuUsageUrl()
    {
        this.getCpuUsageCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getProcessCpuUsageUrl(), Map.class).getBody();
        this.getLogger().info("{} | {}", this.getCpuUsageCounter(), response);
    }
}
