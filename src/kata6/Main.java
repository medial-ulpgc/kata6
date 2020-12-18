package kata6;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import kata6.control.Command;
import kata6.control.DownCommand;
import kata6.control.LeftCommand;
import kata6.control.RightCommand;
import kata6.control.UpCommand;
import kata6.model.Block;
import kata6.model.BlockSquareGrid;
import kata6.view.BlockDisplay;
public class Main extends JFrame{

    public static void main(String[] args) {
        new Main().execute();
    }
    private BlockDisplay blockDisplay;
    private BlockSquareGrid blockSquareGrid;
    private Block block;
    private static final int UI_EXTRAS_SIZE=20;
    private Map<String, Command> commands;

    public Main(){
        this.setTitle("BlockShifter");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(blockPanel());
        this.setSize(blockSquareGrid.gridSize()*BlockPanel.BLOCK_SIZE,blockSquareGrid.gridSize()*BlockPanel.BLOCK_SIZE + UI_EXTRAS_SIZE);

    }

    private void execute() {
        this.block = new Block(blockSquareGrid);
        this.blockDisplay.display(block);
        this.block.register(blockDisplay);
        this.add(toolbar(), BorderLayout.SOUTH);
        this.commands = createCommands();
        this.pack();
        this.setVisible(true);
    }

    private JComponent blockPanel() {
        BlockPanel blockPanel = new BlockPanel();
        this.blockSquareGrid = blockPanel;
        this.blockDisplay = blockPanel;
        return blockPanel;
    }

    private Map<String, Command> createCommands() {
        commands = new HashMap<>();
        commands.put("L", new LeftCommand(block));
        commands.put("R", new RightCommand(block));
        commands.put("U", new UpCommand(block));
        commands.put("D", new DownCommand(block));
        return commands;
    }

    private Component toolbar() {
        JMenuBar toolbar = new JMenuBar();
        toolbar.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolbar.add(button("L"));
        toolbar.add(button("R"));
        toolbar.add(button("U"));
        toolbar.add(button("D"));
        return toolbar;
    }

    private JButton button(String commandName) {
        JButton button = new JButton(commandName);
        button.addActionListener((ActionEvent event)->commands.get(commandName).execute());
        return button;
    }
    
    
}
