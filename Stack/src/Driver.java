
public class Driver {

	public static void main(String[] args) {
		Stack<String> s = new Stack<String>();
		s.push("World!");
		System.out.println("Stack objects: " + s.getLength());

		s.push(" ");
		System.out.println("Stack objects: " + s.getLength());
		
		s.push("Hello,");
		System.out.println("Stack objects: " + s.getLength());
		
		
		String[] strings = new String[3];
		strings[0] = s.pop();
		System.out.println("Stack objects: " + s.getLength());
		
		strings[1] = s.pop();
		System.out.println("Stack objects: " + s.getLength());
		
		strings[2] = s.peek();
		System.out.println("Stack objects: " + s.getLength());
		
		StringBuilder sb = new StringBuilder();
		sb.append(strings[0]);
		sb.append(strings[1]);
		sb.append(strings[2]);
		System.out.println(sb.toString());
	}
	
}
