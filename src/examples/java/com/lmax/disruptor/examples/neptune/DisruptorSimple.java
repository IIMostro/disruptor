package com.lmax.disruptor.examples.neptune;

import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.TimeUnit;

/**
 * @author li.bowei
 */
public class DisruptorSimple {

    public static void main(String[] args) throws Exception
    {
        Disruptor<OrderEvent> disruptor = DisruptorConfiguration.getInstance();
        disruptor.handleEventsWith(new MessageEventHandler());
        disruptor.start();
        for (int i = 0; i < 10; i++)
        {
            final int fi = i;
            disruptor.publishEvent(((event, sequence) -> event.setId(fi)));
        }

        TimeUnit.SECONDS.sleep(1);
    }
}
