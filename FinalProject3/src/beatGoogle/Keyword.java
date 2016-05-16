package beatGoogle;


public class Keyword {
	String name;
	double weight;
	
	public Keyword(String name, double weight){
		this.name= name;
		this.weight=weight;		
	}
	
	@Override
	public String toString(){
		return name + "," + weight;
	}	

}
