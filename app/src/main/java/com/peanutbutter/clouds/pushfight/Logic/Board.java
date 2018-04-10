package com.peanutbutter.clouds.pushfight.Logic;

import com.peanutbutter.clouds.pushfight.Algorithm.BreadthFirstSearch;
import com.peanutbutter.clouds.pushfight.Utility.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Board
{
    public static final  String        NONE         = "none";
    private static final List < Pair > invalidBoxes =
            new ArrayList <>(
                    Arrays.asList(
                            new Pair( 0, 0 ),
                            new Pair( 1, 0 ),
                            new Pair( 6, 0 ),
                            new Pair( 1, 3 ),
                            new Pair( 5, 3 ),
                            new Pair( 6, 3 ) ) );

    private static final List < Pair > startingPositionsLight =
            new ArrayList <>(
                    Arrays.asList(
                            new Pair( 3, 0 ),
                            new Pair( 3, 1 ),
                            new Pair( 3, 2 ),
                            new Pair( 3, 3 ),
                            new Pair( 2, 3 ) ) );

    private static final List < Pair > startingPositionsDark =
            new ArrayList <>(
                    Arrays.asList( new Pair( 4, 0 ),
                                   new Pair( 4, 1 ),
                                   new Pair( 4, 2 ),
                                   new Pair( 4, 3 ),
                                   new Pair( 5, 3 ) ) );

    private static final int X = 8;
    private static final int Y = 4;

    private Map < Pair, Box > gameBoard = new HashMap <>();
    private Map.Entry < Pair, Box > outOfBounds;

    public Board ()
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

        outOfBounds = null;
    }

    public boolean movePiece ( Pair source, Pair dest )
    {
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        if ( bfs.isReachable( source, dest, gameBoard ) )
        {
            Piece piece = gameBoard.get( source ).getPiece();
            gameBoard.get( source ).setPiece( null );
            gameBoard.get( dest ).setPiece( piece );
            return true;
        }

        return false;
    }

    // todo
    public boolean pushPiece ( Pair source, Pair dest )
    {
        // check if dest is taken
        if ( !gameBoard.containsKey( dest ) ||
             gameBoard.get( dest ).getPiece() == null ||
             !gameBoard.get( source ).getPiece().isPusher() )
        {
            return false;
        }

        Pair vector = dest.subtract( source );

        return push( dest, dest.add( vector ) );
    }

    private boolean push ( Pair source, Pair dest )
    {
        if ( !gameBoard.containsKey( dest ) ||
             gameBoard.get( dest ).getPiece() == null)
        {

        }

    }

    public void resetBoard ()
    {
        boolean lightSquare = true;
        boolean darkSquare  = true;

        for ( Map.Entry < Pair, Box > entry : gameBoard.entrySet() )
        {
            if ( startingPositionsLight.contains( entry.getKey() ) )
            {
                entry.getValue().setPiece( lightSquare
                                           ? new Square( Piece.COLOR_LIGHT )
                                           : new Round( Piece.COLOR_LIGHT ) );
                lightSquare = !lightSquare;
            }
            else if ( startingPositionsDark.contains( entry.getKey() ) )
            {
                entry.getValue().setPiece( darkSquare
                                           ? new Square( Piece.COLOR_DARK )
                                           : new Round( Piece.COLOR_DARK ) );
                darkSquare = !darkSquare;
            }
            else
            {
                entry.getValue().setPiece( null );
            }
        }
    }

    public String checkWin ()
    {
        if ( outOfBounds == null )
        {
            return NONE;
        }

        // return the color opposite the one out of bounds
        return Piece.COLOR_DARK.equals( outOfBounds.getValue().getPiece().getColor() )
               ? Piece.COLOR_LIGHT
               : Piece.COLOR_DARK;
    }
}
