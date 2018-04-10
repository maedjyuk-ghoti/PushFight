package com.peanutbutter.clouds.pushfight.Algorithm;

import com.peanutbutter.clouds.pushfight.Logic.Box;
import com.peanutbutter.clouds.pushfight.Utility.Pair;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BreadthFirstSearch
{
    private Queue < Pair > open_queue   = new LinkedList <>();
    private Queue < Pair > closed_queue = new LinkedList <>();

    public boolean isReachable ( Pair source, Pair dest, Map < Pair, Box > boxes )
    {
        open_queue.add( source );

        while ( !open_queue.isEmpty() )
        {
            Pair currentPair = open_queue.remove();

            if ( currentPair.equals( dest ) )
            {
                return true;
            }

            for ( Pair connectedPair : getConnectedPairs( currentPair, boxes ) )
            {
                if ( closed_queue.contains( connectedPair ) )
                {
                    continue;
                }

                if ( !open_queue.contains( connectedPair ) &&
                     boxes.get( connectedPair ).getPiece() == null )
                {
                    open_queue.add( connectedPair );
                }

                closed_queue.add( connectedPair );
            }
        }

        return false;
    }

    private LinkedList < Pair > getConnectedPairs ( Pair currentPair, Map < Pair, Box > boxes )
    {
        Queue < Pair > queue = new LinkedList <>();

        Pair right = new Pair( currentPair.getOne() + 1, currentPair.getTwo() );
        if ( boxes.containsKey( right ) )
        {
            queue.add( right );
        }

        Pair left = new Pair( currentPair.getOne() - 1, currentPair.getTwo() );
        if ( boxes.containsKey( left ) )
        {
            queue.add( left );
        }

        Pair up = new Pair( currentPair.getOne(), currentPair.getTwo() + 1 );
        if ( boxes.containsKey( up ) )
        {
            queue.add( up );
        }

        Pair down = new Pair( currentPair.getOne(), currentPair.getTwo() - 1 );
        if ( boxes.containsKey( down ) )
        {
            queue.add( down );
        }

        return (LinkedList < Pair >) queue;
    }
}
