class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");

    BST<Integer> test = new BST<>(null);
    for(int i = 5; i < 10; i++){
      Integer x = new Integer(i+1);
      test.insert(x);
    }
    for(int i = 0; i < 5; i++){
      Integer x = new Integer(i+1);
      test.insert(x);
    }
  }
}
class BST<T extends Comparable<T>> {
  private Node<T> head;
  public BST(Node<T> h){
    this.head = h;
  }
  public Node<T> getHead(){
    return head;
  }
  public void setHead(Node<T> h){
    this.head = h;
  }
  public Node<T> find(T val){
    Node<T> result = head;
    while(result != null){
      if(result.getVal().compareTo(val) == 0){
        return result;
      }
      else{
        if(val.compareTo(result.getVal()) < 0){
          result = result.getLeft();
        }
        else{
          result = result.getRight();
        }
      }
    }
    return null;
  }
  public void insert(T val){
    Node<T> node= new Node<T>(val,null,null,null);
    insNode(node);
    
  }
  public void insNode(Node<T> n){
    Node<T> cyclic = head;
    Node<T> oldCyclic = null;
    while(cyclic != null){
      oldCyclic = cyclic;
      if(n.getVal().compareTo(cyclic.getVal()) < 0){
        cyclic = cyclic.getLeft();
      }
      else{
        cyclic = cyclic.getRight();
      }
    }
    n.setParent(oldCyclic);
    if(n.getVal().compareTo(oldCyclic.getVal()) < 0){
      oldCyclic.setLeft(n);
    }
    else{
      oldCyclic.setRight(n);
    }
  }
  public Node<T> remove(T val){
    Node<T> rmv = find(val);
    Node<T> result = rmv;
    if(rmv == null){
      return null;
    }
    //ez
    if(rmv.getLeft() == null && rmv.getRight() == null){
      if(rmv == rmv.getParent().getLeft()){
        rmv.getParent().setLeft(null);
      }
      else{
        rmv.getParent().setRight(null);
      }
    }
    //Skip the middleman
    if(rmv.getLeft() == null && rmv.getRight() != null){
      if(rmv == rmv.getParent().getLeft()){
        rmv.getParent().setLeft(rmv.getRight());
        rmv.getRight().setParent(rmv.getParent());
      }
      else{
        rmv.getParent().setRight(rmv.getRight());
        rmv.getRight().setParent(rmv.getParent());
      }
    }
    //Skip the middleman
    if(rmv.getLeft() != null && rmv.getRight() == null){
      if(rmv == rmv.getParent().getLeft()){
        rmv.getParent().setLeft(rmv.getLeft());
        rmv.getLeft().setParent(rmv.getParent());
      }
      else{
        rmv.getParent().setRight(rmv.getLeft());
        rmv.getLeft().setParent(rmv.getParent());
      }
    }
    //what
    if(rmv.getLeft() != null && rmv.getRight() != null){
      Node<T> maxLeft = rmv.maxL();
      rmv.setVal(maxLeft.getVal());
      maxLeft.getParent().setRight(maxLeft.getLeft());
      if(maxLeft.getLeft() != null){
        maxLeft.getLeft().setParent(maxLeft.getParent());
      }
    }
    rmv = null;
    return result;
  }
  public String toString(){
    Node<T> cyclic
  }
}
public class Node<T>{
    private Node<T> p;
    private Node<T> l;
    private Node<T> r;
    private T val;
    public Node<T>(T val,Node<T> p, Node<T> l, Node<T> r){
      this.val = val;
      this.p = p;
      this.l = l;
      this.r = r;
    }
    public Node<T> getParent(){
      return p;
    }
    public void setParent(Node<T> x){
      p = x;
    }
    public Node<T> getLeft(){
      return l;
    }
    public void setLeft(Node<T> y){
      l = y;
    }
    public Node<T> getRight(){
      return r;
    }
    public void setRight(Node<T> z){
      r = z;
    }
    public T getVal(){
      return val;
    }
    public void setVal(T val){
      this.val = val;
    }
    public Node<T> maxL(){
      Node<T> cyclic = this.getLeft();
      Node<T> oldCyclic = null;
      while(cyclic != null){
        oldCyclic = cyclic;
        cyclic = cyclic.getRight();
      }
      return oldCyclic; 
    }
    public Node<T> minR(){}
  }
