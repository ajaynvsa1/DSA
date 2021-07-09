
class Main {
  public static void main(String[] args) {
    //Testing the linked list
    LinkedList<Integer> x = new LinkedList<Integer>(null);
    for(int i = 0; i < 10; i++){
      Node<Integer> b = new Node<Integer>(i+1,null);
      x.add(b);
    }
    Integer tbr = new Integer(1);
    x.remove(tbr);
    x.insert(4,11);
    System.out.println(x);
  }
}

//Generic linked list
class LinkedList <T> {
  //Tracks the head  
  private Node<T> head;
  //Tracks the tail
  private Node<T> latest;

  public LinkedList (Node<T> head) {
    this.head = head;
    latest = head;
  }

  public boolean isEmpty(){
    return head==null;
  }

  public void add(Node<T> node){
    if(head == null){
      head = node;
      latest = node;
    }
    else{
      latest.setNext(node);
      latest = node;
    }
  }

  public Node<T> getHead(){
    return head;
  }
  
  public Node<T> remove(T val){
    Node<T> temp = head;
    Node<T> past = head;
    while(temp != null){
      if(temp.getVal().equals(val)){
        if(temp == head){
          head = head.getNext();
          past=null;
        }
        else if(temp == latest){
          latest = past;
          latest.setNext(null);
        }
        else{
          past.setNext(temp.getNext());
        }
        break;
      }
      else{
        past = temp;
        temp = temp.getNext();
      }
    }
    return temp;
  }
  public void insert(T val,T insertVal){
    Node<T> in = new Node<T>(insertVal,null);
    Node<T> temp = head;
    Node<T> past = head;
    while(temp != null){
      if(temp.getVal().equals(val)){
        if(temp == head){
          in.setNext(head);
          head = in;
        }
        else{
          past.setNext(in);
          in.setNext(temp);
        }
        break;
      }
      else{
        past = temp;
        temp = temp.getNext();
      }
    }
  }

  public String toString() {
    StringBuilder sb = new StringBuilder("");
    sb.append("[");
    Node<T> temp = head.getNext();
    while(temp.getNext() != null){
      sb.append(temp.getVal());
      sb.append(" , ");
      temp = temp.getNext();
    }
    sb.append(temp.getVal());
    sb.append("]");
    return sb.toString();
  }
}

class Node <T> {
  private T val;
  private Node<T> next;
  public Node (T val, Node<T> next){
    this.val = val;
    this.next = next;
  }
  public T getVal() {
    return val;
  }
  public void setVal(T val) {
    this.val = val;
  }
  public Node<T> getNext() {
    return next;
  }
  public void setNext(Node<T> next) {
    this.next = next;
  }

}