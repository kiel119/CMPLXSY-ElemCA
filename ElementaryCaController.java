import java.lang.reflect.InvocationTargetException;

public class ElementaryCaController implements Runnable{
    private ElementaryCa elementaryCa;
    private ElementaryCaView elementaryCaView;
    private int[] currentState;

    public ElementaryCaController(ElementaryCa elementaryCa, ElementaryCaView elementaryCaView, int[] initialState){
        this.elementaryCa = elementaryCa;
        this.elementaryCaView = elementaryCaView;
        this.currentState = initialState;
    }

    @Override
    public void run(){
        try{
            while(true){
				this.elementaryCaView.updateState(this.currentState);
                this.currentState = this.elementaryCa.getNewState(this.currentState);
				Thread.sleep(30);
            }
        }catch(InterruptedException | InvocationTargetException e){
            System.out.println(e.getStackTrace());
        }
        
    }
}