package tiles.types;

import entities.mobs.Player;
import tiles.Tile;

/**
 * any tile that is solid
 * @author Graham
 *
 */
public class WallTile extends Tile{

	/**
	 * use it create a tile that is solid
	 * @param id the id of the tile
	 */
	public WallTile(int id) {
		super(id, true);
	}
        

        public void update(Player p){
            //gets the players x, y ,width, and height
            float px = p.getX();
            float py = p.getY();
            int pw = p.getWidth();
            int ph = p.getHeight();
            float pdx = 0;
            float pdy = 0;
            if(px + pw >= x && px + pw <= x + STANDARD_DIAMETER){
                if(py + ph >= y && py + ph <= y + STANDARD_DIAMETER){
                    pdy -= (y - (py + ph));
                }
                else if(py <= y + STANDARD_DIAMETER && py >= y){
                    pdy += (y - py);
                }
            }
            else if(px <= x + STANDARD_DIAMETER && px >= x){
                if(){
                    
                }
                else if(){
                    
                }   
            }                

}
