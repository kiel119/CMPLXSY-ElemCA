import java.util.HashMap;

public class ElementaryCa {
	private HashMap<String, Integer> mappings;

	public ElementaryCa(){
		this.mappings = new HashMap<String, Integer>();
	}

	public void addMapping(String key, int value){
		mappings.put(key, value);
	}

	public int[] getNewState(int[] oldState){
		String s1, s2, s3;
		int newState[] = new int[oldState.length];

		s1 = Integer.toString(oldState[oldState.length - 1]);
		s2 = Integer.toString(oldState[0]);
		s3 = Integer.toString(oldState[1]);
		
		newState[0] = mappings.get(s1 + s2 + s3);

		for(int i = 1; i < oldState.length - 1; ++i){
			s1 = Integer.toString(oldState[i-1]);
			s2 = Integer.toString(oldState[i]);
			s3 = Integer.toString(oldState[i+1]);
			
			newState[i] = mappings.get(s1 + s2 + s3);
		}

		s1 = Integer.toString(oldState[oldState.length - 2]);
		s2 = Integer.toString(oldState[oldState.length - 1]);
		s3 = Integer.toString(oldState[0]);
		newState[oldState.length - 1] = mappings.get(s1 + s2 + s3);
		return newState;
	}
}