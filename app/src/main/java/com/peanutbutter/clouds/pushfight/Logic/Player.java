package com.peanutbutter.clouds.pushfight.Logic;

public class Player
{
    private String name;
    private int    wins;
    private int    loses;
    private int    matches;

    public Player ( String name, int wins, int loses, int matches )
    {
        this.name = name;
        this.wins = wins;
        this.loses = loses;
        this.matches = matches;
    }

    public String getName () { return name; }

    public int getWins ()    { return wins; }

    public int getLoses ()   { return loses; }

    public int getMatches () { return matches; }
}
