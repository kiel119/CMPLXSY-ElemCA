import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args){
		int gridSize = 73;

		int[] initialState = new int[gridSize];
		for(int i=0; i<gridSize; ++i){
			initialState[i] = 0;
		}
		initialState[(int) ((gridSize - 1) / 2.0)] = 1;

		ElementaryCaView view = new ElementaryCaView(gridSize, initialState);
		ElementaryCa elementaryCa = new ElementaryCa();
		elementaryCa.addMapping("111", 1);
		elementaryCa.addMapping("110", 0);
		elementaryCa.addMapping("101", 1);
		elementaryCa.addMapping("100", 1);
		elementaryCa.addMapping("011", 0);
		elementaryCa.addMapping("010", 1);
		elementaryCa.addMapping("001", 1);
		elementaryCa.addMapping("000", 0);
		ElementaryCaController controller = new ElementaryCaController(elementaryCa, view, initialState);

		Runnable createFrameRunnable = new Runnable(){
			@Override
			public void run(){
				JFrame frame = new JFrame();
				frame.setTitle("Elementary Cellular Automata (Rule 182)");
				int frameSize = view.getGridImageSize();
				frame.setSize(frameSize + 20, frameSize + 40);
				frame.getContentPane().add(view);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		};

		try{
			SwingUtilities.invokeAndWait(createFrameRunnable);
		}catch(InterruptedException | InvocationTargetException e){
			System.out.println(e.getStackTrace());
		}
		
		Thread controllerThread = new Thread(controller);
		controllerThread.start();
	}
}