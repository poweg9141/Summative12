/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics.animation;

import graphics.SpriteSheet;

/**
 *
 * @author poweg9141
 */
public class Animation {
    
    private SpriteSheet sheet;
    private int ImagesPerSecond;
    private long startTime;
    
    public Animation(SpriteSheet sheet, int imagesPerSecond){
        this.sheet = sheet;
        this.ImagesPerSecond = imagesPerSecond;
    }
    
    public BufferedImage getImageAtTime(long time){
        long newTime = startTime - time;
        
    }
}
