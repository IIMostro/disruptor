package com.lmax.disruptor.examples.neptune;

import com.lmax.disruptor.EventHandler;

/**
 * @author li.bowei
 */
public class MessageEventHandler implements EventHandler<OrderEvent>
{
    @Override
    public void onEvent(final OrderEvent event, final long sequence, final boolean endOfBatch) throws Exception
    {
        System.out.println("thread: "+ Thread.currentThread().getName() + ", event: " + event.toString());
    }
}
