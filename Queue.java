//Implementing queue using doubly linked list
class Queue <T>{
  private DoublyLinkedList<T> contents;
  public Queue() {
    contents = new DoublyLinkedList(null);
  }
  public void add(T val){
    contents.add(val);
  }
  public T remove(){
    return contents.remove(contents.getHead());
  }
  public T peek(){
    return contents.getHead().getVal();
  }
  public boolean empty(){
    return contents.isEmpty();
  }
  public String toString(){
    return contents.toString();
  }
}