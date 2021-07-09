import java.util.*;
class Main {
 //Check if a string made of '[' '{' '(' ']' '}' ')' is valid using stack
  public static void main(String[] args) {
    Stack<Character> x = new Stack<Character>();
    Scanner in = new Scanner(System.in);
    String a = in.next();
    char[] c = a.toCharArray();
    HashMap<Character,Character> map = new HashMap<>();
    map.put('{','}');
    map.put('[',']');
    map.put('(',')');
    boolean result = true;
    for(int i = 0; i < c.length; i++){
      Character current = new Character(c[i]);
      if(current == '{' || current == '[' || current == '('){
        x.push(c[i]);
      }
      else if(x.peek() != null && current.equals(map.get(x.peek()))){
        x.pop();
      }
      else{
        result = false;
        break;
      }
    }
    if(!x.empty()){
      result = false;
    }
    System.out.print(result);
  }

}
