package com.lmax.disruptor.examples.neptune;

import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

/**
 * @author li.bowei
 */
public class DisruptorConfiguration
{

    public static Disruptor<OrderEvent> getInstance(){
        WaitStrategy strategy = new YieldingWaitStrategy();
        int bufferSize = 16;
        Disruptor<OrderEvent> disruptor = new  Disruptor<>(OrderEvent::new, bufferSize, Thread::new, ProducerType.SINGLE, strategy);
        disruptor.setDefaultExceptionHandler(new ExceptionHandler<>()
        {
            @Override
            public void handleEventException(Throwable ex, long sequence, OrderEvent event)
            {
                System.err.println("error");
            }

            @Override
            public void handleOnStartException(Throwable ex)
            {
            }

            @Override
            public void handleOnShutdownException(Throwable ex)
            {
            }
        });
        return disruptor;
    }
}
