import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args){
		int gridSize = 63;

		int[] initialState = new int[gridSize];
		for(int i=0; i<gridSize; ++i){
			initialState[i] = 0;
		}
		initialState[(int) ((gridSize - 1) / 2.0)] = 1;

		ElementaryCaView view = new ElementaryCaView(gridSize, initialState);

		Runnable createFrameRunnable = new Runnable(){
			@Override
			public void run(){
				JFrame frame = new JFrame();
				frame.setTitle("Elementary Cellular Automata (Rule 150)");
				int frameSize = view.getGridImageSize();
				frame.setSize(frameSize + 20, frameSize + 40);
				frame.getContentPane().add(view);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(false);
				frame.setVisible(true);
			}
		};

		try{
			SwingUtilities.invokeAndWait(createFrameRunnable);
		}catch(InterruptedException | InvocationTargetException e){
			System.out.println(e.getStackTrace());
		}
		
	}
}