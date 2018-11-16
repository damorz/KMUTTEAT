package com.kmutteats.srinuan.firebasekmutteat;

public class Item
{
    private String name;
    private String location;
    private String description;
    private int priority;

    public Item()
    {

    }



    public Item (String name,String location,String description,int priority)
    {
        this.name = name;
        this.location = location;
        this.description = description;
        this.priority = priority;

    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
