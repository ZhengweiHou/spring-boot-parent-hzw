package com.hzw.learn.springboot.batchbase.partition;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.ResourceAwareItemWriterItemStream;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Map;

public class HzwPartitionResourceItemReader extends AbstractItemCountingItemStreamItemReader implements ResourceAwareItemWriterItemStream, Partitioner {
    @Override // by AbstractItemCountingItemStreamItemReader invoke by read override by ItemReader
    protected Object doRead() throws Exception {
        return null;
    }

    @Override // by AbstractItemCountingItemStreamItemReader
    protected void doOpen() throws Exception {

    }

    @Override // by AbstractItemCountingItemStreamItemReader
    protected void doClose() throws Exception {

    }

    @Override   // by ResourceAwareItemWriterItemStream
    public void setResource(Resource resource) {

    }

    @Override   // by ResourceAwareItemWriterItemStream implement ItemWriter
    public void write(List list) throws Exception {

    }

    @Override   // by Partitioner
    public Map<String, ExecutionContext> partition(int gridSize) {
        return null;
    }
}
