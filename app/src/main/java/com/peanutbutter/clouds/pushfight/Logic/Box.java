package com.peanutbutter.clouds.pushfight.Logic;

public class Box
{
    public static final String COLOR_DARK  = "dark";
    public static final String COLOR_LIGHT = "light";

    private Piece  piece;
    private String color;

    public void displayBox ()
    {

    }

    public Piece getPiece ()             { return piece; }

    public void setPiece ( Piece piece ) { this.piece = piece; }
}
