package com.whatever.chess.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by felipecosta on 8/21/16.
 */
 public class  Util {
    public static final String blackPawn = "blackPawn.png";
    public static final String whitePawn = "whitePawn.png";
    public static final String blackKnight = "blackKnight.png";
    public static final String whiteKnight = "whiteKnight.png";
    public static final String blackRook = "blackRook.png";
    public static final String whiteRook = "whiteRook.png";
    public static final String blackQueen = "blackQueen.png";
    public static final String whiteQueen = "whiteQueen.png";
    public static final String blackKing = "blackKing.png";
    public static final String whiteKing = "whiteKing.png";
    public static final String blackBishop = "blackBishop.png";
    public static final String whiteBishop = "whiteBishop.png";


    public static Sprite loadSprite(String path){
        if(Gdx.files.internal(path).exists()){
            Sprite sprite = new Sprite(new Texture(Gdx.files.internal(path)));
            System.out.println("Loaded "+ path);
            return sprite;
        }
        else{
            System.err.println("Missing file " + path);
            System.exit(-1);
        }
        return null;
    }
}
