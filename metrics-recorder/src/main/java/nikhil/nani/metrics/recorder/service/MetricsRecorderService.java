package nikhil.nani.metrics.recorder.service;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

public interface MetricsRecorderService
{
    String ecReconcilerBaseUrl = "http://localhost:9060";
    String jdkReconcilerBaseUrl = "http://localhost:8040";

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

    @Scheduled(fixedDelay = 2 * 1000)
    default void recordJvmBufferCountUrl()
    {
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmBufferCountUrl(), Map.class).getBody();
        this.getLogger().info(response.toString());
    }

    @Scheduled(fixedDelay = 2 * 1000)
    default void recordJvmBufferMemoryUsedUrl()
    {
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmBufferMemoryUsedUrl(), Map.class).getBody();
        this.getLogger().info(response.toString());
    }

    @Scheduled(fixedDelay = 2 * 1000)
    default void recordJvmBufferTotalCapacityUrl()
    {
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmBufferTotalCapacityUrl(), Map.class).getBody();
        this.getLogger().info(response.toString());
    }

    @Scheduled(fixedDelay = 2 * 1000)
    default void recordJvmGcLiveDataSizeUrl()
    {
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmGcLiveDataSizeUrl(), Map.class).getBody();
        this.getLogger().info(response.toString());
    }

    @Scheduled(fixedDelay = 2 * 1000)
    default void recordJvmGcMaxDataSizeUrl()
    {
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmGcMaxDataSizeUrl(), Map.class).getBody();
        this.getLogger().info(response.toString());
    }

    @Scheduled(fixedDelay = 2 * 1000)
    default void recordJvmGcMemoryAllocatedUrl()
    {
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmGcMemoryAllocatedUrl(), Map.class).getBody();
        this.getLogger().info(response.toString());
    }

    @Scheduled(fixedDelay = 2 * 1000)
    default void recordJvmGcMemoryPromotedUrl()
    {
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmGcMemoryPromotedUrl(), Map.class).getBody();
        this.getLogger().info(response.toString());
    }

    @Scheduled(fixedDelay = 2 * 1000)
    default void recordJvmGcPauseUrl()
    {
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmGcPauseUrl(), Map.class).getBody();
        this.getLogger().info(response.toString());
    }

    @Scheduled(fixedDelay = 2 * 1000)
    default void recordJvmMemoryCommittedUrl()
    {
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmMemoryCommittedUrl(), Map.class).getBody();
        this.getLogger().info(response.toString());
    }

    @Scheduled(fixedDelay = 2 * 1000)
    default void recordJvmMemoryMaxUrl()
    {
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmMemoryMaxUrl(), Map.class).getBody();
        this.getLogger().info(response.toString());
    }

    @Scheduled(fixedDelay = 2 * 1000)
    default void recordJvmMemoryUsedUrl()
    {
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmMemoryUsedUrl(), Map.class).getBody();
        this.getLogger().info(response.toString());
    }

    @Scheduled(fixedDelay = 2 * 1000)
    default void recordProcessCpuUsageUrl()
    {
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getProcessCpuUsageUrl(), Map.class).getBody();
        this.getLogger().info(response.toString());
    }
}
