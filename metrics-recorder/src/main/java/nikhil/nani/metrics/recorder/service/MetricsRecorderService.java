package nikhil.nani.metrics.recorder.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
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

    Path getJvmBufferCountFilePath();

    Path getJvmBufferMemoryUsedFilePath();

    Path getJvmBufferTotalCapacityFilePath();

    Path getJvmGcLiveDataSizeFilePath();

    Path getJvmGcMaxDataSizeFilePath();

    Path getJvmGcMemoryAllocatedFilePath();

    Path getJvmGcMemoryPromotedFilePath();

    Path getJvmGcPauseFilePath();

    Path getJvmMemoryCommittedFilePath();

    Path getJvmMemoryMaxFilePath();

    Path getJvmMemoryUsedFilePath();

    Path getCpuUsageFilePath();

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
    default void recordJvmBufferCountUrl() throws Exception
    {
        this.getJvmBufferCountCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmBufferCountUrl(), Map.class).getBody();
        String message = String.format("Time:%d | %s | %s", System.currentTimeMillis(), this.getJvmBufferCountCounter(), response);
        this.getLogger().info(message);
        Files.write(this.getJvmBufferCountFilePath(), (message + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    @Scheduled(fixedDelay = 1000)
    default void recordJvmBufferMemoryUsedUrl() throws Exception
    {
        this.getJvmBufferMemoryUsedCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmBufferMemoryUsedUrl(), Map.class).getBody();
        String message = String.format("Time:%d | %s | %s", System.currentTimeMillis(), this.getJvmBufferMemoryUsedCounter(), response);
        this.getLogger().info(message);
        Files.write(this.getJvmBufferMemoryUsedFilePath(), (message + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    @Scheduled(fixedDelay = 1000)
    default void recordJvmBufferTotalCapacityUrl() throws Exception
    {
        this.getJvmBufferTotalCapacityCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmBufferTotalCapacityUrl(), Map.class).getBody();
        String message = String.format("Time:%d | %s | %s", System.currentTimeMillis(), this.getJvmBufferTotalCapacityCounter(), response);
        this.getLogger().info(message);
        Files.write(this.getJvmBufferTotalCapacityFilePath(), (message + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    @Scheduled(fixedDelay = 1000)
    default void recordJvmGcLiveDataSizeUrl() throws Exception
    {
        this.getJvmGcLiveDataSizeCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmGcLiveDataSizeUrl(), Map.class).getBody();
        String message = String.format("Time:%d | %s | %s", System.currentTimeMillis(), this.getJvmGcLiveDataSizeCounter(), response);
        this.getLogger().info(message);
        Files.write(this.getJvmGcLiveDataSizeFilePath(), (message + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    @Scheduled(fixedDelay = 1000)
    default void recordJvmGcMaxDataSizeUrl() throws Exception
    {
        this.getJvmGcMaxDataSizeCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmGcMaxDataSizeUrl(), Map.class).getBody();
        String message = String.format("Time:%d | %s | %s", System.currentTimeMillis(), this.getJvmGcMaxDataSizeCounter(), response);
        this.getLogger().info(message);
        Files.write(this.getJvmGcMaxDataSizeFilePath(), (message + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    @Scheduled(fixedDelay = 1000)
    default void recordJvmGcMemoryAllocatedUrl() throws Exception
    {
        this.getJvmGcMemoryAllocatedCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmGcMemoryAllocatedUrl(), Map.class).getBody();
        String message = String.format("Time:%d | %s | %s", System.currentTimeMillis(), this.getJvmGcMemoryAllocatedCounter(), response);
        this.getLogger().info(message);
        Files.write(this.getJvmGcMemoryAllocatedFilePath(), (message + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    @Scheduled(fixedDelay = 1000)
    default void recordJvmGcMemoryPromotedUrl() throws Exception
    {
        this.getJvmGcMemoryPromotedCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmGcMemoryPromotedUrl(), Map.class).getBody();
        String message = String.format("Time:%d | %s | %s", System.currentTimeMillis(), this.getJvmGcMemoryPromotedCounter(), response);
        this.getLogger().info(message);
        Files.write(this.getJvmGcMemoryPromotedFilePath(), (message + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    @Scheduled(fixedDelay = 1000)
    default void recordJvmGcPauseUrl() throws Exception
    {
        this.getJvmGcPauseCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmGcPauseUrl(), Map.class).getBody();
        String message = String.format("Time:%d | %s | %s", System.currentTimeMillis(), this.getJvmGcPauseCounter(), response);
        this.getLogger().info(message);
        Files.write(this.getJvmGcPauseFilePath(), (message + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    @Scheduled(fixedDelay = 1000)
    default void recordJvmMemoryCommittedUrl() throws Exception
    {
        this.getJvmMemoryCommittedCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmMemoryCommittedUrl(), Map.class).getBody();
        String message = String.format("Time:%d | %s | %s", System.currentTimeMillis(), this.getJvmMemoryCommittedCounter(), response);
        this.getLogger().info(message);
        Files.write(this.getJvmMemoryCommittedFilePath(), (message + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    @Scheduled(fixedDelay = 1000)
    default void recordJvmMemoryMaxUrl() throws Exception
    {
        this.getJvmMemoryMaxCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmMemoryMaxUrl(), Map.class).getBody();
        String message = String.format("Time:%d | %s | %s", System.currentTimeMillis(), this.getJvmMemoryMaxCounter(), response);
        this.getLogger().info(message);
        Files.write(this.getJvmMemoryMaxFilePath(), (message + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    @Scheduled(fixedDelay = 1000)
    default void recordJvmMemoryUsedUrl() throws Exception
    {
        this.getJvmMemoryUsedCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getJvmMemoryUsedUrl(), Map.class).getBody();
        String message = String.format("Time:%d | %s | %s", System.currentTimeMillis(), this.getJvmMemoryUsedCounter(), response);
        this.getLogger().info(message);
        Files.write(this.getJvmMemoryUsedFilePath(), (message + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    @Scheduled(fixedDelay = 1000)
    default void recordProcessCpuUsageUrl() throws Exception
    {
        this.getCpuUsageCounter().increment();
        Map<String, Object> response = this.getRestTemplate()
                .getForEntity(this.getProcessCpuUsageUrl(), Map.class).getBody();
        String message = String.format("Time:%d | %s | %s", System.currentTimeMillis(), this.getCpuUsageCounter(), response);
        this.getLogger().info(message);
        Files.write(this.getCpuUsageFilePath(), (message + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
