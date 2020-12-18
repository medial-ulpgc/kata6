
package kata6;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import kata6.model.Block;
import kata6.model.BlockSquareGrid;
import kata6.view.BlockDisplay;
class BlockPanel extends JPanel implements BlockDisplay, BlockSquareGrid{

    static final int BLOCK_SIZE=30;
    static final int GRID_SIZE=9;
    private Block block;

    public BlockPanel() {
        this.setSize(BLOCK_SIZE*GRID_SIZE, BLOCK_SIZE*GRID_SIZE);
        
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BLOCK_SIZE*GRID_SIZE, BLOCK_SIZE*GRID_SIZE);
        
    }
    
    
    

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0,getWidth(), getHeight());
        g.setColor(Color.GRAY);
        int m = GRID_SIZE* BLOCK_SIZE; 
        
        for (int i = 0; i < GRID_SIZE; i++) {
            int d = i*BLOCK_SIZE;
            g.drawLine(0,d,m,d);
            g.drawLine(d,0,d,m);
        }
        if(block!=null){
            g.setColor(Color.RED);
            g.fillRect((block.x())*BLOCK_SIZE, (GRID_SIZE -(block.y()+1))*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        }    
    }

    @Override
    public void display(Block block) {
        this.block = block;
        this.repaint();
    }

    @Override
    public int gridSize() {
        return GRID_SIZE;
    }

    @Override
    public void changed() {
        this.repaint();
    }
    
}
