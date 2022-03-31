package com.lmax.disruptor.examples.neptune;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.EventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.Sequence;
import com.lmax.disruptor.dsl.EventProcessorFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author li.bowei
 */
public class MultiMessageEventProcessorFactory implements EventProcessorFactory<OrderEvent>
{

    private static final Map<Integer, EventProcessor> map = new ConcurrentHashMap<>();

    @Override
    public EventProcessor createEventProcessor(final RingBuffer<OrderEvent> ringBuffer, final Sequence[] barrierSequences)
    {
        int key = (int) (ringBuffer.getCursor() % 10);
        if(map.containsKey(key)) return map.get(key);
        BatchEventProcessor<OrderEvent> processor = new BatchEventProcessor<>(ringBuffer, ringBuffer.newBarrier(barrierSequences), new MessageEventHandler());
        map.put(key, processor);
        return processor;
    }
}
