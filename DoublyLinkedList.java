class Main {
  public static void main(String[] args) {
    //Testing double linked list
    DoublyLinkedList<Integer> x = new DoublyLinkedList<Integer>(null);
    for(int i = 0; i < 10; i++){
      Node<Integer> b = new Node<Integer>(i+1,null,null);
      x.add(b);
    }
    System.out.println(x);
    x.remove(1);
    System.out.println(x);
    x.insert(4,11);
    System.out.println(x);
    x.add(12);
    System.out.println(x);
    System.out.println(x.toStringRev());
  }
}

//Generic doubly linked list
class DoublyLinkedList <T> {
  private Node<T> head;
  private Node<T> latest;

  public DoublyLinkedList (Node<T> head) {
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
      Node<T> temp = latest;
      latest = node;
      latest.setPrev(temp);
      
    }
  }

  public Node<T> add(T val){
    Node<T> temp = new Node<T>(val,null,null);
    add(temp);
    return temp;
  }

  public Node<T> getHead(){
    return head;
  }

  public Node<T> remove(T val){
    Node<T> temp = findNode(val);
    if(temp!=null)
      remove(temp);
    return temp;
  }

  public T remove(Node<T> node){
    if(node == null)
      return null;
    Node<T> temp = node.getPrev();
    if(node == head) {
      head = node.getNext();
      head.setPrev(null); 
    }
    else if(node == latest){
      latest = node.getPrev();
      latest.setNext(null);
    }
    else{
      temp.setNext(node.getNext());
    }
    return node.getVal();
  }

  public Node<T> findNode(T val){
    Node<T> temp = head;
    while(temp != null) {
      if(temp.getVal() == val){
        return temp;
      }
      temp = temp.getNext();
    }
    return null;
  }

  public void insert(T val,T insertVal){
    Node<T> in = new Node<T>(insertVal,null,null);
    Node<T> temp = findNode(val);
    if(temp == null){
      return;
    }
    if(temp == head){
        in.setNext(head);
        head = in;
    }
    else{
      temp.getPrev().setNext(in);
      in.setNext(temp);
      in.setPrev(temp.getPrev());
      temp.setPrev(in);
    }
  }

  public String toString() {
    StringBuilder sb = new StringBuilder("");
    sb.append("[");
    Node<T> temp = head;
    while(temp.getNext() != null){
      sb.append(temp.getVal());
      sb.append(" , ");
      temp = temp.getNext();
    }
    sb.append(temp.getVal());
    sb.append("]");
    return sb.toString();
  }

  public String toStringRev() {
    StringBuilder sb = new StringBuilder("");
    sb.append("[");
    Node<T> temp = latest;
    while(temp.getPrev() != null) {
      sb.append(temp.getVal());
      sb.append(" , ");
      temp = temp.getPrev();
    }
    sb.append(temp.getVal());
    sb.append("]");
    return sb.toString();
  }

}
class Node <T> {
  private T val;
  private Node<T> next;
  private Node<T> prev;

  public Node (T val, Node<T> next, Node<T> prev){
    this.val = val;
    this.next = next;
    this.prev = prev;
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
  
  public Node<T> getPrev(){
    return prev;
  }

  public void setPrev(Node<T> prev) {
    this.prev = prev;
  }

}