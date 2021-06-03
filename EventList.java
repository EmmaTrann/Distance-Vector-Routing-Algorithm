package lab5.sectionb02.id7876069.logic;


import lab5.sectionb02.id7876069.entity.Event;

public interface EventList
{
    public boolean add(Event e);
    public Event removeNext();
    public String toString();
    public double getLastPacketTime(int entityFrom, int entityTo);
}
