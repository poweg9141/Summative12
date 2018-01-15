package tiles;

import coreEngine.Game;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import coreEngine.GameVariables;
import tiles.identifier.TileIdentifier;

/**
 * any texture that can be displayed to the screen but cant move or change form
 * @author Graham
 *
 */
public class Tile {
	
	//an array to store all the types of tile added into the game, tiles in the array are accessible using their renderable id
	public static final Tile[] tiles = new Tile[GameVariables.getMaxGameTiles()];
	//the standard width and height of a tile
	protected final int STANDARD_DIAMETER = GameVariables.getSTANDARD_TILE_DIAMETER();
	//a tiles texture and id it was created with
	protected BufferedImage texture;
	protected final int id;
	//whether or not the tile will act as a solid object in the game
	protected final boolean isSolid;
	//x and y of tile
        protected int x, y;
        protected static Game game;
	/**
	 * create a game tile
	 * @param id the tiles id
	 * @param isSolid whether it is solid or not
	 */
	public Tile(Game game, int id, boolean isSolid){
		//gets the tiles buffered image texture from its id
		texture = TileIdentifier.getTileImageFromID(id);
		this.id = id - 9000;
		this.isSolid = isSolid;		
		//adds the created tile type to the list of tiles
		tiles[this.id] = this;
                this.game = game;
	}
	
	//used to update the texture of the tile before it is rendered
	public void update(){
		
	}
	
	//renders the tile to the screen
	public void render(Graphics g, int x, int y){
                this.x = x;
                this.y = y;
		g.drawImage(texture, (int)(x + game.getCamera().getxOff()), (int)(y + game.getCamera().getyOff()), STANDARD_DIAMETER, STANDARD_DIAMETER, null);
	}
	
	//adds the default tile to the list of tiles in every spot to prevent a call to a tile that does not exist
	public static void initializeTiles(){
		for(int i = 0; i < tiles.length; i++){
			tiles[i] = new Tile(game, GameVariables.getDefaultTileId(), false);
		}
	}
	
	//returns the id used to access the tile from the list of tiles
	public static int returnRenderID(int id){
		//the id passes is from the gameVariables class where all tile ids start at 900
		//the id used to access it from the array starts from 0
		return id - 9000;
	}
	
	/**
	 * returns whether or not the tile is solid
	 * @return true is solid, false if not
	 */
	public boolean isSolid(){
		return isSolid;
	}
	
	public int getId(){
		return id;
	}
        
        public int getStandardDiameter(){
            return STANDARD_DIAMETER;            
        }
        
        public int getX(){
            return x;
        }
        
        public int getY(){
            return y;
        }
        
        public void setImage(BufferedImage image){
            this.texture = image;
        }      
        
        public BufferedImage getImage(){
            return this.texture;
        }
        
        public Tile getTile(){
            return this;
        }            
}
