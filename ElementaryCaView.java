import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.reflect.InvocationTargetException;
import java.awt.Color;

public class ElementaryCaView extends JPanel{
	private int[][] state;
	private int nextRow;
	private int size;
	private RepaintRunnable repaintRunnable;

	private static final Color ONE_STATE_COLOR = Color.BLACK;
	private static final Color ZERO_STATE_COLOR = new Color(255, 255, 255, 0);
	private static final int RECT_SIZE = 10;
	
	public ElementaryCaView(int size, int[] initialState){
		this.size = size;
		this.state = new int[this.size][this.size];
		this.nextRow = 0;
		this.repaintRunnable = new RepaintRunnable();
		this.copyState(this.nextRow, initialState);
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
		Graphics2D g2 = (Graphics2D) g;

		int imageSize = this.getGridImageSize();
		this.setSize(imageSize, imageSize);

		for(int i=0; i<this.size; ++i){
			for(int j=0; j<this.size; ++j){
				Color cellColor = this.state[i][j] == 0 ? ZERO_STATE_COLOR : ONE_STATE_COLOR;
				g2.setColor(cellColor);
				g2.fillRect(j * RECT_SIZE, i * RECT_SIZE, RECT_SIZE, RECT_SIZE);
			}
		}

		int lineSize = this.size * RECT_SIZE;
		g2.setColor(Color.gray);
		for(int i=0; i<this.size; ++i){
			int current = i * RECT_SIZE;
			g2.drawLine(current, 0, current, lineSize);
			g2.drawLine(0, current, lineSize, current);
		}

		g2.dispose();
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