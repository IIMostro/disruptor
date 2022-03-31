package com.lmax.disruptor.examples.neptune;

import java.util.StringJoiner;

/**
 * @author li.bowei
 */
public class OrderEvent
{

    private int id;
    private String name;

    @Override
    public String toString()
    {
        return new StringJoiner(", ", OrderEvent.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }

    public int getId()
    {
        return id;
    }

    public void setId(final int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }
}
