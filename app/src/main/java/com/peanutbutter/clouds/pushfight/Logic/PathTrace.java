package com.peanutbutter.clouds.pushfight.Logic;

import com.peanutbutter.clouds.pushfight.Utility.Pair;

import java.util.List;

public class PathTrace
{
    public int                    sourceX;
    public int                    sourceY;
    public int                    destX;
    public int                    destY;
    public boolean                hasValidTrace;
    public List < List < Pair > > trace;
}
