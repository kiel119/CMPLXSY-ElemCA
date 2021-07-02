import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.awt.Color;
import java.awt.Dimension;

public class ElementaryCaView extends JComponent{
	private int[][] state;
	private int nextRow;
	private int size;
	private RepaintRunnable repaintRunnable;

	private static final Color ONE_STATE_COLOR = Color.BLACK;
	private static final Color ZERO_STATE_COLOR = new Color(255, 255, 255, 0);
	private static final int RECT_SIZE = 10;
	
	public ElementaryCaView(int size){
		this.size = size;
		this.state = new int[this.size][this.size];
		this.nextRow = 0;
		this.repaintRunnable = new RepaintRunnable();
		this.resetState();
		int imageSize = this.getGridImageSize();
		this.setPreferredSize(new Dimension(imageSize, imageSize));
		this.setMinimumSize(new Dimension(imageSize, imageSize));
		this.setMaximumSize(new Dimension(imageSize, imageSize));
	}

	private void copyState(int row, int[] newState){
		for(int i=0; i<this.size; ++i){
			this.state[row][i] = newState[i];
		}
	}

	private void resetState(){
		for(int i=0; i<this.size; ++i){
			for(int j=0; j<this.size; ++j){
				this.state[i][j] = 0;
			}
		}
	}

	private class RepaintRunnable implements Runnable {
		@Override
		public void run(){
			ElementaryCaView.this.repaint();
		}
	}

	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);

		for(int i=0; i<this.size; ++i){
			for(int j=0; j<this.size; ++j){
				Color cellColor = this.state[i][j] == 0 ? ZERO_STATE_COLOR : ONE_STATE_COLOR;
				g.setColor(cellColor);
				g.fillRect(j * RECT_SIZE, i * RECT_SIZE, RECT_SIZE, RECT_SIZE);
			}
		}

		int lineSize = this.size * RECT_SIZE;
		g.setColor(Color.gray);
		for(int i=0; i<this.size; ++i){
			int current = i * RECT_SIZE;
			g.drawLine(current, 0, current, lineSize);
			g.drawLine(0, current, lineSize, current);
		}

		int gridSize = this.getGridImageSize();
		g.drawRect(0, 0, gridSize, gridSize);

		g.dispose();
	}

	public int getGridImageSize(){
		return this.size * RECT_SIZE;
	}

	public void updateState(int[] newState) throws InterruptedException, InvocationTargetException{
		if(this.nextRow == this.size){
			this.nextRow = 0;
			this.resetState();
		}
		this.copyState(this.nextRow, newState);
		this.nextRow++;
		if(SwingUtilities.isEventDispatchThread()){
			this.repaintRunnable.run();
		}else{
			SwingUtilities.invokeAndWait(this.repaintRunnable);
		}
	}
}