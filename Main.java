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

		ElementaryCaView view[] = new ElementaryCaView[1];
		ElementaryCa elementaryCa = new ElementaryCa();
		elementaryCa.addMapping("111", 1);
		elementaryCa.addMapping("110", 0);
		elementaryCa.addMapping("101", 1);
		elementaryCa.addMapping("100", 1);
		elementaryCa.addMapping("011", 0);
		elementaryCa.addMapping("010", 1);
		elementaryCa.addMapping("001", 1);
		elementaryCa.addMapping("000", 0);

		try{
			SwingUtilities.invokeAndWait(new Runnable(){
				@Override
				public void run(){
					JFrame frame = new JFrame();
					view[0] = new ElementaryCaView(gridSize);
					frame.setTitle("Elementary Cellular Automata (Rule 182)");
					frame.getContentPane().add(view[0]);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.pack();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
			});
			ElementaryCaController controller = new ElementaryCaController(elementaryCa, view[0], initialState);
			Thread controllerThread = new Thread(controller);
			controllerThread.start();
		}catch(InterruptedException | InvocationTargetException e){
			System.out.println(e.getStackTrace());
		}
		
		
	}
}