//Implementing stack using dynArr
class Stack <T> {

  private dynArr<T> contents;

  public Stack(){
    contents= new dynArr<T>(1);
  }

  public T pop(){
    T res = contents.remove(contents.size()-1);
    return res;
  }

  public void push(T val){
    contents.add(val);
  }

  public T peek(){
    if(contents.size() == 0){
      return null;
    }
    return contents.get(contents.size()-1);
  }
  
  public boolean empty(){
    return contents.isEmpty();
  }
  
  public int search(Object o){
    return contents.indexOf((T)o);
  }
  
  public String toString(){
    return contents.toString();
  }

}