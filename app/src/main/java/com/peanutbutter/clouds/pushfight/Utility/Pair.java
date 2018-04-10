package com.peanutbutter.clouds.pushfight.Utility;

public class Pair
{
    private int one;
    private int two;

    public Pair ( int one, int two )
    {
        this.one = one;
        this.two = two;
    }

    public int getOne () { return one; }

    public int getTwo () { return two; }

    @Override
    public boolean equals ( Object obj )
    {
        if ( obj == null )
        {
            return false;
        }
        if ( !Pair.class.isAssignableFrom( obj.getClass() ) )
        {
            return false;
        }

        final Pair pair = (Pair) obj;

        return this.one == pair.getOne() &&
               this.two == pair.getTwo();
    }

    @Override
    public int hashCode ()
    {
        int hash = 42;

        hash = 2 * hash + this.one;
        hash = 2 * hash + this.two;

        return hash;
    }

    public Pair subtract ( Pair pair )
    {
        return new Pair( one - pair.getOne(), two - pair.getTwo() );
    }

    public Pair add ( Pair pair )
    {
        return new Pair( one + pair.getOne(), two + pair.getTwo() );
    }
}
