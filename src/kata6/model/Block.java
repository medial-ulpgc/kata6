package kata6.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import kata6.view.BlockDisplay;

public class Block {
    
    private int x;
    private int y;
    private final BlockSquareGrid grid;
    private final Timer timer;
    List<Observer> observers = new ArrayList();
    public Block(BlockSquareGrid grid) {
        this.grid = grid;
        x= y= grid.gridSize()/2;
        
        this.timer = new Timer();
        this.timer.schedule(timerTask(), 10, 1000);
        
    }
    
    public void left(){
        if(x>0){
            x--;
            changed();
        }
    }
    public void right(){
        if(x<grid.gridSize()-1){
            x++;
            changed();
        }
    }
    public void down(){
        if (y>0){
            y--;
            changed();
        }
    }
    public void up(){
        if(y<grid.gridSize()-1){
            y++;
            changed();
        }
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    private TimerTask timerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                double r = Math.random();
                if(r>0.2) return;
                if(r>0.15)up(); 
                if(r>0.10)down();
                if(r>0.05)left();
                else right();
                
            }
        };
    }

    private void changed() {
        observers.stream().forEach(Observer::changed);
    }

    public void register(Observer observer) {
        observers.add(observer);
    }
    public interface Observer{
        void changed();
    }
}
