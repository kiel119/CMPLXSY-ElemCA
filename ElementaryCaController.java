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
                Thread.sleep(30);
                this.currentState = this.elementaryCa.getNewState(this.currentState);
                this.elementaryCaView.updateState(this.currentState);
            }
        }catch(InterruptedException | InvocationTargetException e){
            System.out.println(e.getStackTrace());
        }
        
    }
}