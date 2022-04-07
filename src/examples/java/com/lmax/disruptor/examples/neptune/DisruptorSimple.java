package com.lmax.disruptor.examples.neptune;

import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;

/**
 * @author li.bowei
 */
public class DisruptorSimple
{

    public static void main(String[] args) throws Exception
    {
        factory();
    }


    public void simple()
    {
        Disruptor<OrderEvent> disruptor = DisruptorConfiguration.getInstance();
        disruptor.handleEventsWith(new MessageEventHandler());
        disruptor.start();
        for (int i = 0; i < 10; i++)
        {
            final int fi = i;
            disruptor.publishEvent(((event, sequence) -> event.setId(fi)));
        }
        disruptor.shutdown();
    }

    public static void multiMessageHandler()
    {
        Disruptor<OrderEvent> disruptor = DisruptorConfiguration.getInstance();
        for (int i = 0; i < 5; i++)
        {
            disruptor.handleEventsWith(new MessageEventHandler());
        }
        disruptor.start();
        for (int i = 0; i < 10; i++)
        {
            final int fi = i;
            disruptor.publishEvent(((event, sequence) -> event.setId(fi)));
        }
        disruptor.shutdown();
    }

    public static void arrayMessageHandler()
    {
        Disruptor<OrderEvent> disruptor = DisruptorConfiguration.getInstance();
        MessageEventHandler[] handlers = new MessageEventHandler[5];
        for (int i = 0; i < 5; i++)
        {
            handlers[i] = new MessageEventHandler();
        }
        disruptor.handleEventsWith(handlers);
        disruptor.start();
        for (int i = 0; i < 10; i++)
        {
            final int fi = i;
            disruptor.publishEvent(((event, sequence) -> event.setId(fi)));
        }
        disruptor.shutdown();
    }

    public static void groupMessageHandler()
    {
        Disruptor<OrderEvent> disruptor = DisruptorConfiguration.getInstance();

        EventHandlerGroup<OrderEvent> group = disruptor.handleEventsWith(new MessageEventHandler(), new MessageEventHandler());

        disruptor.start();
        for (int i = 0; i < 10; i++)
        {
            final int fi = i;
            disruptor.publishEvent(((event, sequence) -> event.setId(fi)));
        }
        disruptor.shutdown();
    }

    public static void factory()
    {
        Disruptor<OrderEvent> disruptor = DisruptorConfiguration.getInstance();
        disruptor.handleEventsWith(new MultiMessageEventProcessorFactory());
        disruptor.start();
        for (int i = 0; i < 10; i++)
        {
            final int fi = i;
            disruptor.publishEvent(((event, sequence) -> event.setId(fi)));
        }
        disruptor.shutdown();
    }
}
