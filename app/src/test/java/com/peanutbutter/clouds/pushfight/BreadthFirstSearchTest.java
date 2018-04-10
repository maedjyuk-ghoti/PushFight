package com.peanutbutter.clouds.pushfight;

import com.peanutbutter.clouds.pushfight.Algorithm.BreadthFirstSearch;
import com.peanutbutter.clouds.pushfight.Logic.Box;
import com.peanutbutter.clouds.pushfight.Logic.Piece;
import com.peanutbutter.clouds.pushfight.Logic.Round;
import com.peanutbutter.clouds.pushfight.Utility.Pair;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BreadthFirstSearchTest
{
    /**
     * The game board, 0x0 is top left, 6x3 is bottom right
     * --0000-
     * 0000000
     * 0000000
     * -0000--
     */
    private static final List < Pair > invalidBoxes =
            new ArrayList <>(
                    Arrays.asList(
                            new Pair( 0, 0 ),
                            new Pair( 1, 0 ),
                            new Pair( 6, 0 ),
                            new Pair( 1, 3 ),
                            new Pair( 5, 3 ),
                            new Pair( 6, 3 ) ) );

    private static final int X = 7;
    private static final int Y = 4;

    private Map < Pair, Box > gameBoard = new HashMap <>();

    @Before
    public void setup ()
    {
        for ( int i = 0; i < X; i++ )
        {
            for ( int j = 0; j < Y; j++ )
            {
                Pair pair = new Pair( i, j );
                if ( !invalidBoxes.contains( pair ) )
                {
                    gameBoard.put( pair, new Box() );
                }
            }
        }
    }

    /**
     * Attempt to reach an empty space
     * --0000-
     * 0000000
     * 000x000
     * -0000--
     */
    @Test
    public void isReachableEmpty ()
    {
        BreadthFirstSearch bfs = new BreadthFirstSearch();

        Pair source = new Pair( 3, 2 );
        gameBoard.get( source ).setPiece( new Round( Piece.COLOR_DARK ) );

        Pair dest = new Pair( 4, 2 );

        assertTrue( bfs.isReachable( source, dest, gameBoard ) );
    }

    /**
     * Attempt to reach an occupied space
     * --0000-
     * 0000000
     * 000xx00
     * -0000--
     */
    @Test
    public void isReachableOccupied ()
    {
        BreadthFirstSearch bfs = new BreadthFirstSearch();

        Pair source = new Pair( 3, 2 );
        gameBoard.get( source ).setPiece( new Round( Piece.COLOR_DARK ) );

        Pair dest = new Pair( 4, 2 );
        gameBoard.get( dest ).setPiece( new Round( Piece.COLOR_DARK ) );

        assertFalse( bfs.isReachable( source, dest, gameBoard ) );
    }

    /**
     * Attempt to reach an unreachable space
     * --0x00-
     * 000x000
     * 0x0x000
     * -00x0--
     */
    @Test
    public void isReachableUnreachable ()
    {
        BreadthFirstSearch bfs = new BreadthFirstSearch();

        Pair source = new Pair( 1, 2 );
        gameBoard.get( source ).setPiece( new Round( Piece.COLOR_DARK ) );

        Pair dest = new Pair( 4, 2 );

        gameBoard.get( new Pair( 3, 0 ) ).setPiece( new Round( Piece.COLOR_DARK ) );
        gameBoard.get( new Pair( 3, 1 ) ).setPiece( new Round( Piece.COLOR_DARK ) );
        gameBoard.get( new Pair( 3, 2 ) ).setPiece( new Round( Piece.COLOR_DARK ) );
        gameBoard.get( new Pair( 3, 3 ) ).setPiece( new Round( Piece.COLOR_DARK ) );

        assertFalse( bfs.isReachable( source, dest, gameBoard ) );
    }

    /**
     * Attempt to reach an current space
     * --0000-
     * 0000000
     * 000x000
     * -0000--
     */
    @Test
    public void isReachableCurrent ()
    {
        BreadthFirstSearch bfs = new BreadthFirstSearch();

        Pair source = new Pair( 3, 2 );
        gameBoard.get( source ).setPiece( new Round( Piece.COLOR_DARK ) );

        Pair dest = new Pair( 3, 2 );

        assertTrue( bfs.isReachable( source, dest, gameBoard ) );
    }

    /**
     * Attempt to reach an current space
     * --0000-
     * 0000000
     * 000x000
     * -0000--
     */
    @Test
    public void isReachableInvalidSpace ()
    {
        BreadthFirstSearch bfs = new BreadthFirstSearch();

        Pair source = new Pair( 3, 2 );
        gameBoard.get( source ).setPiece( new Round( Piece.COLOR_DARK ) );

        Pair dest = new Pair( 0, 0 );

        assertFalse( bfs.isReachable( source, dest, gameBoard ) );
    }
}