package com.peanutbutter.clouds.pushfight.Logic;

public abstract class Piece
{
    public static final String COLOR_DARK  = "brown";
    public static final String COLOR_LIGHT = "white";

    private String  color;
    private boolean pusher;

    abstract public void display ();

    Piece ( String color, boolean pusher )
    {
        this.color = color;
        this.pusher = pusher;
    }

    public String getColor ()  { return color; }

    public boolean isPusher () { return pusher; }

    public PathTrace tracePaths ( int sourceX, int sourceY, int destX, int destY )
    {

        return null;
    }
}
